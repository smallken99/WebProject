package com.smallken.action;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smallken.db.DBUtil;
import com.smallken.util.DATE;
import com.smallken.vo.History;

public class HistoryAction extends ActionSupport implements ModelDriven<History> {
	//have to initialize it	
	History history = new History();
	private List<History> historys;
	private String times = "";
	private String pub_dashboard="";
	private String ismore = "";
	


	DBUtil dbuti = null;
	

	/**
	 * 
	SELECT *  FROM  `DTSF01` 
	 */
	
	public String execute()   {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String QUERY_SQL = getSQL(history.getRoom());
		ResultSet rs;
		try {
			rs = dbuti.Query(QUERY_SQL);
			rs.next();
			history.setRoom(rs.getString("room"));
			history.setInput_date(DATE.getNowTime("yyyy-MM-dd"));
			history.setName(rs.getString("name"));
			history.setLast_degrees(String.valueOf(new BigDecimal(rs
					.getString("this_degrees")).intValue()));
			history.setThis_degrees_t(String.valueOf(new BigDecimal(rs
					.getString("this_degrees")).intValue()));
			history.setRent_amt(String.valueOf(new BigDecimal(rs
					.getString("rent_amt")).intValue()));
			history.setPub_electric_amt("0");
			history.setElectric_amt("0");
			history.setDiposit_amt("0");
			history.setTotal_amt("0");
			history.setMessage("");
			times = rs.getString("times");
			pub_dashboard = rs.getString("pub_dashboard");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		return "addpay";

	}
 

	public String doCalculate() throws Exception {
		BigDecimal  pub_electric_amt = new BigDecimal(0);
		BigDecimal  electric_amt = new BigDecimal(0);
		BigDecimal  total_amt = new BigDecimal(0);
		BigDecimal diposit_amt =new BigDecimal(history.getDiposit_amt());
		electric_amt = new BigDecimal(history.getThis_degrees_t()).subtract(new BigDecimal(history.getLast_degrees()))
				.multiply(new BigDecimal(times));		
		// 有公共電費時
		if(!pub_dashboard.equals("")){
			pub_electric_amt = getpub_electric_amt(pub_dashboard, history.getInput_date());
		}
		total_amt = electric_amt.add(new BigDecimal(history.getRent_amt())).add(diposit_amt).add(pub_electric_amt);
		history.setPub_electric_amt((String.valueOf(pub_electric_amt.intValue())));
		history.setElectric_amt(String.valueOf(electric_amt.intValue()));
		history.setTotal_amt(String.valueOf(total_amt.intValue()));
		
		String msg = "";
		// 有押金的代表初次繳費,應該沒有電費
		if(history.getDiposit_amt().equals("0")){
			// 有公共電費時
			if(!pub_dashboard.equals("")){
				msg = "你好，應繳納房租 " + history.getRent_amt()+"，公共電費 " + pub_electric_amt.intValue() + "，個人電費 " + electric_amt.intValue()
						   +"，合計 " + total_amt.intValue() +" 元";
			}else{
				msg = "你好，本月房租共 " + String.valueOf(total_amt.intValue()) +" 元\r\n("
						+ history.getThis_degrees_t() +"-" + new BigDecimal(history.getLast_degrees()).intValue()+ ")x"
						+ new BigDecimal(times).setScale(1) +"+"+											+
						new BigDecimal(history.getRent_amt()).intValue() + "=" +total_amt.intValue() ;
			}

		}else{
				msg = "繳納押金 " + diposit_amt.intValue() +"，房租 " + history.getRent_amt()+"，電費 " + electric_amt.intValue()
						   +"\r\n，合計 " + total_amt.intValue() +" 元";
		}
		history.setMessage(msg);
		return "addpay";

	}
	
	
	private BigDecimal getpub_electric_amt(String pub_dashboard,
			String input_date) {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BigDecimal avg_amt = new BigDecimal(0);;
		String QUERY_SQL = getSQL3(pub_dashboard,input_date);
		ResultSet rs;
		try {
			rs = dbuti.Query(QUERY_SQL);
			rs.next();
			avg_amt = new BigDecimal(rs.getString("avg_amt"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		
		return avg_amt;
	}





	public String insert()   {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			dbuti = new DBUtil(false,false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO `DTSF02`(`INPUT_DATE`, `ROOM`, `NAME`, `LAST_DEGREES`, `THIS_DEGREES`," +
				" `RENT_AMT`, `PUB_ELECTRIC_AMT`,`ELECTRIC_AMT`, `DIPOSIT_AMT`, `TOTAL_AMT`, `MESSAGE`) VALUES ('" + 
				history.getInput_date() + "','" + history.getRoom()+ "','" + history.getName() + "'," + history.getLast_degrees() + "," +
				history.getThis_degrees_t() + "," + history.getRent_amt()+ "," + history.getPub_electric_amt() + "," + history.getElectric_amt()  + "," + history.getDiposit_amt()  + "," +
				history.getTotal_amt()+ ",'" + history.getMessage()   +  "')";	
		
		String sql2 = "UPDATE `DTSF01` SET `THIS_DEGREES`='" + history.getThis_degrees_t() +
				"' WHERE ROOM ='" + history.getRoom() + "'";	
		try {
			dbuti.executeUpdate(sql);
			dbuti.executeUpdate(sql2);
			dbuti.commit();
		} catch (SQLException e) {
			dbuti.rollback();
			e.printStackTrace();
		}finally{
			dbuti.close();
		}
		
		return SUCCESS;

	}

	
	public String delete()   {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "DELETE  FROM  `DTSF02` WHERE  INPUT_DATE='" + DATE.toF2Date(history.getInput_date())+ "' AND ROOM='" +
				history.getRoom() + "'  AND  THIS_DEGREES='" + history.getThis_degrees_t() + "' AND LAST_DEGREES='" 
				+ history.getLast_degrees() + "'";
		
		try {
			dbuti.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbuti.close();
		}
		
		return SUCCESS;

	}
	
	public String query() throws Exception {
			historys = findAllHistorys();
		return SUCCESS;
	}


	
	public History getModel() {
 
		return history;
		
	}

	public String getPub_dashboard() {
		return pub_dashboard;
	}


	public void setPub_dashboard(String pub_dashboard) {
		this.pub_dashboard = pub_dashboard;
	}

	public String getIsmore() {
		return ismore;
	}

	public void setIsmore(String ismore) {
		this.ismore = ismore;
	}
	
	public String getTimes() {
		return times;
	}


	public void setTimes(String times) {
		this.times = times;
	}
	
	public List<History> getHistorys(){		
	    return historys;
	} 
	
	public void Historys(List<History> historys){
	    this.historys = historys;
	} 	


	private List<History> findAllHistorys() {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ResultSet rs = null;
		List<History> lt = new ArrayList<History>();
		try {
			rs = dbuti.Query( getSQL2(history.getRoom()));
			while(rs.next()){
				History  vo = new History();
				vo.setRoom(rs.getString("room"));
				vo.setInput_date(DATE.toFDate(rs.getString("input_date")));
				vo.setName(rs.getString("name"));
				vo.setLast_degrees(rs.getString("last_degrees"));
				vo.setThis_degrees_t(rs.getString("this_degrees"));
				vo.setRent_amt(rs.getString("rent_amt"));
				vo.setPub_electric_amt(rs.getString("pub_electric_amt"));
				vo.setElectric_amt(rs.getString("electric_amt"));
				vo.setDiposit_amt(rs.getString("diposit_amt"));
				vo.setTotal_amt(rs.getString("total_amt"));
				vo.setMessage(rs.getString("message"));
				lt.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbuti.close();
		}

		return lt;
	}	
	
	private String getSQL(String room) {
		String sql = "SELECT *  FROM  `DTSF01`  WHERE ROOM = '" + room + "' AND  STATUS='Y'";
		return sql;
	}

	private String getSQL2(String room) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *  FROM  `DTSF02`  WHERE ROOM = '").append(room).append( "' " );
		sb.append("ORDER BY INPUT_DATE DESC,THIS_DEGREES DESC ");
		if(!ismore.equals("Y")){
			sb.append("LIMIT 0 , 10");
		}
		return sb.toString();
	}
	
	private String getSQL3(String pub_dashboard, String input_date) {
		String sql = "SELECT * FROM `DTSF04` WHERE `DASHBOARD` = '" 
			+ pub_dashboard + "' AND `INPUT_DATE` = '" 
			+ input_date + "'";
		return sql;
	}
	
}
