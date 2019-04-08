package cn.houserent.interceptor;

import java.util.Map;

import cn.houserent.entity.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限验证检查拦截器
 * @author jbit 
 */

public class AuthorizationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1919190535109141892L;

	/*
	* 拦截器的拦截方法
	*/
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		String theName = invocation.getInvocationContext().getName();
		if(theName.equals("user")){
			return invocation.invoke();
		}
		//获取用户会话信息
		Map session = invocation.getInvocationContext().getSession();
		User user = (User)session.get("login");
		if (user == null) {
			//终止执行，返回登录页面
			return Action.LOGIN;
		} else {
			//继续执行剩余的拦截器和Action
			return invocation.invoke();
		}
	}
}
