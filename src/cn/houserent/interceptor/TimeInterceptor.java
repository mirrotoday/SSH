package cn.houserent.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimeInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -1919190535109141892L;

	/*
	* 拦截器的拦截方法
	*/
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		long first = System.currentTimeMillis();
		String result = invocation.invoke();
		long last =  System.currentTimeMillis();
		long peroid = last - first;
		System.out.println("---Action执行时间为：" + peroid);
		 return result;
	}
}
