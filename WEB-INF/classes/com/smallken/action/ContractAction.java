package com.smallken.action;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smallken.db.DBUtil;
import com.smallken.util.DATE;
import com.smallken.vo.Contract;

public class ContractAction extends ActionSupport implements ModelDriven<Contract> {
	//have to initialize it	
	Contract contract = new Contract();
	private List<Contract> contracts;
	DBUtil dbuti = null;
	/**
	 * 
	 INSERT INTO `DTSF01`(`ROOM`, `BEGIN_DATE`, `NAME`, `END_DATE`,
	 `CELL_PHONE`, `NICK_NAME`, `RENT_AMT`, `DIPOSIT`, `THIS_DEGREES`,
	  `TIMES`, `ADDRESS`, `STATUS`) VALUES
	  ([value-1],[value-2],[value-3],[value
	  -4],[value-5],[value-6],[value-7],[value
	 -8],[value-9],[value-10],[value-11],[value-12])
	 */
	
	/**
	 * 
	INSERT INTO `DTSF02`(`INPUT_DATE`, `ROOM`, `NAME`, `LAST_DEGREES`, `THIS_DEGREES`,
	 `RENT_AMT`, `ELECTRIC_AMT`, `DIPOSIT_AMT`, `TOTAL_AMT`, `MESSAGE`) VALUES
	  ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10])
	 */
	
	public String execute()  {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String  INSERT_SQL = getSQL(contract);
		try {
			dbuti.executeUpdate(INSERT_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		return SUCCESS;
 
	}

	public String doInsert()  {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String  INSERT_SQL = getSQL(contract);
		try {
			dbuti.executeUpdate(INSERT_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		
		return SUCCESS;
 
	}
	
	public String doQuery()  {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		String query_SQL = getSQL2(contract);
		try {
			rs = dbuti.Query(query_SQL);
			rs.next();
			contract.setRoom(rs.getString("ROOM"));
			contract.setBegin_date(DATE.toFDate(rs.getString("BEGIN_DATE")));
			contract.setName(rs.getString("NAME"));
			contract.setEnd_date(DATE.toFDate(rs.getString("END_DATE")));
			contract.setCell_phone(rs.getString("CELL_PHONE"));
			contract.setNick_name(rs.getString("NICK_NAME"));
			contract.setRent_amt(String.valueOf(new BigDecimal(rs
					.getString("RENT_AMT")).intValue()));
			contract.setPub_dashboard(rs.getString("PUB_DASHBOARD")); 
			contract.setDiposit(String.valueOf(new BigDecimal(rs
					.getString("DIPOSIT")).intValue()));
			contract.setThis_degrees(String.valueOf(new BigDecimal(rs
					.getString("THIS_DEGREES")).intValue()));
			contract.setTimes(String.valueOf(new BigDecimal(rs
					.getString("TIMES")).setScale(1)));
			contract.setAddress(rs.getString("ADDRESS"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}

		return SUCCESS;
	}

	public String doDelete()   {
		String room = contract.getRoom();
		String begindate = DATE.toF2Date(contract.getBegin_date());
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			dbuti.executeUpdate(getDeletesql(room,begindate));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}
		
		return SUCCESS;
	}

	public List<Contract> getContracts(){		
	    return contracts;
	} 
	
	public void Contracts(List<Contract> contracts){
	    this.contracts = contracts;
	} 
	
	public String findContracts(){
		contracts = findAllContracts();
	     return "success";
	}	
	
	private List<Contract> findAllContracts() {
		try {
			dbuti = new DBUtil(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ResultSet rs = null;
		List<Contract> lt = new ArrayList<Contract>();
		try {
			rs = dbuti.Query("SELECT *  FROM  `DTSF01` WHERE STATUS = 'Y' ORDER BY ROOM");
			while(rs.next()){
				Contract vo = new Contract();
				vo.setRoom(rs.getString("ROOM"));
				vo.setBegin_date(DATE.toFDate(rs.getString("BEGIN_DATE")));
				vo.setName(rs.getString("NAME"));
				vo.setEnd_date(DATE.toFDate(rs.getString("END_DATE")));
				vo.setIspreline(getisPreLine(rs.getString("END_DATE"),40));
				vo.setCell_phone(rs.getString("CELL_PHONE"));
				vo.setNick_name(rs.getString("NICK_NAME"));
				vo.setRent_amt(String.valueOf(new BigDecimal(rs.getString("RENT_AMT")).intValue()));
				vo.setDiposit(String.valueOf(new BigDecimal(rs.getString("DIPOSIT")).intValue()));
				vo.setThis_degrees(String.valueOf(new BigDecimal(rs.getString("THIS_DEGREES")).intValue()));
				vo.setTimes(rs.getString("TIMES"));
				vo.setAddress(rs.getString("ADDRESS"));
				lt.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbuti.close();
		}

		return lt;
	}
	

	/**
	 * 合約到期日days天前提示
	 * @param date1
	 * @return
	 */
	private boolean getisPreLine(String date1,int days) {
		String date2  = DATE.getNowTime("yyyy-MM-dd");
		String  between = DATE.getTwoDay(date1, date2);
		if(Integer.parseInt(between) < days){
			return true;
		}else{
			return false;
		}		
	}

	public Contract getModel() {
		 
		return contract;
		
	}
	
	private String getSQL(Contract cont) {
		String sql = " INSERT INTO `DTSF01`(`ROOM`, `BEGIN_DATE`, `NAME`, `END_DATE`, `CELL_PHONE`, `NICK_NAME`, `RENT_AMT`, `DIPOSIT`, `PUB_DASHBOARD`, `THIS_DEGREES`, `TIMES`, `ADDRESS`, `STATUS`) " +
				"VALUES ('" + cont.getRoom() + "','"+ cont.getBegin_date() + "','" + cont.getName() + "','" + cont.getEnd_date() + "','" + cont.getCell_phone() 
				+ "','" + cont.getNick_name() + "'," + cont.getRent_amt() + "," + cont.getDiposit() + ",'" + cont.getPub_dashboard() +  "',"  +  cont.getThis_degrees() + "," +  cont.getTimes() 
				+ ",'" + cont.getAddress() +"','Y')" ;
		return sql;
	}
	
	
	private String getSQL2(Contract cont) {
		String sql = "SELECT * FROM  `DTSF01` WHERE ROOM='" + cont.getRoom() 
						   +"' AND BEGIN_DATE='" + DATE.toF2Date(cont.getBegin_date())+ "'";
		return sql;
	}
	

	
	private String getDeletesql(String room, String begindate) {
		String sql = "UPDATE  `DTSF01`  SET STATUS='N' WHERE ROOM ='" + room +"' AND BEGIN_DATE='" + 
								begindate + "'";
		return sql;
	}
	
	
	
	
}
