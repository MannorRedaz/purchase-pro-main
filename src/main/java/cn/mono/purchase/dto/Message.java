package cn.mono.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Message {
	private boolean success = false;
	private String msg;
	private List date;
	private Integer status = 0;

	//代表是何种用户
	private int  p;

	public Message(boolean success, String msg, List date, int p) {
		this.success = success;
		this.msg = msg;
		this.date = date;
		this.p = p;
	}

	public Message(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public  void clear(){
		success=false;
		msg="";
		date=null;
		p=0;
	}
//	public static <T> Message<T> success(T data) {
//		Message<T> result = new Message<>();
//		result.success=Boolean.TRUE;
//		result.date = (List) data;
//		result.msg = ErrorCodeEnum.SUCCESS.getMsg();
//		return result;
//	}
//
//	public static <T> Message<T> error(ErrorCodeEnum errorCodeEnum) {
//		Message<T> result = new Message<>();
//		result.success=Boolean.FALSE;
//		result.msg = errorCodeEnum.getMsg();
//		return result;
//	}
//	public static <T> Message<T> error(T data, ErrorCodeEnum errorCodeEnum) {
//		Message<T> result = new Message<>();
//		result.success=Boolean.FALSE;
//		result.date = (List) data;
//		result.msg = errorCodeEnum.getMsg();
//		return result;
//	}


}
