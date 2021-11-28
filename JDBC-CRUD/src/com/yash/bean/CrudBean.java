package com.yash.bean;

import java.util.Scanner;

public class CrudBean 
{
	private int id,age;
	private String name;

	public CrudBean(int id, String name, int age) 
	{
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public CrudBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void update() 
	{
		System.out.println("ENTER NAME, AGE : ");
		Scanner sc = new Scanner(System.in);
		name = sc.next();
		age = sc.nextInt();
	}
}