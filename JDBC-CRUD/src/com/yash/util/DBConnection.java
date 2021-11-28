package com.yash.util;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.yash.util.DBConnection;

public class DBConnection 
{	 
	public static Connection conn = null;

	public static Connection getDbInstance() 
	{
		if (conn==null) 
		{
			conn =  getConnection();
		}
		return conn;
	}
	public static Connection getConnection() 
	{
		FileInputStream fin = null;
		Properties prop = new Properties();
		try 
		{
			fin = new FileInputStream("D:\\Coding\\Eclipse\\jdbc_general_batch\\dbconfig.properties");
			try 
			{
				prop.load(fin);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		
		
		String URLNAME = prop.getProperty("URLNAME");
		String DRIVERCLASS = prop.getProperty("DRIVERCLASS");
		String USERNAME = prop.getProperty("USERNAME");
		String PASSWORD = prop.getProperty("PASSWORD");
		
		Connection conn = null;
		try 
		{
			Class.forName(DRIVERCLASS);
		
			conn = DriverManager.getConnection(URLNAME, USERNAME, PASSWORD);
			
			if (conn!= null) 
			{
				System.out.println("Db Connected : " + conn);
			} 
			else 
			{
				System.out.println("Db not Connected : " + conn);
			}
		
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args)
	{
		System.out.println("DBConnection.getConnection(): " + DBConnection.getConnection());
	}
}