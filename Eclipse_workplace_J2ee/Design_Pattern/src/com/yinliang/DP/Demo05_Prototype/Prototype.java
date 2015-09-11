package com.yinliang.DP.Demo05_Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Prototype implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private String string;
	private SerializableObject obj;

	/* 浅复制 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}

	/* 深复制 */
	public Object deepClone() throws IOException, ClassNotFoundException {
		/* 写入当前对象的二进制流 */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		/* 读出二进制流产生的新对象 */
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();

	}

}

class SerializableObject {
	private static final long serialVersionUID = 1L;
}