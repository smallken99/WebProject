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
import com.smallken.vo.DTSF03;
import com.smallken.vo.History;

public class PubDashboardAction extends ActionSupport implements ModelDriven<DTSF03> {
	//have to initialize it	
	DTSF03 dtsf03 = new DTSF03();
	private List<DTSF03> dtsf03s;
    private String input_date = "";
    private String last_degrees="";
	private String electric_amt ="";
	DBUtil dbuti = null;
	

	
	/**
	 * 
	SELECT *  FROM  `DTSF03` 
	 */
	
	public String execute() throws Exception {
		dtsf03s = findAlldtsf03s();
		return SUCCESS;
	}


	
	public String query()   {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String QUERY_SQL = getSQL2(dtsf03.getDashboard());
		ResultSet rs;
		try {
			rs = dbuti.Query(QUERY_SQL);
			rs.next();
			dtsf03.setDashboard(rs.getString("dashboard"));
			dtsf03.setDashb_name(rs.getString("dashb_name"));
			dtsf03.setThis_degrees(rs.getString("this_degrees"));
			dtsf03.setTimes(rs.getString("times"));
			dtsf03.setAvg_num(rs.getString("avg_num"));
			last_degrees = rs.getString("this_degrees");
			input_date = DATE.getNowTime("yyyy-MM-dd");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		return SUCCESS;

	}

	public String doCalculate()   {
		BigDecimal c_this_degrees = new BigDecimal(dtsf03.getThis_degrees());
		BigDecimal c_last_degrees = new BigDecimal(last_degrees);
		BigDecimal c_times = new BigDecimal(dtsf03.getTimes());
		BigDecimal c_avg_num = new BigDecimal(dtsf03.getAvg_num());
		BigDecimal c_electric_amt = c_this_degrees.subtract(c_last_degrees).multiply(c_times).divide(c_avg_num,0);
		electric_amt = String.valueOf(c_electric_amt.intValue());
		return SUCCESS;

	}	

	
	public DTSF03 getModel() {
 
		return dtsf03;
		
	}


	
	public List<DTSF03> getDtsf03s(){		
	    return dtsf03s;
	} 
	
	public void dtsf03s(List<DTSF03> dtsf03s){
	    this.dtsf03s = dtsf03s;
	} 	


	private List<DTSF03> findAlldtsf03s() {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ResultSet rs = null;
		List<DTSF03> lt = new ArrayList<DTSF03>();
		try {
			String s  =getSQL();
			rs = dbuti.Query(s );
			while(rs.next()){
				DTSF03  vo = new DTSF03();
				vo.setDashboard(rs.getString("dashboard"));
				vo.setDashb_name(rs.getString("dashb_name"));
				vo.setThis_degrees(rs.getString("this_degrees"));
				vo.setTimes(rs.getString("times"));
				vo.setAvg_num(rs.getString("avg_num"));
				lt.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbuti.close();
		}

		return lt;
	}	
	
	public String getElectric_amt() {
		return electric_amt;
	}



	public void setElectric_amt(String electric_amt) {
		this.electric_amt = electric_amt;
	}



	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}



	public String getLast_degrees() {
		return last_degrees;
	}



	public void setLast_degrees(String last_degrees) {
		this.last_degrees = last_degrees;
	}



	private String getSQL() {
		String sql = "SELECT * FROM  `DTSF03`";
		return sql;
	}

	private String getSQL2(String dashboard) {
		String sql = "SELECT * FROM  `DTSF03` WHERE DASHBOARD='" + dashboard + "'";
		return sql;
	}

	
}
