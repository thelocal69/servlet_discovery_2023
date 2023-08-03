<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/lib/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5">
		<form class="form-control" action='<c:url value="/demojsp" />' method="post">
			<div class="">
				<h1>QUẢN LÍ SẢN PHẨM</h1>
				<div class="mb-3" style="width: 40%">
					<label for="exampleFormControlInput1" class="form-label">Tên
						sản phẩm</label> <input type="text" class="form-control"
						id="exampleFormControlInput1" name="name">
				</div>
				<div class="mb-3" style="width: 40%">
					<label for="exampleFormControlInput1" class="form-label">Số
						Lượng:</label> <input type="text" class="form-control"
						id="exampleFormControlInput1" name="quantity">
				</div>
				<div class="mb-3" style="width: 40%">
					<label for="exampleFormControlInput1" class="form-label">Giá
						bán:</label> <input type="text" class="form-control"
						id="exampleFormControlInput1" name="price">
				</div>
				<input type="hidden" name="count">
				<div class="col-auto">
					<button type="submit" class="btn btn-primary mb-3">Lưu lại</button>
				</div>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên sản phẩm</th>
						<th>Số lượng</th>
						<th>Giá bán</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach items="${listStudent}" var="item" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${item.name}</td>
								<td>${item.quantity}</td>
								<td>${item.price}</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous">
	
	</script>
</body>
</html>