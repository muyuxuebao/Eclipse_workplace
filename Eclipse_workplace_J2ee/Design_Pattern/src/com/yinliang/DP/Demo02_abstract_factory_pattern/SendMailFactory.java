package com.yinliang.DP.Demo02_abstract_factory_pattern;

public class SendMailFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}

}
