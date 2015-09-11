package Register;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Chat.ChatClient;

public class Login {
	boolean f = false;//按登录时设置的一个标志
	private JFrame frame = new JFrame("QQ 登录窗口");
	private JButton submit = new JButton("登陆");
	private JButton reset = new JButton("重置");
	private JButton register = new JButton("立即注册");
	private JLabel nameLab = new JLabel("用户名:");
	private JLabel passLab = new JLabel("密 码:");
	private JLabel infoLab = new JLabel("用户登陆系统");
	private JTextField nameText = new JTextField(10);
	private JPasswordField passText = new JPasswordField();

	public Login() {
		Font fnt = new Font("Serief", Font.ITALIC + Font.BOLD, 12);
		infoLab.setFont(fnt);
		submit.addActionListener(new ActionListener() { //采用内部匿名类
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == submit) { //判断触发器源是否是提交按钮
					String tname = nameText.getText().trim(); //得到输入的用户名
					String temppass = new String(passText.getPassword());//得到 输入的密码,此时通过 getPassageword()方法返回的是字符数组
					String tpass = temppass.trim();
					LoginCheck log = new LoginCheck(tname, tpass);// 实 例 化 LoginCheck 对象,传入输入的用户名和密码
					if (log.validate()) {//对用户名和密码进行验证 
						try {
							Thread.sleep(2000); //2 秒后打开聊天窗口
							f = true; //登录成功后的表示项为 true 
							new ChatClient(tname);
							System.out.println("登录成功");
							frame.dispose(); //关闭本窗口
						} catch (Exception ee) {//异常获取
						}
					} else {
						infoLab.setText("登陆失败,错误的用户名或密码!");// 
					}
				}
			}
		});
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == register) {
					frame.dispose();
					Register register = new Register();
					register.setSize(600, 470);
					register.setLocationRelativeTo(null);
					register.setVisible(true);
					register.setResizable(false);
				}
			}
		});
		reset.addActionListener(new ActionListener() { //采用内部匿名类
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == reset) { //判断触发器源是否是提交按钮
					nameText.setText("");//设置文本框中的内容 passText.setText("") ;//设置文本框中的内容 infoLab.setText("用户登陆系统") ;//恢复标签显示
				}
			}
		});
		frame.addWindowListener(new WindowAdapter() {//加入窗口监听
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setLayout(null);//使用绝对定位 
		nameLab.setBounds(5, 5, 60, 20);
		passLab.setBounds(5, 30, 60, 20);
		infoLab.setBounds(5, 65, 220, 30);
		nameText.setBounds(65, 5, 100, 20);
		passText.setBounds(65, 30, 100, 20);
		submit.setBounds(165, 5, 60, 20);
		reset.setBounds(165, 30, 60, 20);
		register.setBounds(80, 95, 90, 20);
		frame.add(nameLab);
		frame.add(passLab);
		frame.add(infoLab);
		frame.add(nameText);
		frame.add(passText);
		frame.add(submit);
		frame.add(reset);
		frame.add(register);
		frame.setSize(280, 160);
		frame.getContentPane().setBackground(Color.pink);
		frame.setResizable(false);
		frame.setLocation(600, 200);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		new Login();//登录实现 
	}
}