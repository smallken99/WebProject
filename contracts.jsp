<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  


<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
<script language="JavaScript">
	function confDel(url){
		var conf = confirm("是否刪除?");
		if(conf){
			 window.location.replace(url);
		}else{
			return false;
		}	
	}

</script>

</head>
<body>

<table border="1">
	<tr><td>
	房號<td>姓名<td>租約起始<td>租約結束<td>租金<td>電表度數
	<td>.<td>.<td>.
	</td></tr> 
<s:iterator value="contracts">
	<tr><td>

	<s:property value="room" /><td>
<s:url action="contracts_query" var="queryLink">
<s:param name="room"><s:property value="room" /></s:param>
<s:param name="begin_date"><s:property value="begin_date" /></s:param>
</s:url>		
	<a href="${queryLink}"><s:property value="name" /></a><td>	
	<s:property value="begin_date" /><td>
	<s:if test="ispreline">
      <font color="red">
    </s:if>
	<s:property value="end_date" /><td>
    <s:property value="rent_amt" /><td>
	<s:property value="this_degrees" /><td>


<s:url action="contracts_delete" var="deleteLink">
  <s:param name="room"><s:property value="room" /></s:param>
  <s:param name="begin_date"><s:property value="begin_date" /></s:param>
</s:url>
	<input type="button" value="新增記錄" onclick="self.location.href='History?room=<s:property value="room" />'"/><td>
	<input type="button" value="繳納記錄"  onclick="self.location.href='History_query?room=<s:property value="room" />'"/><td>	
    <input type="button" value="刪除" onclick="confDel('${deleteLink}');"><td>	

	</td></tr></font>
</s:iterator>
</table>

<input type="button" value="新增租約" onclick="self.location.href='new.jsp'"/>
<input type="button" value="設定公共電費" onclick="self.location.href='pub_dashboard'"/>

</body>
</html>