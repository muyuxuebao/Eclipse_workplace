package Chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatClient extends JFrame {
	private static final long serialVersionUID = 20140405L;

	// 设置窗体的背景图片
	class MyPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("I:\\background.jpg");
			Dimension size = getParent().getSize();
			g.drawImage(icon.getImage(), 0, 0, size.width, size.height, null);
		}
	}

	protected JLabel labtime;
	static TextField sendtext = new TextField();
	static JTextArea accepttext = new JTextArea();
	JLabel lb = new JLabel();
	JLabel lb1 = new JLabel();
	JLabel lb2 = new JLabel();
	JLabel lb3 = new JLabel();
	JButton brecord = new JButton();
	JButton bclose = new JButton();
	JButton bsend = new JButton();
	JScrollPane scr = new JScrollPane(accepttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	String username; // 用户名
	static Socket s = null;
	static DataOutputStream dos = null;
	static DataInputStream dis = null;
	private static boolean bConnected = false;
	private static Thread tRecv = null;

	public ChatClient(final String username) {
		super("QQ 聊天窗口");
		this.username = username;
		System.out.println("username:----" + username);
		sendtext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = username + ": " + labtime.getText() + "\n" + sendtext.getText().trim();
				sendtext.setText("");
				try {
					dos.writeUTF(str);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnected();
				System.exit(0);
			}
		});
		launchFrame();
		start();
	}

	class OtherClient implements Runnable {
		@Override
		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
					accepttext.setText(accepttext.getText() + str + '\n');
				}
			} catch (EOFException e) {
				System.out.println("退出了,byte!");
			} catch (SocketException e) {
				System.out.println("退出了,byte!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		OtherClient oc = new OtherClient();
		tRecv = new Thread(oc);
		tRecv.start();
	}

	public void launchFrame() {
		Font fnt = new Font("Serief", Font.BOLD, 16);// 设置字体 lb.setFont(fnt);
		lb.setForeground(Color.pink);
		lb1.setFont(fnt);
		lb2.setFont(fnt);
		lb3.setFont(fnt);
		lb1.setForeground(Color.red);
		lb2.setForeground(Color.yellow);
		lb3.setForeground(Color.yellow);
		bclose.setFont(fnt);
		bsend.setFont(fnt);
		sendtext.setFont(fnt);
		accepttext.setFont(fnt);
		accepttext.setEditable(false);// 文本不可编辑
		accepttext.setLineWrap(true);// 自动换行
		lb.setText("与好友聊天中");
		lb1.setText("请输入聊天内容: ");
		lb2.setText("当前时间: ");
		DateFormat df = DateFormat.getDateTimeInstance();// 取得系统时间
		String df2 = df.format(new Date());
		lb3.setText(df2);
		labtime = lb3;
		new Time(lb3); // 使时间动态显示
		brecord.setText("聊天记录");
		bclose.setText("关闭");
		bsend.setText("发送");
		JPanel panel = new MyPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		lb.setBounds(30, 15, 120, 20);
		lb1.setBounds(30, 300, 150, 20);
		lb2.setBounds(230, 15, 90, 20);
		lb3.setBounds(320, 15, 200, 20);
		brecord.setBounds(380, 285, 90, 20);
		sendtext.setBounds(30, 320, 440, 30);
		bclose.setBounds(150, 370, 70, 30);
		// 将时间转换成字
		bsend.setBounds(250, 370, 70, 30);
		scr.setBounds(30, 40, 440, 240);
		panel.add(lb);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(brecord);
		panel.add(bclose);
		panel.add(bsend);
		panel.add(sendtext);
		panel.add(scr);
		bclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disconnected();
				System.exit(0);
			}
		});
		bsend.addActionListener(new ActionListener() {
			// 采用内部匿名类
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = username + ": " + labtime.getText() + "\n" + sendtext.getText().trim();
				sendtext.setText("");
				try {
					dos.writeUTF(str);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.setLocation(400, 300);
		this.setSize(520, 450);
		setResizable(false);// 最大化失效
		//
		getContentPane().setBackground(Color.pink);
		setVisible(true);
		connect();
	}

	public void connect() {
		try {
			s = new Socket(InetAddress.getLocalHost(), 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
			System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			System.out.println("服务器未启动,请先启动服务器");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void disconnected() {// 释放资源代码很重要
		try {
			bConnected = false;
			tRecv.join(500);
		} catch (

		InterruptedException e)

		{
			e.printStackTrace();
		} finally {
		}

		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e)

		{
			e.printStackTrace();
		}

	}

}
