package com.internousdev.arizona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.arizona.dto.ProductInfoDTO;
import com.internousdev.arizona.util.DBConnector;

public class ProductInfoDAO {

	//全件取得
	public List<ProductInfoDTO> getProductInfoListAll() throws SQLException{

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();

		String sql="SELECT * FROM product_info";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();

				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCompany(rs.getString("release_company"));
				productInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}

		return productInfoDTOList;
	}

	//商品ID検索
	public ProductInfoDTO getProductInfoByProductId(int productId) throws SQLException{

		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		ProductInfoDTO dto=new ProductInfoDTO();

		String sql="SELECT * FROM product_info WHERE product_id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,productId);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCompany(rs.getString("release_company"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return dto;
	}

	//関連商品
	public List<ProductInfoDTO> getRelatedProductList(int categoryId,int productId,int limitOffset,int limitRowCount) throws SQLException{
		DBConnector db =new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();

		String sql="SELECT * FROM product_info WHERE category_id=? AND product_id <> ? order by rand() limit ?,?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.setInt(2, productId);
			ps.setInt(3, limitOffset);
			ps.setInt(4, limitRowCount);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCompany(rs.getString("release_company"));
				productInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return productInfoDTOList;
	}

	//キーワードが条件の取得
	public List<ProductInfoDTO> getProductInfoListByKeyword(String[] keywordList) throws SQLException{

		//宣言部分
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		//商品情報を入れるリスト
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();

		String sql="SELECT * FROM product_info";
		boolean initializeFlag=true;

		//処理部分
		//if文 条件：リストの一番目が空ではないなら（空の場合は全件検索だから）
		if(!keywordList[0].equals("")){

			for(String keyword:keywordList){
				//initializeFlagで一回目の分岐と二回目以降の分岐で分ける
				//if文 条件：initializeFlagがtrueなら
				if(initializeFlag){

					//keyword（入力された検索ワード）をfor文で繰り返し格納する
					//その後sqlにくっつける
					sql+=" WHERE (product_name LIKE'%" + keyword + "%' OR product_name_kana LIKE '%"+ keyword + "%')";

					//フラグをfalseにする
					initializeFlag=false;

				//initializeFlagがfalseなら
				}else{

					//keyword(入力された検索ワード）をfor文で繰り返し格納する
					//その後sqlにくっつける
					sql +=" OR (product_name LIKE '%" + keyword + "%' OR product_name_kana LIKE '%" + keyword + "%')";
				}
			}
		}

		try{
			//sqlに接続準備
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			//繰り返し文 rs.next()で1つずつ移動
			//sqlから取得した情報をdtoに格納する
			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCompany(rs.getString("release_company"));

				//dtoに格納した情報をリストに格納する
				productInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return productInfoDTOList;
	}

	//カテゴリーIDとキーワードが条件の取得
	public List<ProductInfoDTO> getProductInfoListByCategoryIdAndKeyword(String categoryId,String[] keywordList) throws SQLException{

		//宣言部分
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		//商品情報を入れるリスト
		List<ProductInfoDTO> productInfoDTOList=new ArrayList<ProductInfoDTO>();

		String sql="SELECT * FROM product_info WHERE category_id=" + categoryId;
		boolean initializeFlag = true;

		//処理部分
		if(!keywordList[0].equals("")){

			for (String keyword:keywordList){

				//initializeFlagで一回目の分岐と二回目以降の分岐で分ける
				//if文 条件：initializeFlagがtrueなら
				if(initializeFlag){
					sql +=" AND ((product_name LIKE '%" + keyword + "%' OR product_name_kana LIKE '%" + keyword + "%')";

					//フラグをfalseにする
					initializeFlag=false;

				//条件：initializeFlagがfalseなら
				}else{
					sql += " OR (product_name LIKE '%" + keyword + "%' OR product_name_kana LIKE '%" + keyword + "%')";

				}
			}
			sql +=")";
		}

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			//繰り返し文 rs.next()で1つずつ移動
			//sqlから取得した情報をdtoに格納する
			while(rs.next()){
				ProductInfoDTO dto=new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCompany(rs.getString("release_company"));

				//dtoに格納した情報をリストに格納する
				productInfoDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return productInfoDTOList;
	}

}
