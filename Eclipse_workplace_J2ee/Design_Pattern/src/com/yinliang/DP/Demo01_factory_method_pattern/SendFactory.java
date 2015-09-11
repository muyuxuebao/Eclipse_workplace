package com.yinliang.DP.Demo01_factory_method_pattern;

public class SendFactory {
	public static Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			System.out.println("请输出正确的类型");
			return null;
		}
	}
}
