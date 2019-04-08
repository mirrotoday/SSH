package cn.houserent.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.houserent.dao.hibimpl.HouseDaoHibImpl;
import cn.houserent.entity.House;
import cn.houserent.entity.User;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.impl.HouseBizImpl;
import cn.houserent.util.QueryProperties;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;


public class HouseAction implements Action {
	private String price;
	private String street_id;
	private String type_id;
	private String floorage;
	private String publish_date;
	private String title;
	private Date houseDate;
	private int number = 0; //要显示第几页
	private int total = 0; //总页数
	private int prev = 0; //上一页
	private int next = 0; //下一页
	private int curPage=0;//代表当前页面
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	private static final int RECORD_COUNT =3; //每页显示3条数据
	private List<House> result;
	
	public List<House> getResult() {
		return result;
	}

	public void setResult(List<House> result) {
		this.result = result;
	}

	
	public String execute() {
		ActionContext ac = ActionContext.getContext();
		User user=(User)ac.getSession().get("login");
		HouseBiz hb =new HouseBizImpl();
		result = hb.getHouseByUser(user);
		return SUCCESS;
		
	}

	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String index() throws Exception {

		ActionContext ac = ActionContext.getContext();
		Map request = (Map) ac.get("result");		
		if(number == 0){
			number = 1;
		}
		int start = 0; //从第几条开始
		int end = 0; //到第几条结束
		start = (number - 1) * RECORD_COUNT;
		end = number * RECORD_COUNT;
		
		List<House> result = null;
		HouseDaoHibImpl dao = new HouseDaoHibImpl();
		result = dao.query(start, RECORD_COUNT);
		ac.put("result", result);
		int count = dao.totalCount(); //总条数
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

		return SUCCESS;
	}
	
	public Date getHouseDate() {
		return houseDate;
	}

	public void setHouseDate(Date houseDate) {
		this.houseDate = houseDate;
	}


	/**
	 * 根据条件查找租房信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String lookup() throws Exception {
		if(number == 0){
			number = 1;
		}
		int start = 0; //从第几条开始
		int end = 0; //到第几条结束
		start = (number - 1) * RECORD_COUNT;
		end = number * RECORD_COUNT;
		

		HouseDaoHibImpl dao = new HouseDaoHibImpl();
		QueryProperties qp = new QueryProperties();
		if(null == title){
			title = "";
		} 	
		qp.setTitle(title );
		if(null != price && !"".equals(price)){
			System.out.println("ttt");
			String[] p_price = price.split("-");
			qp.setLow_price(Double.parseDouble(p_price[0].trim()));
			qp.setHigh_price(Double.parseDouble(p_price[1].trim()));
		} else{
			qp.setLow_price(0D);
			qp.setHigh_price(100000D);
		}
		if(null != street_id && !"".equals(street_id)){
			qp.setStreet_id(street_id);
		} else{
			qp.setStreet_id("");
		}
		if(null != type_id && !"".equals(type_id)){
			qp.setType_id(type_id);
		} else{
			qp.setType_id("");
		}
		if(null != floorage && !"".equals(floorage)){
			String[] p_floorage = floorage.split("-");
			qp.setSmall_floorage(Integer.parseInt(p_floorage[0].trim()));
			qp.setBig_floorage(Integer.parseInt(p_floorage[1].trim()));
		}  else{
			qp.setSmall_floorage(0);
			qp.setBig_floorage(Integer.MAX_VALUE);
		}
		if(null != publish_date && "".equals(publish_date)){
			String[] p_publish_date = publish_date.split("-");
			qp.setStart_date(this.stringToDate(p_publish_date[0].trim()));
			
		}else{
			qp.setStart_date(this.stringToDate("1900-01-01 00:00:00"));
		}
		qp.setEnd_date(new Date());		
		int count = dao.totalSearch(qp); //总条数
		System.out.println("---total:" + count);
		
		List<House> houses =dao.findHouseByProperties(start, RECORD_COUNT, qp);
		
		
		total = (count - (count%RECORD_COUNT))/RECORD_COUNT; //总页数
		if(count % RECORD_COUNT != 0){
			total++;
		}
		if(number == 1 && total == 1){
			prev = 1;
			next = 1;
		}else{
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
		}
		curPage=number;
		if(curPage>total){
			curPage=total;
		}
		ActionContext ac = ActionContext.getContext();
		Map session=ac.getSession();
		session.put("qp", qp);
		ac.put("result", houses);
		return SUCCESS;
	}
	
	/**
	 * 根据title返回租房信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String lookupByTitle() throws Exception {
		
		HouseBiz hb = new HouseBizImpl();
		List<House> houses = hb.getHouseByTitle(title);
		ActionContext ac = ActionContext.getContext();
		Map request = (Map) ac.get("request");
		request.put("houses", houses);
		return SUCCESS;
	}
	protected Date stringToDate(String transform){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		Date date = null;
		
		try {
			sdf.parse(transform);
		} catch (ParseException e) {
			e.printStackTrace();
		}			
		return date;
		
	}	


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String streetId) {
		street_id = streetId;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getFloorage() {
		return floorage;
	}

	public void setFloorage(String floorage) {
		this.floorage = floorage;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publishDate) {
		publish_date = publishDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
