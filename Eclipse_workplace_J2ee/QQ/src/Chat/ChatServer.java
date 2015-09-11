package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	static boolean started = false;
	static boolean bconnected = false;
	static ServerSocket ss = null;
	static Socket s = null;
	ArrayList<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new ChatServer().start();
	}

	public void start() {
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e) {
			System.out.println("端口使用中.......");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (started) {
				s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connect");
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;

		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			bconnected = true;
		}

		public void send(String str) {
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (NullPointerException e) {
				System.out.println("对方已经关闭流!");
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("对方退出了,我从 List 中删除了!");
			}
		}

		@Override
		public void run() {
			try {
				while (bconnected) {
					String str = dis.readUTF();
					System.out.println(str);
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);
					}
				}
			} catch (EOFException e) {
				System.out.println("client closed!");
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("对方退出了,我从 List 中删除了!");
			} finally {
				try {
					if (s != null)
						s.close();
					if (dis != null)
						dis.close();
					if (dos != null) {
						dos.close();
						dos = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
