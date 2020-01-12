package com.asiainfo.common.utils;

import org.safehaus.uuid.UUID;
import org.safehaus.uuid.UUIDGenerator;

public class UUIDTool {

	public static String generate() {
		UUIDGenerator generator = UUIDGenerator.getInstance();
		UUID uuid = generator.generateTimeBasedUUID();
		return uuid.toString().replaceAll("-","");
	}

	public static void main(String[] args) {
		System.out.println(UUIDTool.generate());
	}
}