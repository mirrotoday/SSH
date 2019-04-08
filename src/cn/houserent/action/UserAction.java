package cn.houserent.action;

import java.util.List;
import java.util.Map;

import cn.houserent.entity.House;
import cn.houserent.entity.User;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.UserBiz;
import cn.houserent.service.impl.HouseBizImpl;
import cn.houserent.service.impl.UserBizImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * 用户action
 */
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 537099716868797289L;
	private String name;
	private String password;
	private String message;
	
	// 定义业务接口属性
	private UserBiz userBiz;
	//定义setter方法,方法名应和Spring配置文件中业务Bean的名称相匹配
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String register() throws Exception {
		User user = new User();
		user.setName(name);
		user.setPassword(password);		
		if(!userBiz.validate(user.getName().trim())) {
			this.addFieldError("user.name", "用户名存在");
			return "register_input";
		}
		if(!userBiz.register(user)) return "register_input";
		return "register_success";
	}
	
	@SuppressWarnings("unchecked")
	public String login() throws Exception {		
		User user = userBiz.login(name, password);
		ActionContext ac = ActionContext.getContext();
		if(name == null || name.equals("")){
			this.setMessage("登录失败，请检查用户名和密码是否正确");
			return "login_input";
		}
		if(password == null || password.equals("")){
			this.setMessage("登录失败，请检查用户名和密码是否正确");
			return "login_input";
		}		
		Map session = ac.getSession();
		if(null != user){
			session.put("login", user);				
			return "login_success";
		}
		this.setMessage("登录失败，请检查用户名和密码是否正确");
		return "login_input";
	}

}