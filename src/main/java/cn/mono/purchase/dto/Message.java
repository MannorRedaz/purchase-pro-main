package cn.mono.purchase.dto;

import java.util.List;

public class Message {
	private boolean success = false;
	private String msg;
	private List date;
	private Integer status = 0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private int  p;//代表是何种用户

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public Message(boolean success, String msg, List date, int p) {
		this.success = success;
		this.msg = msg;
		this.date = date;
		this.p = p;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Message(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}



	public List getDate() {
		return date;
	}

	public void setDate(List date) {
		this.date = date;
	}
	public  void clear(){
		success=false;
		msg="";
		date=null;
		p=0;
	}

	@Override
	public String toString() {
		return "Message{" +
				"success=" + success +
				", msg='" + msg + '\'' +
				", date=" + date +
				", p=" + p +
				'}';
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}


}
