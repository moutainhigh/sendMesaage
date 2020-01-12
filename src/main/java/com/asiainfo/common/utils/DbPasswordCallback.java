package com.asiainfo.common.utils;

import com.alibaba.druid.util.DruidPasswordCallback;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DbPasswordCallback extends DruidPasswordCallback {
    private static Logger logger = Logger.getLogger(DbPasswordCallback.class);
    private String passwd="bss@2018";
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String pwd = properties.getProperty("password");
        if (StringUtils.isNotBlank(pwd)) {
            try {
                String password = PasswordUtil.decrypt(pwd, passwd);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                logger.error("密码解密失败！");
//                setPassword(pwd.toCharArray());
            }
        }
    }
}
