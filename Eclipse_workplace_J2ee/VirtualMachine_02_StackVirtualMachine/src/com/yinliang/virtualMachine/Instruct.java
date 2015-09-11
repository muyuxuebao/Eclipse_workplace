package com.yinliang.virtualMachine;

public class Instruct {
	InstructEnum insCode;
	int operand;

	public Instruct(InstructEnum code, int operand) {
		super();
		this.insCode = code;
		this.operand = operand;
	}

	public Instruct(InstructEnum code) {
		super();
		this.insCode = code;
	}


}
