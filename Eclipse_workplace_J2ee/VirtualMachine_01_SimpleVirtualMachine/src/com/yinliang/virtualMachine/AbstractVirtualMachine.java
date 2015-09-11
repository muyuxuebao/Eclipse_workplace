package com.yinliang.virtualMachine;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVirtualMachine {

	// 虚拟机操作码
	static final int const_input = 1; // 操作码：01，存放用户输入的数据到内存
	static final int const_print = 2; // 操作码：02，输出内存中的数据到屏幕
	static final int const_load = 3; // 操作码：03，加载内存数据到累加寄存器
	static final int const_store = 4; // 操作码：04，存放累加寄存器到内存
	static final int const_plus = 5; // 操作码：05，累加寄存器数据加上内存数据
	static final int const_minus = 6; // 操作码：06，累加寄存器数据减去内存数据
	static final int const_multiply = 7; // 操作码：07，累加寄存器数据乘以内存数据
	static final int const_divde = 8; // 操作码：08，累加寄存器数据除以内存数据
	static final int const_branch = 9; // 操作码：09，转移
	static final int const_branch_below = 10; // 操作码：10，累加寄存器数据小于0，则转移
	static final int const_branch_zero = 11; // 操作码：11，累加寄存器数据等于0，则转移
	static final int const_halt = 12; // 操作码：12，终止

	static final Map<Integer, String> op_Map = new HashMap<Integer, String>();

	static {
		op_Map.put(const_input, "const_input");
		op_Map.put(const_print, "const_print");
		op_Map.put(const_load, "const_load");
		op_Map.put(const_store, "const_store");
		op_Map.put(const_plus, "const_plus");
		op_Map.put(const_minus, "const_minus");
		op_Map.put(const_multiply, "const_multiply");
		op_Map.put(const_divde, "const_divde");
		op_Map.put(const_branch, "const_branch");
		op_Map.put(const_branch_below, "const_branch_below");
		op_Map.put(const_branch_zero, "const_branch_zero");
		op_Map.put(const_halt, "const_halt");
	}

	public abstract void boot(); // 启动虚拟机

	public abstract void shut(); // 关闭虚拟机

	public abstract void load_program(String filePath); // 加载程序到虚拟机内存

	public abstract void print_program(); // 打印程序

	public abstract void run_program(); // 运行程序
}
