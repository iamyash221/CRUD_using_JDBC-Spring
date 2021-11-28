package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bean.ProductBean;
import com.dao.ProductDao;



@Controller
public class ProductController 
{	
	@Autowired
	ProductDao productDao;

	@GetMapping("/newproduct")
	public String newProduct(Model model) 
	{
		model.addAttribute("product", new ProductBean());
		return "NewProduct";
	}

	@PostMapping("/saveproduct")
	public String saveProduct(@ModelAttribute("product") @Valid ProductBean productBean, BindingResult result, Model model) 
	{
		if (result.hasErrors()) 
		{
			model.addAttribute("product", productBean);
			return "NewProduct";
		} 
		else 
		{
			productDao.insertProduct(productBean);
			return "Home";
		}
	}

	@GetMapping("/listproduct")
	public String listProduct(Model model) 
	{
		List<ProductBean> product = productDao.displayProduct();
		model.addAttribute("product", product);
		return "ListProduct";
	}

	@GetMapping("/deleteproduct")
	public String deleteProduct(HttpServletRequest request) 
	{
		int productId = Integer.parseInt(request.getParameter("productId"));
		productDao.deleteProduct(productId);
		return "redirect:/listproduct";
	}

	@GetMapping("/editproduct")
	public String editProduct(Model model, HttpServletRequest request) 
	{
		int productId = Integer.parseInt(request.getParameter("productId"));
		ProductBean product = productDao.getProductById(productId);
		model.addAttribute("product", product);
		return "EditProduct";
	}

	@PostMapping("/updateproduct")
	public String updateProduct(ProductBean product) 
	{
		productDao.updateProduct(product);	
		return "redirect:/listproduct";
	}
}
