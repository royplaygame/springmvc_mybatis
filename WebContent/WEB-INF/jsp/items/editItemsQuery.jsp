<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>批量修改商品信息列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//鼠标hover事件
		$("#tb tr").hover(function() {
			$(this).addClass("trbgclolor");
		}, function() {
			$(this).removeClass("trbgclolor");
		});
	});

</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/list.css" />
<style type="text/css">
li span {
	float: right;
}

.trbgclolor {
	background-color: green;
}
</style>
</head>
<body>
	<h1>批量修改商品信息列表</h1>
	
	<form id="myForm"
		action="${pageContext.request.contextPath }/items/editItemsAllSubmit.action"
		method="post">
		<table width="100%" border="1" id="tb">
			<tr>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>价格</th>
				<th>商品详情</th>
				<th>商品图片</th>
				<th>创建时间</th>
			</tr>
			<c:choose>
				<c:when test="${!empty requestScope.list }">
					<c:forEach items="${requestScope.list }" var="item" varStatus="status">
						<tr>
							<td><input type="text" name="itemsList[${status.index }].id" value="${item.id }" readonly="readonly"/></td>
							<td><input type="text" name="itemsList[${status.index }].name" value="${item.name }"/></td>
							<td><input type="text" name="itemsList[${status.index }].price" value="${item.price }"/></td>
							<td><input type="text" name="itemsList[${status.index }].detail" value="${item.detail }"/></td>
							<td><input type="text" name="itemsList[${status.index }].pic" value="${item.pic }"/></td>
							<td><input type="text" name="itemsList[${status.index }].createtime" value="<fmt:formatDate value="${item.createtime}" type="date" />"/></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">暂时没有数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<br/>
		<br/>
		<input type="submit" value="保存" />
	</form>
</body>
</html>