package Register;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener, KeyListener, FocusListener {

	private static final long serialVersionUID = 20140404; //注册组件

	JLabel lbl_name, lbl_password, lbl_repassword, lbl_realName, lbl_sex;
	JLabel lbl_phone, lbl_email, lbl_vocation, lbl_city;//地址之类的标签 
	JLabel lbl_name_tishi, lbl_password_tishi, lbl_repassword_tishi;//提示标签
	JTextField text_name, text_realName, text_phone, text_email, text_vocation, text_city;// 输 入 文 本 框
	JPasswordField password, repassword;//密码的输入文本框 
	CheckboxGroup group;
	Checkbox sex_m;
	Checkbox sex_w;
	JButton button_submit, button_exit;//按钮
	JPanel jPanel;//装载容器

	public Register() {
		super("用户注册");
		//标签组建的初始化
		//用户基本信息
		lbl_name = new JLabel("用 户");
		lbl_password = new JLabel("密 码");
		lbl_repassword = new JLabel("确认密码:");
		lbl_realName = new JLabel("真实姓名:");
		lbl_sex = new JLabel("性 别:");
		lbl_phone = new JLabel("电话号码:");
		lbl_email = new JLabel("E—mail:");
		lbl_vocation = new JLabel("职 位:");
		lbl_city = new JLabel("住 址:");
		//提示信息
		lbl_name = new JLabel("请输入您的真实姓名!");
		lbl_password = new JLabel("请输入密码!");
		lbl_repassword = new JLabel("两次输入不一样,请重新输入!");
		//输入框组件的初始化 //基本信息输入框
		text_name = new JTextField();
		password = new JPasswordField();
		repassword = new JPasswordField();
		text_realName = new JTextField();
		text_phone = new JTextField();
		text_email = new JTextField();
		text_vocation = new JTextField();
		text_city = new JTextField(); //性别组件
		group = new CheckboxGroup();
		sex_m = new Checkbox("男", group, true);
		sex_w = new Checkbox("女", group, false);
		//按钮组件的初始化
		button_submit = new JButton("提交");
		button_exit = new JButton("取消");
		Font font = new Font("Serif", Font.BOLD, 18); //向容器添加组件
		jPanel = new JPanel();//创建容器 jPanel.setLayout(null); lbl_name.setBounds(50, 50,90,20); lbl_password.setBounds(50,80,90,20); lbl_repassword.setBounds(50,110,90,20); lbl_sex.setBounds(50,140,90,20); lbl_realName.setBounds(50,170,100, 20); lbl_phone.setBounds(50, 230, 90, 20); lbl_email.setBounds(50, 260, 90, 20); lbl_vocation.setBounds(50, 290, 90, 20); lbl_city.setBounds(50,320,90,20);
		text_name.setBounds(150, 50, 200, 20);
		password.setBounds(150, 80, 200, 20);
		repassword.setBounds(150, 110, 200, 20);
		sex_m.setBounds(170, 140, 40, 20);//性别选择框 sex_w.setBounds(270, 140,40,20); text_realName.setBounds(150,170,200,20); text_phone.setBounds(150, 230, 200, 20); text_email.setBounds(150,260,200,20); text_vocation.setBounds(150,290,200,20); text_city.setBounds(150,320,200,20); button_submit.setBounds(150,370,90,30); button_exit.setBounds(300,370,90,30);
		lbl_name_tishi.setBounds(360, 50, 200, 20);
		lbl_password_tishi.setBounds(360, 80, 200, 20);
		lbl_repassword_tishi.setBounds(360, 110, 200, 20);
		lbl_name.setFont(font);
		lbl_password.setFont(font);
		lbl_repassword.setFont(font);
		lbl_sex.setFont(font);
		lbl_realName.setFont(font);
		lbl_vocation.setFont(font);
		lbl_phone.setFont(font);
		lbl_city.setFont(font);
		lbl_email.setFont(font);
		jPanel.add(lbl_name);
		jPanel.add(lbl_password);
		jPanel.add(lbl_repassword);
		jPanel.add(lbl_sex);
		jPanel.add(lbl_realName);
		jPanel.add(lbl_vocation);
		jPanel.add(lbl_phone);
		jPanel.add(lbl_email);
		jPanel.add(lbl_city);
		jPanel.add(text_name);
		jPanel.add(password);
		jPanel.add(repassword);
		jPanel.add(sex_m);
		jPanel.add(sex_w);
		jPanel.add(text_realName);
		jPanel.add(text_phone);
		jPanel.add(text_email);
		jPanel.add(text_vocation);
		jPanel.add(text_city);
		jPanel.add(button_submit);
		jPanel.add(button_exit);
		jPanel.add(lbl_name_tishi);
		jPanel.add(lbl_password_tishi);
		jPanel.add(lbl_repassword_tishi);
		add(jPanel);
		button_exit.addActionListener(this);
		button_submit.addActionListener(this);
		text_name.addFocusListener(this);
		password.addFocusListener(this);
		repassword.addFocusListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				new Login();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnstring = e.getActionCommand();
		if (btnstring.equals("提交")) {
			String username = text_name.getText().trim();
			String pass = String.valueOf(password.getPassword());
			String repass = String.valueOf(repassword.getPassword());
			String sex = group.getSelectedCheckbox().getLabel();
			String realName = text_realName.getText();
			String phone = text_phone.getText();
			String email = text_email.getText();
			String vocation = text_vocation.getText();
			String city = text_city.getText();
			if (!username.equals("") && !pass.equals("") && pass.equals(repass))
				System.out.println("提交");
			String sql = "insert into jdbctest values(" + 0 + ",'" + username + "'," + "'" + pass + "'," + "'" + realName + "'," + "'" + sex + "'," + "'" + phone + "'," + "'" + email + "'," + "'" + vocation + "'," + "'" + city + "'" + ");";
			System.out.println(sql);
			UserDAO userdao = new UserDAO();
			boolean flag = userdao.addUser(sql);
			if (flag) {
				JOptionPane.showMessageDialog(this, "注册成功!");
				this.dispose();
				new Login();
			} else {
				System.out.println("取消");
				this.dispose();
				new Login();
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		String nameString = text_name.getText().trim();
		String pass = String.valueOf(password.getPassword());
		String repass = String.valueOf(repassword.getPassword());
		if (e.getSource() == text_name) {
			if (nameString.equals("")) {
				System.out.println("用户名为空");
				lbl_name_tishi.setText("唉!用户名不能为空!");
				lbl_name_tishi.setBounds(360, 50, 200, 20);
				lbl_name_tishi.setForeground(Color.red);
			} else {
				lbl_name_tishi.setText("设置成功");
				lbl_name_tishi.setBounds(360, 50, 200, 20);
				lbl_name_tishi.setForeground(Color.green);
			}
		}
		if (e.getSource() == password) {
			if (pass.equals("")) {
				System.out.println("密码为空");
				lbl_password_tishi.setText("唉!密码不能为空!");
				lbl_password_tishi.setBounds(360, 80, 200, 20);
				lbl_password_tishi.setForeground(Color.red);
			} else {
				lbl_password_tishi.setText("设置成功");
				lbl_password_tishi.setBounds(360, 80, 200, 20);
				lbl_password_tishi.setForeground(Color.green);
			}
		}
		if (e.getSource() == repassword) {
			if (!repass.equals(pass) || repass.equals("")) {
				System.out.println("唉!两次密码不一致,请重新输入");
				lbl_repassword_tishi.setText("唉!两次不一样!");
				lbl_repassword_tishi.setBounds(360, 110, 200, 20);
				lbl_repassword_tishi.setForeground(Color.red);
			} else {
				lbl_repassword_tishi.setText("设置成功");
				lbl_repassword_tishi.setBounds(360, 110, 200, 20);
				lbl_repassword_tishi.setForeground(Color.green);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
