package com.internousdev.arizona.dto;

import java.util.Date;

public class ProductInfoDTO {

		private int id;
		private int productId;
		private String productName;
		private String productKana;
		private String company;
		private Date releaseDate;
		private String productDescription;
		private int price;
		private int categoryId;
		private String imageFilePath;
		private String imageFileName;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id=id;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductId(int productId){
		this.productId=productId;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName=productName;;
	}

	public String getProductKana(){
		return productKana;
	}

	public void setProductKana(String productKana){
		this.productKana=productKana;
	}

	public String getCompany(){
		return company;
	}

	public void setCompany(String company){
		this.company=company;
	}

	public Date getReleaseDate(){
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate){
		this.releaseDate=releaseDate;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public void setProductDescription(String productDescription){
		this.productDescription=productDescription;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price=price;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(int categoryId){
		this.categoryId=categoryId;
	}

	public String getImageFilePath(){
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath){
		this.imageFilePath=imageFilePath;
	}

	public String getImageFileName(){
		return imageFileName;
	}

	public void setImageFileName(String imageFileName){
		this.imageFileName=imageFileName;
	}

}
