package com.yinliang.virtualMachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VirtualMachine extends AbstractVirtualMachine {
	int m_memory[] = new int[100]; // 内存
	int m_instruction_counter; // 指令计数器
	int m_instruction_register; // 指令寄存器
	int m_opcode; // 操作码寄存器
	int m_address; // 内存地址寄存器
	int m_accumulator; // 累加寄存器

	public VirtualMachine() {
		this.m_instruction_counter = 0;
		this.m_instruction_register = 0;
		this.m_opcode = 0;
		this.m_address = 0;
	}

	@Override
	public void boot() {
		System.out.println("Virtual machine is booting...");
		System.out.println("Complete booting.\n");
	}

	@Override
	public void shut() {
		System.out.println("Virtual machine is shutting...");
		System.out.println("Complete shutting.\n");
	}

	@Override
	public void load_program(String filePath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.length() == 4) {
					int d = Integer.parseInt(line.trim());
					this.m_memory[i++] = d;
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void print_program() {
		System.out.println("Virtual machine is printing the program...");
		for (int i = 0; (i < this.m_memory.length) && ((this.m_memory[i] / 100) != const_halt); i++) {
			System.out.println(String.format("command: %20s, data: %2d", op_Map.get(this.m_memory[i] / 100), this.m_memory[i] % 100));
		}
	}

	@Override
	public void run_program() {
		System.out.println("Virtual machine is running the program...");

		// 取出指令
		this.m_instruction_counter = 0;
		this.m_instruction_register = this.m_memory[this.m_instruction_counter];

		// 指令解码
		this.m_opcode = this.m_instruction_register / 100;
		this.m_address = this.m_instruction_register % 100;

		while (this.m_opcode != const_halt) {

			System.out.println("************************************************************************");
			System.out.println(String.format("地址:%-4d指令:%-20s数据: %02d", this.m_instruction_counter, op_Map.get(this.m_opcode), this.m_address));

			this.m_instruction_counter++; // 移动到下一条指令

			switch (this.m_opcode) {
			case const_input:
				int buffer;
				Scanner scanner = new Scanner(System.in);
				buffer = scanner.nextInt();
				this.m_memory[this.m_address] = buffer;
				break;

			case const_print:
				System.out.println(this.m_memory[this.m_address]);
				break;

			case const_load:
				this.m_accumulator = this.m_memory[this.m_address];
				break;

			case const_store:
				this.m_memory[this.m_address] = this.m_accumulator;
				break;

			case const_plus:
				this.m_accumulator += this.m_memory[this.m_address];
				break;

			case const_minus:
				this.m_accumulator -= this.m_memory[this.m_address];
				break;

			case const_multiply:
				this.m_accumulator *= this.m_memory[this.m_address];
				break;

			case const_divde:
				this.m_accumulator /= this.m_memory[this.m_address];
				break;

			case const_branch:
				this.m_instruction_counter = this.m_address;
				break;

			case const_branch_below:
				if (this.m_accumulator < 0) {
					this.m_instruction_counter = this.m_address;
				}
				break;

			case const_branch_zero:
				if (this.m_accumulator == 0) {
					this.m_instruction_counter = this.m_address;
				}
				break;

			default:
				break;
			}

			// 取指令
			this.m_instruction_register = this.m_memory[this.m_instruction_counter];
			this.m_opcode = this.m_instruction_register / 100;
			this.m_address = this.m_instruction_register % 100;
		}
	}

	public static void main(String[] args) {
		VirtualMachine virtualMachine = new VirtualMachine();
		virtualMachine.load_program("program.txt");
		// virtualMachine.print_program();
		virtualMachine.run_program();
	}

}
