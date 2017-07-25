<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  


<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
</head>
<body>

<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>
<input type="button" value="回上一頁" onclick="history.go(-1)"/>
<table border="1">
	<tr><td>
	電表<td>電表名稱<td>目前度數<td>每度電費<td>分攤人數
	<td>.<td>. 
	</td></tr> 
<s:iterator value="dtsf03s">
	<tr><td>
	<s:property value="dashboard" /><td>	
	<s:property value="dashb_name	" /><td>
	<s:property value="this_degrees" /><td>
    <s:property value="times" /><td>
	<s:property value="avg_num" /><td>
	<input type="button" value="新增記錄" onclick="self.location.href='pub_dashboard_new?dashboard=<s:property value="dashboard" />'"/><td>
	<input type="button" value="繳納記錄"  onclick="self.location.href='pub_dashboard_query?dashboard=<s:property value="dashboard" />'"/><td>

	</td></tr>
</s:iterator>
</table>


</body>
</html>