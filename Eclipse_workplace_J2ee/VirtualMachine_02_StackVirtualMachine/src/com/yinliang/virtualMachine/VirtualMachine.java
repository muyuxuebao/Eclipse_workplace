package com.yinliang.virtualMachine;

import java.util.List;
import java.util.Stack;

public class VirtualMachine {
	private List<Instruct> instructList;
	Stack<Data> dataStack;

	public VirtualMachine(List<Instruct> instructList2) {
		this.instructList = instructList2;
	}

	public void run() {
		int ip = 0; // 指令索引
		int ipc = 1; // 指令前移量
		Data a, b;

		while (ip < this.instructList.size()) {
			switch (this.instructList.get(ip).insCode) {
			case PUSH:
				break;

			case POP:

				break;
			case TOP:

				break;
			case INPUT:

				break;
			case OUTPUT:

				break;
			case JMP:

				break;
			case JMP_TRUE:

				break;
			case JMP_FALSE:

				break;
			case JMP_EQUAL:

				break;
			case JMP_NOT_EQUAL:

				break;
			case JMP_BIGGER:

				break;
			case JMP_SMALLER:

				break;

			default:
				break;
			}
		}
	}

	// 装在指令序列，其功能是：输入两个数，将其比较，输出较大的
	public void LoadInstruct_TestBigger(List<Instruct> instructlList) {

	}

	// 重置
	void reset() {

	}
}
