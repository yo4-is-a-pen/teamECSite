<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/home.css">
<link rel="stylesheet" href="./css/productDetails.css">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>商品詳細</h1>
<s:if test="dto != null">
	<s:form action="AddCartAction">
	<div class="box1">
	<div class="2column-container">
	<div class="left">
		<img src='<s:property value="dto.imageFilePath"/>/<s:property value="dto.imageFileName"/>' class="item-image-box-320"/><br>
	</div>
	<div class="right">
	<table class="vertical-list-table-mini">
		<tr>
		<th scope="row"><s:label value="商品名"/></th>
		<td><s:property value="dto.productName"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="商品名ふりがな"/></th>
		<td><s:property value="dto.productKana"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="値段"/></th>
		<td><s:property value="dto.price"/>円</td>
		</tr>
		<tr>
		<th scope="row"><s:label value="購入個数"/></th>
		<td><s:select name="productCount" list="%{productCountList}"/>個</td>
		</tr>
		<tr>
		<th scope="row"><s:label value="発売会社名"/></th>
		<td><s:property value="dto.Company"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="発売年月日"/></th>
		<td><s:property value="dto.releaseDate"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="商品詳細情報"/></th>
		<td><s:property value="dto.productDescription"/></td>
		</tr>
		</table>
	</div>
	</div>
	<s:hidden name="productId" value="%{dto.productId}"/>
	</div>
	<div class="submit_btn_box">
	<s:submit value="カートに追加" class="submit_btn" />
	</div>
	</s:form>
	<s:if test="relatedProductList!=null && relatedProductList.size()>0">
		<div class="box2">
			<div class="product-details-recommend-box">
			<h2>【 関連商品 】</h2>
			<s:iterator value="relatedProductList">
					<div class="recommend-box">
					<a href='<s:url action="ProductDetailAction">
					<s:param name="productId" value="%{productId}"/>
					</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-100"/>
					<s:property value="productName"/><br>
					</a>
					</div>
			</s:iterator>
			</div>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="info">
		商品の詳細情報がありません。
	</div>
</s:else>
</div>
</body>
</html>