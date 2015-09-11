package com.yinliang.DP.Demo10_Bridge;

public abstract class Bridge {
	private Sourceable source = null;

	public void method() {
		source.method();
	}

	public Sourceable getSource() {
		return source;
	}

	public void setSource(Sourceable source) {
		this.source = source;
	}

}
