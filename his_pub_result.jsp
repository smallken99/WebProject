<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>
<input type="button" value="回上一頁" onclick="history.go(-1)"/>
<table border="1">
<tr><td>
電表<td>輸入日期<td>上次度數<td>本次度數<td>電費<td>每人分攤電<td>刪除
</td></tr>
<s:iterator value="dtsf04s">    
	<tr><td>
	<s:property value="dashboard" /><td>
	<s:property value="input_date" /><td>
	<s:property value="last_degrees" /><td>
	<s:property value="This_degrees" /><td>
    <s:property value="electric_amt" /><td>	
    <s:property value="avg_amt" /><td>
	<s:url action="pub_dashboard_delete"  var="deleteLink">
		<s:param name="dashboard"><s:property value="dashboard" /></s:param>
		 <s:param name="input_date"><s:property value="input_date" /></s:param>
	</s:url>
    <input type="button" value="刪除" onclick="self.location.href='${deleteLink}'"/><td>	
	</td></tr>
</s:iterator>
</table>


