package com.yinliang.DP.Demo18_Command;

public class MyCommand implements Command {

	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	private Receiver receiver = null;

	@Override
	public void exe() {
		// TODO Auto-generated method stub
		this.receiver.action();
	}

}
