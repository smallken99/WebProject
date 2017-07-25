<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<html>
<head>
<meta name="viewport" content="width=device-width">
<title>JSP for RentManageForm form</title>
</head>
<body>

<script type="text/javascript">
	//清除文字(onfocus)
	function cleartext(id) {
		id.value = "";
	}
	
	function putzero(id){
		if(id.value==''){
			id.value="0";
		}		
	}
	
	function selectAll(id){
		id.select();
		copyToClipboard(id.value);
	}
	
	function copyToClipboard(txt)
	{
		if(window.clipboardData)
		{
			window.clipboardData.clearData();
			window.clipboardData.setData("Text", txt);
		}
		else if(navigator.userAgent.indexOf("Opera") != -1)
		{
			window.location = txt;
		}
		else if (window.netscape)
		{
			try
			{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}
				catch (e)
				{
					alert("被瀏覽器拒絕！\n請在瀏覽器地址欄輸入'about:config'之後退回\n將'signed.applets.codebase_principal_support'設置為'true'");
				}
				var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
				if (!clip)
					return;
				var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
				if (!trans)
					return;
				trans.addDataFlavor('text/unicode');
				var str = new Object();
				var len = new Object();
				var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
				var copytext = txt;
				str.data = copytext;
				trans.setTransferData("text/unicode",str,copytext.length*2);
				var clipid = Components.interfaces.nsIClipboard;
				if (!clip)
					return false;
				clip.setData(trans,null,clipid.kGlobalClipboard);
		}
	}
</script>
<s:form action="History">  
<input type="button" value="回首頁" onclick="self.location.href='contracts'"/>
<s:textfield name="room" label="房號" ></s:textfield>  
<s:textfield name="input_date" label="日期" ></s:textfield>  
<s:textfield name="name" label="房客姓名"></s:textfield>  
<s:textfield name="last_degrees" label="上次電表"></s:textfield>  
<s:textfield name="this_degrees_t" label="本次電表度數"  onfocus="cleartext(this)"  onblur="putzero(this)"></s:textfield>  
<s:textfield name="rent_amt" label="租金"></s:textfield> 
<s:textfield name=" pub_electric_amt" label="公共電費"></s:textfield> 
<s:textfield name="electric_amt" label="個人電費"></s:textfield>  
<s:textfield name="diposit_amt" label="押金" onfocus="cleartext(this)"  onblur="putzero(this)"></s:textfield>  
<s:textfield name="total_amt" label="應繳房租"></s:textfield>   
<s:textarea name="message" label="訊息"   cols="22" rows="3" onfocus="selectAll(this)"></s:textarea>  
<s:hidden   name="times" />
<s:hidden name="pub_dashboard"/>
<s:submit value="計算"  action="History_calculate" />
<s:submit value="新增"  action="History_insert"  />  
</s:form>

</body>
</html>