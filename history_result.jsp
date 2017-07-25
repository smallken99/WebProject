<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  


<table border="1">
<tr><td>
輸入日期<td>房號<td>姓名<td>上次<br>度數<td>本次<br>度數<td>公共<br>電費<td>個人<br>電費<td>租金<td>押金<td>應繳<br>租金<td>訊息<td><td>
</td></tr>

<s:iterator value="historys">    
	<tr><td>
	<s:property value="input_date" /><td>
	<s:property value="room" /><td>
	<s:property value="name" /><td>
	<s:property value="last_degrees" /><td>
	<s:property value="This_degrees_t" /><td>
	<s:property value="pub_electric_amt" /><td>
    <s:property value="electric_amt" /><td>	
    <s:property value="rent_amt" /><td>
	<s:property value="diposit_amt" /><td>
	<s:property value="total_amt" /><td>
	<s:property value="message" /><td>
	<s:url action="History_delete"  var="deleteLink">
		 <s:param name="input_date"><s:property value="input_date" /></s:param>
		 <s:param name="room"><s:property value="room" /></s:param>
		 <s:param name="last_degrees"><s:property value="last_degrees" /></s:param>
		 <s:param name="This_degrees_t"><s:property value="This_degrees_t" /></s:param>
	</s:url>
	<s:url action="History_query" var="queryLink2">
	<s:param name="room"><s:property value="room" /></s:param>
	<s:param name="ismore">Y</s:param>
	</s:url>	
    <input type="button" value="刪除" onclick="self.location.href='${deleteLink}'"/><td>	
	</td></tr>
</s:iterator>
</table>

<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>
<a href="${queryLink2}">更多...</a>
