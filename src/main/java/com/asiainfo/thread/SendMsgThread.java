package com.asiainfo.thread;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.common.SpringContextHolder;
import com.asiainfo.order.service.OrderService;

import java.util.Map;
import java.util.concurrent.Callable;

public class SendMsgThread implements Callable<Boolean> {


    private Map orderMap;
    private JSONObject result;

    public SendMsgThread(Map orderMap,JSONObject result){
        this.orderMap=orderMap;
        this.result=result;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Boolean call() throws Exception {
        OrderService orderService = (OrderService) SpringContextHolder.getBean("orderService");
        orderService.scanMqMsg(orderMap,new JSONObject());
        return true;
    }



}