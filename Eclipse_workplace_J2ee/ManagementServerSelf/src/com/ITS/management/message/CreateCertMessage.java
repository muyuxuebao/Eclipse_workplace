package com.ITS.management.message;

import java.util.ArrayList;

import com.ITS.management.SystemConstant;
import com.ITS.management.bean.KeyValueCell;

public class CreateCertMessage extends Message{

	@Override
	public String wrapMessage() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getClass().getName());
		sb.append(SystemConstant.DIVEIDER);
		sb.append(SystemConstant.ACTION_CREATE_CERT);
		sb.append(SystemConstant.DIVEIDER);
		sb.append(SystemConstant.TYPE_BOOLEAN);
		sb.append(SystemConstant.DIVEIDER);
		
		String extraStr = wrapExtra();
		sb.append(extraStr);
		
		return sb.toString();
	}

	@Override
	public String wrapExtra() {
		// TODO Auto-generated method stub
		if(extra == null)
		{
			return null;
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<KeyValueCell> list = (ArrayList<KeyValueCell>) extra;
		
		StringBuilder sb = null;
		for(KeyValueCell cell: list)
		{
			if(sb == null)
			{
				sb = new StringBuilder("");
			}
			else
			{
				sb.append(SystemConstant.ARRAYLIST_DIVIDER);
			}
			
			sb.append(cell.getContent());
		}
		
		if(sb == null)
		{
			return null;
		}
		else
		{
			return sb.toString();
		}	
	}

	@Override
	public void unwrapExtra(String extraStr) {
		// TODO Auto-generated method stub
		
		if(extraStr == null)
		{
			return;
		}
		
		String[] tempList = extraStr.split(SystemConstant.ARRAYLIST_DIVIDER);
		String[] tempInfo;
		
		KeyValueCell cell = null;
		ArrayList<KeyValueCell> list = new ArrayList<KeyValueCell>();
		
		for(String cellStr: tempList)
		{
			tempInfo = cellStr.split(SystemConstant.KEY_VALUE_DIVIDER);
			
			cell = new KeyValueCell(tempInfo[0], tempInfo[1]);
			list.add(cell);
		}
		
		this.extra = list;

	}
	
}
