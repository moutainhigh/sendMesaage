package com.asiainfo.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.common.utils.StringUtil;
import com.asiainfo.order.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("orderController")
public class OrderController {
    private static Logger logger = Logger.getLogger(OrderController.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private OrderService orderService;

    public void scanTable(String[] args) {
        Date date = new Date();
        logger.info("----------定时任务执行---------------" + sdf.format(date).toString());
        JSONObject result = new JSONObject();
        result.put("status", -1);
        Map map = new HashMap();
        map.put("state", "10A");//10A初始状态
        if(!StringUtil.isEmpty(orderService.getSF())){
            map.put("sf",orderService.getSF());
        }
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    if(!StringUtil.isEmpty(args[0])) {
                        map.put("mod", args[0]);
                    }
                } else if (i == 1) {
                    if(!StringUtil.isEmpty(args[1])) {
                        map.put("modValue", args[1]);
                    }
                } else if (i == 2) {
                    if(!StringUtil.isEmpty(args[2])){
                        map.put("state", args[2]);
                    }
                }else if(i == 3){
                    if(!StringUtil.isEmpty(args[3])){
                        String tmp= args[3];
                        if(tmp.startsWith("(")){
                            map.put("remindCodeFlag", "in");
                            map.put("remindCode", args[3].substring(1,args[3].length()-1));
                        }else if(tmp.startsWith("!")){
                            map.put("remindCodeFlag", "notin");
                            map.put("remindCode", args[3].substring(2,args[3].length()-1));
                        }else{
                            map.put("remindCode", args[3]);
                        }
                    }
                }
            }
        }

        List<Map<String, Object>> list = orderService.queryOrderList(map);

        for (Map orderMap : list) {
            orderService.sendMsg(orderMap, result);
        }
        orderService.check();
        logger.info("----------定时任务执行完成---------------");
    }

    /**
     * 加载模板数据到缓存中
     */
    @ResponseBody
    @RequestMapping("/initTemplate")
    public String initTemplate() {
        JSONObject result = new JSONObject();
        orderService.initEventMapping();
        orderService.initEventTemplate();
        result.put("status", 0);
        result.put("message", "短信模板缓存更新成功");
        return result.toString();
    }
}
