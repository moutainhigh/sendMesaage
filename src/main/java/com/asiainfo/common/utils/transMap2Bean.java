package com.asiainfo.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class transMap2Bean {
    public static void copyBean(Object String, Object Map) {
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (value == null) {
                    return null;
                }
                String str = (String) value;
                if (str.trim().equals("")) {
                    return null;
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    return df.parse(str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);
        try {
            BeanUtils.copyProperties(String, Map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
