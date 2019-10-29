package com.internousdev.arizona.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.arizona.dao.ProductInfoDAO;
import com.internousdev.arizona.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailAction extends ActionSupport implements SessionAware{

	private int productId;
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductList;
	private ProductInfoDTO dto=new ProductInfoDTO();
	private Map<String, Object> session;

	public String execute(){

	try{
		ProductInfoDAO dao = new ProductInfoDAO();
		dto=dao.getProductInfoByProductId(productId);

		if(dto.getProductId()==0){
			dto=null;
		}else{
			productCountList=new ArrayList<Integer>();//購入個数のリストを作成
			for(int i=1; i<=5; i++){
				productCountList.add(i);
			}
				relatedProductList=dao.getRelatedProductList(dto.getCategoryId(), dto.getProductId(), 0, 3);
			}
	 }catch(SQLException e){
		 e.printStackTrace();
		 return ERROR;
	 }
		return SUCCESS;
	}

	public ProductInfoDTO getDto() {
		return dto;
	}

	public void setDto(ProductInfoDTO dto) {
		this.dto = dto;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductId(int productId){
		this.productId=productId;
	}

	public List<Integer> getProductCountList(){
		return productCountList;
	}

	public void setProductCountList(List<Integer> productCountList){
		this.productCountList=productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductList(){
		return relatedProductList;
	}

	public void setRelatedProductList(List<ProductInfoDTO> relatedProductList){
		this.relatedProductList=relatedProductList;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session=session;
	}
}