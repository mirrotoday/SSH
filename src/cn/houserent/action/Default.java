package cn.houserent.action;

import com.opensymphony.xwork2.ActionSupport;

public class Default extends ActionSupport{

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{
		message = "请求不存在";
		return "fail";
		
	}
}
