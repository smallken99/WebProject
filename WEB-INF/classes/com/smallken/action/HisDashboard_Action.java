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
import com.smallken.vo.DTSF04;
import com.smallken.vo.History;

public class HisDashboard_Action extends ActionSupport implements ModelDriven<DTSF04> {
	//have to initialize it	
	DTSF04 dtsf04 = new DTSF04();
	private List<DTSF04> dtsf04s;
	private String dashb_name ="";
	private String times ="";
	private String avg_num="";
	DBUtil dbuti = null;
	

	
	public String getDashb_name() {
		return dashb_name;
	}



	public void setDashb_name(String dashb_name) {
		this.dashb_name = dashb_name;
	}



	public String getTimes() {
		return times;
	}



	public void setTimes(String times) {
		this.times = times;
	}



	public String getAvg_num() {
		return avg_num;
	}



	public void setAvg_num(String avg_num) {
		this.avg_num = avg_num;
	}



	/**
	 * 
	SELECT *  FROM  `DTSF04` 
	 */
	
	public String execute() throws Exception {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String QUERY_SQL = getSQL2(dtsf04.getDashboard());
		ResultSet rs;
		try {
			rs = dbuti.Query(QUERY_SQL);
			rs.next();
			dtsf04.setDashboard(rs.getString("dashboard"));
			dtsf04.setInput_date(DATE.getNowTime("yyyy-MM-dd"));
			dashb_name = rs.getString("dashb_name");
			dtsf04.setThis_degrees(rs.getString("this_degrees"));
			dtsf04.setLast_degrees(rs.getString("this_degrees"));
			times = rs.getString("times");
			avg_num = rs.getString("avg_num");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		return SUCCESS;
	}


	
	public String query() throws Exception {
		dtsf04s = findAlldtsf04s();
		return SUCCESS;
	}

	public String doCalculate()   {
		dtsf04.setDashboard(dtsf04.getDashboard().split(",")[0]);
		BigDecimal c_this_degrees = new BigDecimal(dtsf04.getThis_degrees());
		BigDecimal c_last_degrees = new BigDecimal(dtsf04.getLast_degrees());
		BigDecimal c_times = new BigDecimal(times);
		BigDecimal c_avg_num = new BigDecimal(avg_num);
		BigDecimal c_electric_amt =  c_this_degrees.subtract(c_last_degrees).multiply(c_times);
		BigDecimal c_avg_amt = c_electric_amt.divide(c_avg_num,0);
		dtsf04.setElectric_amt(String.valueOf(c_electric_amt.intValue()));
		dtsf04.setAvg_amt(String.valueOf(c_avg_amt.intValue()));
		return SUCCESS;

	}	


	public String insert()   {
		//HttpServletRequest request = ServletActionContext.getRequest();
		dtsf04.setDashboard(dtsf04.getDashboard().split(",")[0]);
		try {
			dbuti = new DBUtil(false,false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO `DTSF04`(`DASHBOARD`, `INPUT_DATE`, `LAST_DEGREES`, `THIS_DEGREES`, `ELECTRIC_AMT`, " +
				"				`AVG_AMT`) VALUES('" + 
				dtsf04.getDashboard()+ "','" +dtsf04.getInput_date()+ "'," + dtsf04.getLast_degrees()+ "," + dtsf04.getThis_degrees()+ "," +
				dtsf04.getElectric_amt()+ "," + dtsf04.getAvg_amt() +  ")";	
//		
		String sql2 = "UPDATE `DTSF03` SET `THIS_DEGREES`=" +  dtsf04.getThis_degrees()+
				"  WHERE DASHBOARD ='" + dtsf04.getDashboard()+ "'";	
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
		
		String sql = "DELETE  FROM  `DTSF04` WHERE  INPUT_DATE='" + dtsf04.getInput_date()+ "' AND DASHBOARD='" +
				dtsf04.getDashboard()+ "'";
		
		try {
			dbuti.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbuti.close();
		}
		
		return SUCCESS;

	}
	
	
	public DTSF04 getModel() {
 
		return dtsf04;
		
	}


	
	public List<DTSF04> getDtsf04s(){		
	    return dtsf04s;
	} 
	
	public void dtsf04s(List<DTSF04> DTSF04s){
	    this.dtsf04s = DTSF04s;
	} 	


	private List<DTSF04> findAlldtsf04s() {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ResultSet rs = null;
		List<DTSF04> lt = new ArrayList<DTSF04>();
		try {
			String s  =getSQL(dtsf04.getDashboard());
			rs = dbuti.Query(s );
			while(rs.next()){
				DTSF04  vo = new DTSF04();
				vo.setDashboard(rs.getString("dashboard"));
				vo.setInput_date(rs.getString("input_date"));
				vo.setLast_degrees(rs.getString("last_degrees"));
				vo.setThis_degrees(rs.getString("this_degrees"));
				vo.setElectric_amt(rs.getString("electric_amt"));
				vo.setAvg_amt(rs.getString("avg_amt"));
				lt.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbuti.close();
		}

		return lt;
	}	
	




	private String getSQL(String dashboard) {
		String sql = "SELECT * FROM  `DTSF04`  WHERE DASHBOARD='" + dashboard +"' ORDER BY INPUT_DATE DESC";
		return sql;
	}

	private String getSQL2(String dashboard) {
		String sql = "SELECT * FROM  `DTSF03` WHERE DASHBOARD='" + dashboard + "'";
		return sql;
	}

	
}
