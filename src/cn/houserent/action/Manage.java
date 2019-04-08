package cn.houserent.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import cn.houserent.dao.hibimpl.HouseDaoHibImpl;
import cn.houserent.entity.House;
import cn.houserent.entity.User;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.impl.HouseBizImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("manage")
public class Manage extends ActionSupport{
	@Autowired
	@Qualifier("houseBiz")
	private HouseBiz houseBiz;
	public void setHouseBiz(HouseBiz houseBiz) {
		this.houseBiz = houseBiz;
	}

	private static final long serialVersionUID = 1L;
	private int number = 0; //要显示第几页
	private int total = 0; //总页数
	private int prev = 0; //上一页
	private int next = 0; //下一页
	private int[] numbers;
	private static final int RECORD_COUNT = 3; //每页显示3条数据
	private List<House> result;
	private User publisher;
	

	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	public List<House> getResult() {
		return result;
	}
	public void setResult(List<House> result) {
		this.result = result;
	}
	public String list() throws Exception{
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		publisher=new User();
		publisher=(User)session.get("login");
		if(number == 0){
			number = 1;
		}
		int start = 0; //从第几条开始
		int end = 0; //到第几条结束
		start = (number - 1) * RECORD_COUNT;
		end = number * RECORD_COUNT;
		
		result = houseBiz.query(start, RECORD_COUNT);
		//session.put("result", result);
		int count = houseBiz.getAllHouse().size(); //总条数
		total = (count - (count%RECORD_COUNT))/RECORD_COUNT; //总页数
		if(count % RECORD_COUNT != 0){
			total++;
		}
		if(number <= 1){
			prev = 1;
			next = number + 1;
		}else if(number >= total){
			prev = number - 1;
			next = total;
		}else{
			prev = number - 1;
			next = number + 1;
		}
		numbers = new int[total];
		for(int i = 0;i < total;i++){
			numbers[i] = i+1;
		}		
		return "list";
	}
	public String ajaxList() throws Exception{
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		if(number == 0){
			number = 1;
		}
		int start = 0; //从第几条开始
		int end = 0; //到第几条结束
		start = (number - 1) * RECORD_COUNT;
		end = number * RECORD_COUNT;
		
		List<House> result = null;
		result = houseBiz.query(start, RECORD_COUNT);
		session.put("result", result);
		int count = houseBiz.getAllHouse().size(); //总条数
		total = (count - (count%RECORD_COUNT))/RECORD_COUNT; //总页数
		if(count % RECORD_COUNT != 0){
			total++;
		}
		if(number <= 1){
			prev = 1;
			next = number + 1;
		}else if(number >= total){
			prev = number - 1;
			next = total;
		}else{
			prev = number - 1;
			next = number + 1;
		}
		return "ajaxlist";
	}
	
	public int[] getNumbers() {
		return numbers;
	}
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}


}
