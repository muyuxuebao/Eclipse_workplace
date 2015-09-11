package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer; //时间类

public class Time {
	public Time(JLabel time) {//构造方法 this.setTimer(time);//设置时间
	}

	public void setTimer(JLabel time) {
		final JLabel varTime = time;
		Timer timeAction = new Timer(1000, new ActionListener() { //时间监听
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis(); //得到系统时间 
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//设置时间方法 //传递组件

				//转换日期显示格式的时间
				varTime.setText(df.format(new Date(timemillis)));

			}
		});
		timeAction.start();
		//开启线程
	}
}