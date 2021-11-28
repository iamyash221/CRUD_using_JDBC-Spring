package com.bean;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
public class ProductBean 
{
	int productId,productQty;
	
	@NotEmpty(message = "enter name")
	String productName;
	
	float productPrice;
	
	public int getProductId() 
	{
		return productId;
	}
	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	public int getProductQty() 
	{
		return productQty;
	}
	public void setProductQty(int productQty) 
	{
		this.productQty = productQty;
	}
	public String getProductName() 
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public float getProductPrice() 
	{
		return productPrice;
	}
	public void setProductPrice(float productPrice) 
	{
		this.productPrice = productPrice;
	}
}
