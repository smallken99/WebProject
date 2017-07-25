<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
  
<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
</head>
<body>
  
查詢結果如下： 

<table border="1">
<tr><td>房號:<td><s:property value="room"/></tr>
<tr><td>租約起始日:<td><s:property value="begin_date" /></tr>
<tr><td>房客姓名:<td><s:property value="name" /></tr>
<tr><td>租約到期日:<td><s:property value="end_date" /></tr> 
<tr><td>手機號碼:<td><s:property value="cell_phone" /></tr>
<tr><td>稱呼:<td><s:property value="nick_name" /></tr>
<tr><td>每月租金:<td><s:property value="rent_amt" /></tr> 
<tr><td>押金:<td><s:property value="diposit" /></tr>
<tr><td>公共電表:<td><s:property value="pub_dashboard" /></tr>
<tr><td>目前電表度數:<td><s:property value="this_degrees" /></tr>
<tr><td>每度電費:<td><s:property value="times" /></tr>
<tr><td>連絡地址:<td><s:property value="address" /></tr>
</table><p/>
<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>

</body>
</html>
