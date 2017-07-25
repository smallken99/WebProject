<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
</head>
<body>  
  
新增成功! 結果如下：<br/>
房號:<s:property value="room"/><br/>
租約起始日:<s:property value="begin_date" /><br/>
房客姓名:<s:property value="name" /><br/>
租約到期日:<s:property value="end_date" /><br/> 
手機號碼:<s:property value="cell_phone" /><br/>
稱呼:<s:property value="nick_name" /><br/>
每月租金:<s:property value="rent_amt" /><br/> 
押金:<s:property value="diposit" /><br/>
目前電表度數:<s:property value="this_degrees" /><br/>
每度電費:<s:property value="times" /><br/>
連絡地址:<s:property value="address" /><p/> 

<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>
</body>
</html>