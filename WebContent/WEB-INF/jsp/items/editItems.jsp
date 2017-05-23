<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/form.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
    	//修改提交判断
    	$('input[type=submit]').click(function(){
    		//表单验证
    		$("#editform").validate({
    			rules : {
    				trainNo : "required",
    				deStation : "required",
    				arrStation : "required",
    				deTime : "required",
    				arrTime : "required",
    				arrStation : "required",
    				duration : "required",
    				ttype : "required",
    				mile : "required"
    			},
    			success: function(label) {
    			    label.html("&nbsp;").addClass("checked");
    			}
    		});
    		
    		//提醒用户是否修改用户内容
    		var flag=confirm("您确定要修改这条记录么？");
    		if(flag){
    			$('#editform').submit();
    		}else{
    			return false;
    		}
    	});
    	//返回火车信息列表
    	$('input[type=reset]').click(function(){
    		location.href="<%=request.getContextPath()%>
	/trainList.do?pageNo=${param.pageNo}";
						});

		//设置历时字段内容
		$(":input[name=arrTime]").change(function() {
			var deTime = $(":input[name=deTime]").val().split(":");
			var arrTime = $(":input[name=arrTime]").val().split(":");
			if (arrTime[1] < deTime[1]) {
				var hour = Number(arrTime[0]) - Number(deTime[0]) - 1;
				var min = Number(arrTime[1]) + 60 - Number(deTime[1]);
			} else {
				var hour = Number(arrTime[0]) - Number(deTime[0]);
				var min = Number(arrTime[1]) - Number(deTime[1]);
			}
			$(":input[name=duration]").val(hour + ":" + min);
		});

		$(":input[name=deTime]").change(function() {
			var deTime = $(":input[name=deTime]").val().split(":");
			var arrTime = $(":input[name=arrTime]").val().split(":");
			if (arrTime[1] < deTime[1]) {
				var hour = Number(arrTime[0]) - Number(deTime[0]) - 1;
				var min = Number(arrTime[1]) + 60 - Number(deTime[1]);
			} else {
				var hour = Number(arrTime[0]) - Number(deTime[0]);
				var min = Number(arrTime[1]) - Number(deTime[1]);
			}
			$(":input[name=duration]").val(hour + ":" + min);
		});

		//给select赋值
	});
</script>
<style type="text/css">
input {
	border-radius: 5px;
}
</style>
</head>
<body>
	<center>
		<h1>商品详情</h1>
		<form id="editform"
			action="<%=request.getContextPath()%>/items/editItemsSubmit.action?pageNo=1"
			method="post">
			<fieldset>
				<div>
					<label for="itemsId">商品ID：</label> <input type="text"
						name="itemsId" id="itemsId" value="${itemsCustom.id }"
						readonly="readonly" />
				</div>
				<div>
					<label for="name">* 商品名称：</label> <input type="text" name="name"
						id="name" value="${itemsCustom.name }" />
				</div>
				<div>
					<label for="price">* 商品价格：</label> <input type="text" name="price"
						id="price" value="${itemsCustom.price }" />
				</div>
				<div>
					<label for="pic">商品图片：</label> <input type="text" name="pic"
						id="pic" value="${itemsCustom.pic }" />
				</div>
				<div>
					<label for="detail">商品详情：</label>
					<textarea name="detail" id="detail" rows="3" cols="30">${itemsCustom.detail }</textarea>
				</div>
				<br /> <br />
				<div>
					<label for="createtime">商品创建时间：</label> <input type="text"
						name="createtime" id="createtime"
						value="<fmt:formatDate value="${itemsCustom.createtime }"  pattern="yyyy-MM-dd"/>" />
				</div>
				<br /> <br />
				<div>
					<input type="submit" value="确定" /> &nbsp; <input type="reset"
						value="返回" />
				</div>
			</fieldset>
		</form>
		<c:if test="${allErrors!=null }">
			<c:forEach items="${allErrors }" var="error">
	        ${error.defaultMessage } <br />
	        </c:forEach>
		</c:if>
	</center>
</body>
</html>