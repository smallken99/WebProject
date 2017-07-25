<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@page import="com.smallken.util.DATE"%>

<%
String sysdate = DATE.getNowTime("yyy-MM-dd");
%>
<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
</head>
<body>
<s:set var="sysdate"><%=sysdate%></s:set>
<s:form action="contracts_insert">  
<s:textfield name="room" label="房號" ></s:textfield>  
<s:textfield name="begin_date" label="租約起始日"    value="%{sysdate}"  tooltip="ex.2015-07-22"/>  
<s:textfield name="name" label="房客姓名"></s:textfield>  
<s:textfield name="end_date" label="租約到期日"  tooltip="ex.2016-07-21"></s:textfield>  
<s:textfield name="cell_phone" label="手機號碼"></s:textfield>  
<s:textfield name="nick_name" label="稱呼"></s:textfield>  
<s:textfield name="rent_amt" label="每月租金"></s:textfield>  
<s:textfield name="diposit" label="押金"></s:textfield>  
<s:select name="pub_dashboard"  label="公共電表"  list="{'','B0'}"  />
<s:textfield name="this_degrees" label="目前電表度數"></s:textfield>  
<s:textfield name="times" label="每度電費" value="4.5"></s:textfield>  
<s:textfield name="address" label="連絡地址"></s:textfield>  
<s:submit value="新增" />
<s:submit value="回首頁" action="contracts" />  
 
</s:form>

</body>
</html>

