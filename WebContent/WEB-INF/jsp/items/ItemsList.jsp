<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品信息列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//各行换色
		$("#tb tr th").addClass("bclass");
		$("#tb tr:even").addClass("bgclass");
		//鼠标hover事件
		$("#tb tr").hover(function() {
			$(this).addClass("trbgclolor");
		}, function() {
			$(this).removeClass("trbgclolor");
		});

		//查看记录
		$("#viewTrain")
				.click(
						function() {
							var $checked = $("input:checked");
							if ($checked.length != 1) {
								if ($checked.length < 1) {
									alert("您没有选择记录，请选择一条记录进行查看！");
								} else {
									alert("您选择了多条记录，请选择一条记录进行查看！");
								}
							} else {
								window.location.href = "${pageContext.request.contextPath }/items/editItems.action?trainNo="
										+ $checked.val() + "&pageNo=1";
							}
						});

		//修改记录
		$("#editItems")
				.click(
						function() {
							var $checked = $("input:checked");
							if ($checked.length != 1) {
								if ($checked.length < 1) {
									alert("您没有选择记录，请选择一条记录进行修改！");
								} else {
									alert("您选择了多条记录，请选择一条记录进行修改！");
								}
							} else {
								window.location.href = "${pageContext.request.contextPath }/items/editItems.action?itemsId="
										+ $checked.val() + "&pageNo=1";
							}
						});

		//批量删除记录
		$("#deleteItems").click(function() {
			var $checked = $("input:checked");
			if ($checked.length < 1) {
				alert("请选择一条或者多条记录，再进行删除！");
			} else {
				var flag = confirm("确定要删除选中的多条记录吗？");
				if (flag) {
					$("#myForm").submit();
				}
			}
		});

		//全选实现
		$('#selectAll').click(function() {
			if (this.checked == true) {
				$('input[name=checkboxname]').prop("checked", true);
			} else {
				$('input[name=checkboxname]').removeAttr("checked");
			}

		});

		//新增火车班次，并传参数当前页面
		$('#addTrain').click(
						function() {
							var url = "${pageContext.request.contextPath }/addtrain.jsp?pageNo=1";
							window.location.href = url;
						});
		//批量修改商品
		$('#updateMoreItems').click(
				function(){
					var url = "${pageContext.request.contextPath }/items/editItemsQuery.action";
					window.location.href = url;
				});

	});

	function delconfirm(id, pageNo) {
		var url = "${pageContext.request.contextPath }/trainDelete.do?trainNo=";
		var flag = confirm("确定删除这条记录吗？");
		if (flag) {
			window.location.href = url + id + "&pageNo=" + pageNo;
		}
	}
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
	<h1>商品信息列表</h1>
	<div>
		<form
			action="${pageContext.request.contextPath }/items/queryItems.action" method="post">
			查询条件： <label for="itemsName">商品名称</label> <input type="text"
				name="itemsCustom.name" id="itemsName" /> <input
				type="submit" value="查询" />
		</form>
		<br />
	</div>
	<div id="header">
		<ul>
			<li><a href="#" id="viewTrain">查看</a><a href="#" id="addTrain">新增</a><a
				href="#" id="editItems">修改</a><a href="#" id="deleteItems">批量删除</a><a href="#" id="updateMoreItems">批量修改</a></li>
		</ul>
	</div>
	<form id="myForm"
		action="${pageContext.request.contextPath }/items/deleMoreItems.action?pageNo=1"
		method="post">
		<table width="100%" border="1" id="tb">
			<tr>
				<th><input type="checkbox" id="selectAll" /></th>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>价格</th>
				<th>商品详情</th>
				<th>商品图片</th>
				<th>创建时间</th>
				<th>删除</th>
				<th>新增</th>
			</tr>
			<c:choose>
				<c:when test="${!empty requestScope.list }">
					<c:forEach items="${requestScope.list }" var="item">
						<tr>
							<td><input type="checkbox" name="itemsIds"
								value="${item.id }" /></td>
							<td><a
								href="<%=request.getContextPath()%>/viewTrainInfo.do?trainNo=${item.id }&pageNo=1">${item.id }</a></td>
							<td>${item.name }</td>
							<td>${item.price }</td>
							<td>${item.detail }</td>
							<td>${item.pic }</td>
							<td><fmt:formatDate value="${item.createtime}" type="date" /></td>
							<td><a href="javascript:delconfirm('${item.id }',1)">删除</a></td>
							<td><a href="addtrain.jsp">新增</a></td>
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
	</form>
	<div id="footer">
		<ul>
			<li><a
				href="<%=request.getContextPath()%>/trainList.do?pageNo=1">末页</a>&nbsp;
				<a href="<%=request.getContextPath()%>/trainList.do?pageNo=1">下一页</a>&nbsp;
				<a href="<%=request.getContextPath()%>/trainList.do?pageNo=1">上一页</a>&nbsp;
				<a href="<%=request.getContextPath()%>/trainList.do?pageNo=1">首页</a>
				<span>当前共：<strong>100</strong> 记录,当前是第：<strong>10</strong>
					页&nbsp;
			</span></li>
		</ul>
	</div>
</body>
</html>