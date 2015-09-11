package com.ITS.management.message;

import com.ITS.management.SystemConstant;

public class ImportCertMessage extends Message{

	@Override
	public String wrapExtra() {
		// TODO Auto-generated method stub
		
		return (String) extra;
	}

	@Override
	public void unwrapExtra(String extraStr) {
		// TODO Auto-generated method stub
		
		extra = extraStr;
		return;
	}

	@Override
	public String wrapMessage() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getClass().getName());
		sb.append(SystemConstant.DIVEIDER);
		sb.append(SystemConstant.ACTION_IMPORT_CERT);
		sb.append(SystemConstant.DIVEIDER);
		sb.append(SystemConstant.TYPE_FILE);
		sb.append(SystemConstant.DIVEIDER);
		
		String extraStr = wrapExtra();
		sb.append(extraStr);
		
		return sb.toString();
	}

}
