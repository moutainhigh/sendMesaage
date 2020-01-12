package com.asiainfo.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.common.ResourceUtil;
import com.asiainfo.common.utils.DateUtils;
import com.asiainfo.common.utils.StringUtil;
import com.asiainfo.order.dao.EventMappingMapper;
import com.asiainfo.order.dao.EventTemplateMapper;
import com.asiainfo.order.dao.OrderMapper;
import com.asiainfo.thread.SendMsgThread;
import com.ctg.mq.api.exception.MQException;
import com.ctgMq.tool.CTGMqTool;
import com.ctgMq.tool.STATUS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("orderService")
@Transactional(noRollbackFor = NullPointerException.class)
public class OrderService {
    private static Logger logger = Logger.getLogger(OrderService.class);

    private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config/appConfig");

    private static ExecutorService service = Executors.newFixedThreadPool(100);

    private static final Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EventMappingMapper eventMappingMapper;

    @Autowired
    private EventTemplateMapper eventTemplateMapper;

    public void check() {
//        service.shutdown();
        try {
            while (((ThreadPoolExecutor) service).getActiveCount() > 0) {
                logger.debug("运行线程数：" + ((ThreadPoolExecutor) service).getActiveCount());
                Thread.sleep(100);
            }
        } catch (Exception ex) {

        }


//        try {
//            while (true) {
//                if (service.isTerminated()) {
//                    System.out.println("所有的子线程都结束了！");
//                    System.exit(0);
//                }
//                Thread.sleep(100);
//            }
//        }catch (Exception ex){
//
//        }

    }

    public void sendMsg(Map orderMap, JSONObject result) {
        service.submit(new SendMsgThread(orderMap, result));

//        service.shutdown();
//        try {
//            while (true) {
//                if (service.isTerminated()) {
//                    System.out.println("所有的子线程都结束了！");
//                    System.exit(0);
//                }
//                Thread.sleep(100);
//            }
//        }catch (Exception ex){
//
//        }


    }


    public void scanMqMsg(Map orderMap, JSONObject result) {
        try {
            Map sendMap = new HashMap(); // 创建需要发送短信参数的Map
            String outRemindText = (String) orderMap.get("outRemindText"); // 获取text内容进信分割
            String[] outRemindTextArray = outRemindText.split("\\|");
            if (outRemindTextArray[0] == null) {
                throw new NullPointerException("outRemindText为空，请确认数据是否正确");
            }
            String eventType = (String) ResourceUtil.eventMappingMap.get(outRemindTextArray[0]);
            if (eventType != null) {                     // 执行B格式 直接解析Json报文
                if (outRemindTextArray[1] == null) {
                    throw new NullPointerException("json报文为空，请确认数据是否正确");
                } else {
                    String msg_text = outRemindTextArray[1];
                    Map msgTextMap = JSON.parseObject(msg_text);
                    sendMap.putAll(msgTextMap);
                    sendMap.put("eventType", eventType);

                }
            } else {
                logger.debug("======================遍历开始时间=============================" + new Date());
                String remindText = (String) ResourceUtil.eventTemplateMap.get(outRemindTextArray[0]);
                if (remindText == null) {
                    throw new NullPointerException("事件编码未找到对应短信模板");
                }
                Matcher matcher = pattern.matcher(remindText);
                int i = 1;
                while (matcher.find()) {
                    if (outRemindTextArray[i] == null || outRemindTextArray[i] == " ") {
                        throw new NullPointerException("outRemindText中的分割值数量与模板数不匹配");
                    }
                    String key = matcher.group();
                    sendMap.put(key, outRemindTextArray[i]);
                    i++;
                }
                sendMap.remove("event_id");
                sendMap.put("eventId", outRemindTextArray[1]);  // 事件Id
            }
            logger.debug("==============遍历结束时间====================" + new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sendMap.put("eventCode", outRemindTextArray[0]); // 事件编码
            sendMap.put("contactPhone", orderMap.get("accNbr")); // 号码
            sendMap.put("prodInstId", orderMap.get("remindServId")); // 用户实例Id
            sendMap.put("startTime", sdf.format(orderMap.get("createdDate"))); // 发生时间
            sendMap.put("endTime", sdf.format(orderMap.get("stateDate"))); // 结束时间
            String time = DateUtils.dateString(new Date(), DateUtils.yyyyMMddTIME);
            String seq = time + (int) (Math.random() * 1000 * 1000 * 1000);
            logger.info("=============== 发送的消息ID为:" + orderMap.get("templateOrderId") + "---MQ消息队列所对应的KeyId为:" + seq + " ===============");
//            STATUS status = CTGMqTool.send(seq, "10802", JSON.toJSON(sendMap).toString());
            STATUS status;
            if(ResourceUtil.eventTopicMap.get(sendMap.get("eventType"))!=null){
                status = CTGMqTool.send(seq, "10802", JSON.toJSON(sendMap).toString(),ResourceUtil.eventTopicMap.get(sendMap.get("eventType")).toString());
            }else{
                status = CTGMqTool.send(seq, "10802", JSON.toJSON(sendMap).toString(),"");
            }

            logger.info("发送消息ID:" + orderMap.get("templateOrderId") + ",发送ctg-mq内容:" + JSON.toJSON(sendMap).toString());
            // if ("2".equals(status.getCode())) {//发送成功
            if (status == null || status != STATUS.SENDOK) {
                orderMap.put("state", "10D");//10D发送失败
            } else {
                orderMap.put("state", "10C");//10C发送成功
                logger.info("============发送MQ消息成功============");
            }
            this.updateOrderState(orderMap);
            result.put("status", 0);
            result.put("message", "处理成功");
        } catch (MQException ex) {
            orderMap.put("state", "10D");//mq异常
            logger.error("工单ID：" + orderMap.get("templateOrderId") + "发送mq异常，异常信息" + ex.getMessage());
            this.updateOrderState(orderMap);
        } catch (NullPointerException npe) {
            orderMap.put("state", "10D");//20E 未发送前的效验出错
            logger.error("工单ID：" + orderMap.get("templateOrderId") + "发送失败。" + "异常信息为：" + npe.getMessage());
            this.updateOrderState(orderMap);
        } catch (Exception e) {
            orderMap.put("state", "10D");//其他异常
            logger.error("工单ID：" + orderMap.get("templateOrderId") + "发送消息异常，异常信息" + e.getMessage());
            this.updateOrderState(orderMap);
            e.printStackTrace();
        }
    }

    /**
     * @param errorMsg
     * @return
     */
    public String setErrorMsg(String errorMsg) {
        if (errorMsg != null && !"".equals(errorMsg)) {

            return (errorMsg.length() > 512 ? errorMsg.substring(0, 512):errorMsg);
        }
        return errorMsg;
    }

    public List queryOrderList(Map map) {
        return orderMapper.queryOrderList(map);
    }

    public int updateOrderState(Map map) {
        return orderMapper.updateOrderState(map);
    }

    public int addOrder(Map map) {
        return orderMapper.addOrder(map);
    }

    /**
     * 加载事件Map到缓存中
     */
    public void initEventMapping() {
        List<Map<String, Object>> maps = eventMappingMapper.initEventMapping();
        for (Map map : maps) {
            ResourceUtil.eventMappingMap.put(map.get("outEventType"), map.get("eventType"));
            if(!StringUtil.isEmpty(map.get("topic"))){
                ResourceUtil.eventTopicMap.put(map.get("eventType"),map.get("topic"));
            }
        }
    }

    /**
     * 加载模板到缓存中
     */
    public void initEventTemplate() {
        List<Map<String, Object>> maps = eventTemplateMapper.initEventTemplate();
        for (Map map : maps) {
            ResourceUtil.eventTemplateMap.put(map.get("eventType"), map.get("template"));
            if(!StringUtil.isEmpty(map.get("topic"))){
                ResourceUtil.eventTopicMap.put(map.get("eventType"),map.get("topic"));
            }
        }
    }
    public String getSF() {
        String sf = "";
        if (bundle.containsKey("sf")) {
            sf = bundle.getString("sf");
        }
        return sf;
    }
}