package com.yinliang.virtualMachine;

public enum InstructEnum {
	PUSH, // 入栈
	POP, // 出栈
	TOP, // 栈顶
	INPUT, // 输入
	OUTPUT, // 输出
	JMP, // 跳转
	JMP_TRUE, // 为真跳转
	JMP_FALSE, // 为假跳转
	JMP_EQUAL, // 等于跳转
	JMP_NOT_EQUAL, // 不等跳转
	JMP_BIGGER, // 大于跳转
	JMP_SMALLER // 小于跳转
}