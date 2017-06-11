package com.lorne.core.framework.model;

public class Msg extends  JsonModel{

	private int state;
	private String msg;
	private Object res;


	public Msg() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Msg(int state, Object res) {
		super();
		this.state = state;
		this.res = res;
	}

	public Msg(int state) {
		super();
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getRes() {
		return res;
	}

	public void setRes(Object res) {
		this.res = res;
	}


}
