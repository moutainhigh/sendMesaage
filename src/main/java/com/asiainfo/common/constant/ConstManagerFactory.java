package com.asiainfo.common.constant;

/**
 * 常量构造工厂
 *
 */
public class ConstManagerFactory {
	public static ConstManager getConstManagerImpl(){
		return ConstManagerImpl.getInstance();
	}
}
