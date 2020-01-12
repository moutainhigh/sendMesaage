package com.asiainfo.order.controller;

import com.asiainfo.common.utils.DateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.order.service.OrderService;
import com.ctg.mq.api.exception.MQException;
import com.ctgMq.tool.CTGMqTool;
import com.ctgMq.tool.STATUS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("testOrderController")
public class TestOrderController {
    private static Logger logger = Logger.getLogger(TestOrderController.class);


    @Autowired
    private OrderService orderService;


    public String getData(){
        JSONObject result=new JSONObject();
        result.put("status",-1);
        Map map=new HashMap();
        List<Map<String,Object>> list=null;

        map.put("state","10A");//10A初始状态
        list=orderService.queryOrderList(map);
        for(Map orderMap:list){
            try {
                orderMap.put("state", "10B");//10B处理中
                orderService.updateOrderState(orderMap);
                String time = DateUtils.dateString(new Date(), DateUtils.yyyyMMddTIME);
                String seq = time + (int) (Math.random() * 1000 * 1000 * 1000);
                STATUS status = CTGMqTool.send(seq, "10802", JSON.toJSON(orderMap).toString());
                if ("2".equals(status.getCode())) {//发送成功
                    orderMap.put("state", "10C");//10C发送成功
                } else {
                    orderMap.put("state", "10D");//10D发送失败
                }
                orderService.updateOrderState(orderMap);
                result.put("status", 0);
                result.put("message", "处理成功");
            }catch (MQException ex){
                orderMap.put("state", "10E");//mq异常
                logger.error("工单ID："+orderMap.get("templateOrderId")+"发送mq异常，异常信息"+ex.getMessage());
                orderService.updateOrderState(orderMap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result.toString();
    }



    @ResponseBody
    @RequestMapping("/saveData")
    public String saveData(HttpServletRequest req,  @RequestBody String requestBody){
        JSONObject result=new JSONObject();
        result.put("status",-1);
        try {
            Map map = JSON.parseObject(requestBody, Map.class);
            int i=orderService.addOrder(map);
            result.put("status",0);
            result.put("message","处理成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }


}
