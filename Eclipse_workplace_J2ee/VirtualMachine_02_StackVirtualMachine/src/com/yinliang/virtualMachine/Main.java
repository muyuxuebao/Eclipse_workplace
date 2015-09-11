package com.yinliang.virtualMachine;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Instruct> instructList = new ArrayList<Instruct>();
		instructList.add(new Instruct(InstructEnum.INPUT, 0)); // 读取数据1
		instructList.add(new Instruct(InstructEnum.INPUT, 1)); // 读取数据2
		instructList.add(new Instruct(InstructEnum.PUSH, 0)); // 将数据1压栈
		instructList.add(new Instruct(InstructEnum.PUSH, 1)); // 将数据2压栈
		instructList.add(new Instruct(InstructEnum.JMP_BIGGER, 3)); // 如果数据1大于数据2，则前进3步
		instructList.add(new Instruct(InstructEnum.PUSH, 1)); // 如果数据1不大于数据2，则将数据2压栈
		instructList.add(new Instruct(InstructEnum.JMP, 2)); // 前进2步
		instructList.add(new Instruct(InstructEnum.PUSH, 0)); // 将数据1压栈，承接上面的前进3步
		instructList.add(new Instruct(InstructEnum.OUTPUT)); // 将较大的数输出

		VirtualMachine virtualMachine = new VirtualMachine(instructList);
	}
}
