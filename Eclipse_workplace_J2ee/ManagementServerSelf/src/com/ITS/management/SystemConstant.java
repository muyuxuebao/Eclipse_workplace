package com.ITS.management;

public class SystemConstant {

	public static final String DIVEIDER = " ";
	public static final String KEY_VALUE_DIVIDER = ":";
	public static final String ARRAYLIST_DIVIDER = ";";
	
	public static final int ACTION_CREATE_CERT = 0;
	public static final int ACTION_IMPORT_CERT = 1;
	public static final int ACTION_CREATE_USER = 2;
	public static final int ACTION_RECORD_CERT= 3;
	
	public static final int TYPE_VOID = 0;
	public static final int TYPE_STRING = 1;
	public static final int TYPE_BOOLEAN = 2;
	public static final int TYPE_FILE = 3;
	
	public static final String CERT_PASSWORD = "123456";
	public static final String ROOT_CERT_NAME = "ITSRoot";
	
	public static final int EXPIRE_TYPE_YEAR = 0;
	public static final int EXPIRE_TYPE_MONTH = 1;
	public static final int EXPIRE_TYPE_DAY = 2;
	public static final int EXPIRE_TYPE_DEFAULT = EXPIRE_TYPE_MONTH;
}
