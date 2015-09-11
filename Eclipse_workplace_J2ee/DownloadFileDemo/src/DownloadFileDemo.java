import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.http.HTTPException;

public class DownloadFileDemo {

	static final String DOWNLOAD_FILE_NAME = "RELEASE-NOTES.txt";
	static final String PROCESS_FILE_TEMPLATE = "%s_%d.txt";

	static final int THREAD_COUNT = 3;

	public static void main(String[] args) throws FileNotFoundException {
		// System.setOut(new PrintStream(new FileOutputStream(new
		// File("log.txt"))));
		System.out.println("time :" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

		new DownloadFileDemo().downloadFile();

	}

	private void downloadFile() {
		String path = "http://localhost:8080/" + DOWNLOAD_FILE_NAME;
		boolean hasComplete = true; // 用于记录文件下载是否完成,如果完成,则不下载,否则下载

		try {
			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code = conn.getResponseCode();

			if (code == HttpURLConnection.HTTP_OK) {
				// 服务器返回的数据的长度, 实际上就是要下载的文件的长度.
				int length = conn.getContentLength();
				System.out.println("Total size of file is: " + length);

				// 将要下载的文件分给 THREAD_COUNT 个线程们
				int blockSize = length / THREAD_COUNT;

				for (int i = 1; i <= THREAD_COUNT; i++) {
					int startAddress = (i - 1) * blockSize;
					int process = this.getProcess(i);  
					int endAddress = (i * blockSize) - 1;

					if (i == THREAD_COUNT) {
						endAddress = length - 1;
					}

					System.out.println(i + " " + startAddress + " " + endAddress);

					if (startAddress + process - 1 != endAddress) {
						hasComplete = false;
						new DownloadThread(i, startAddress, process, endAddress, path).start();
					}
				}

				if (hasComplete == true) {
					System.out.println("File is been download before");
				}

			} else {
				throw new HTTPException(code);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中获取下载的进度(已经下载的 byte 数) 
	 * @param threadID
	 * @return
	 */
	private int getProcess(int threadID) {
		String filePath = String.format(PROCESS_FILE_TEMPLATE, DOWNLOAD_FILE_NAME, threadID);

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			return Integer.parseInt(br.readLine());
		} catch (Exception e) {
			return 0;
		}

	}

	class DownloadThread extends Thread {

		private int threadID;
		private int startAddress;
		private int process;
		private int endAddress;
		private String path;

		public DownloadThread(int threadID, int startAddress, int process, int endAddress, String path) {
			super();
			this.threadID = threadID;
			this.startAddress = startAddress;
			this.process = process;
			this.endAddress = endAddress;
			this.path = path;
		}

		@Override
		public void run() {

			try {
				URL url = new URL(this.path);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				// 重要: 请求服务器下载部分的文件, 指定起始和结束地址
				conn.setRequestProperty("Range", "bytes=" + (this.startAddress + this.process) + "-" + this.endAddress);
				conn.setConnectTimeout(5000);

				RandomAccessFile raf = new RandomAccessFile(DownloadFileDemo.DOWNLOAD_FILE_NAME, "rwd"); // 第二个参数是模式, rwd代表下载的数据立即写入硬盘,详见API
				raf.seek(this.startAddress + this.process); // 设置下载存储的起始地址

				int code = conn.getResponseCode();
				if (code == HttpURLConnection.HTTP_PARTIAL) { // 请求下载部分文件,成功的返回值是常量  HttpURLConnection.HTTP_PARTIAL它的值是  206
					InputStream is = conn.getInputStream();

					byte buffer[] = new byte[1024];
					int len;
					int total = 0;
					while ((len = is.read(buffer)) != -1) {

						raf.write(buffer, 0, len);
						total += len;

						RandomAccessFile info = new RandomAccessFile(String.format(PROCESS_FILE_TEMPLATE, DOWNLOAD_FILE_NAME, this.threadID), "rwd");
						info.write(String.valueOf(this.process + total).getBytes());
						info.close();

						System.out.println(String.format("Thread %d has complete %f%%", this.threadID, (100 * ((this.process + total * 1.0) / ((this.endAddress - this.startAddress) + 1)))));

					}

					// File file = new File(String.format(PROCESS_FILE_TEMPLATE,
					// DOWNLOAD_FILE_NAME, this.threadID));file.delete();

				} else {
					throw new HTTPException(code);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
