package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bean.ProductBean;

public class ProductDao 
{
	@Autowired
	JdbcTemplate stmt;
	
	public void insertProduct(ProductBean productBean)
	{
		int record = stmt.update("INSERT INTO product (productName,productPrice,productQty) VALUES 	(?,?,?)",
									productBean.getProductName(),productBean.getProductPrice(),productBean.getProductQty());
		
		System.out.println(record + " :: inserted...");
	}
	
	public List<ProductBean> displayProduct()
	{
		List<ProductBean> products = stmt.query("SELECT * FROM product", 
													new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
		
		return products;
	}
	
	
	public void deleteProduct(int productId)
	{
		int record = stmt.update("DELETE FROM product WHERE productId = ?", productId);
		
		System.out.println(record + " :: deleted...");
	}
	
	@SuppressWarnings("deprecation")
	public ProductBean getProductById(int productId) 
	{
		ProductBean productBean = stmt.queryForObject("SELECT * FROM product WHERE productId = ?", 
														new Object[] {productId}, new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
		
		return productBean;
	}
	
	public void updateProduct(ProductBean productBean) 
	{
		int record = stmt.update("UPDATE product SET productName=?,productPrice=?,productQty=? where productId=?",
									productBean.getProductName(),productBean.getProductPrice(),productBean.getProductQty(),productBean.getProductId());
		
		System.out.println(record + " :: updated");
	}
}
