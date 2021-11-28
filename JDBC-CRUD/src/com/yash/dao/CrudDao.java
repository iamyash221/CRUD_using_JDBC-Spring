package com.yash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.yash.bean.CrudBean;
import com.yash.util.DBConnection;

public class CrudDao 
{
	public int Insert(CrudBean cBean)
	{
		String insert = "INSERT INTO mine (id,name,age) VALUES (?,?,?)";
		Connection conn = DBConnection.getDbInstance();
		PreparedStatement pstmt = null;
		int rowsEffected = 0;
		
		if(conn != null)
		{
			try 
			{
				pstmt = conn.prepareStatement(insert);
				 
				pstmt.setInt(1, cBean.getId());
				pstmt.setString(2, cBean.getName());
				pstmt.setInt(3, cBean.getAge());
				 
				rowsEffected = pstmt.executeUpdate();
				 
				if(rowsEffected>0)
				{
					System.out.println("NEW USER INSERTED");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("DB NOT CONNECTED");
		}
		return rowsEffected;
	}
	
	public int Update(int id,CrudBean cBean)
	{
		String update = "UPDATE mine SET name = ?, age = ? WHERE id = ?";
		Connection conn = DBConnection.getDbInstance();
		PreparedStatement pstmt = null;
		int rowsEffected = 0;
		
		if(conn != null)
		{
			try 
			{
				pstmt = conn.prepareStatement(update);
				pstmt.setString(1, cBean.getName());
				pstmt.setInt(2, cBean.getAge());
				pstmt.setInt(3, id);
				
				rowsEffected = pstmt.executeUpdate();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("DB NOT CONNECTED");
		}
		return rowsEffected;
	}
	
	public int Delete(int id)
	{
		String delete = "DELETE FROM mine WHERE id = ? ";
		Connection conn = DBConnection.getDbInstance();
		PreparedStatement pstmt = null;
		int rowsEffected = 0;
		if(conn!=null)
		{
			try
			{
				pstmt = conn.prepareStatement(delete);
				pstmt.setInt(1,id);
				rowsEffected = pstmt.executeUpdate();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("DB NOT CONNECTED");
		}
		return rowsEffected;
	}
	
	public ArrayList<CrudBean> ListAll()
	{
		String listall = "SELECT * FROM mine";
		Connection conn = DBConnection.getDbInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CrudBean> list = new ArrayList<CrudBean>();
		CrudBean cBean = new CrudBean();
		
		if(conn!=null)
		{
			try
			{
				pstmt = conn.prepareStatement(listall);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					cBean = new CrudBean(rs.getInt(1), rs.getString(2), rs.getInt(3));
					list.add(cBean);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("DB NOT CONNECTED");
		}
		return list;
	}

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int id,age;
		String name;
		CrudBean cbean = null;
		CrudDao cdao = null;
		int rowsEffected = 0;
		
		while(true)
		{
			int ch;
			System.out.println("*****JDBC CRUD*****\n");
			System.out.println("1 --- INSERT");
			System.out.println("2 --- UPDATE");
			System.out.println("3 --- DELETE");
			System.out.println("4 --- LIST ALL");
			System.out.println("0 --- EXIT");
			System.out.println("ENTER YOUR CHOICE : ");
			ch = sc.nextInt();
		
			switch(ch)
			{
			case 1:
	//			-------------INSERT-----------
				
				System.out.println("ENTER YOUR ID, NAME  & AGE : ");
				id = sc.nextInt();
				name = sc.next();
				age = sc.nextInt();
				cbean = new CrudBean(id, name, age);
				cdao = new CrudDao();
				rowsEffected = cdao.Insert(cbean);
				if (rowsEffected>0) 
				{
					System.out.println("RECORD INSERTED : " +rowsEffected);
				} 
				else 
				{
					System.out.println("RECORD NOT INSERTED : " +rowsEffected);
				}
				break;
			
			case 2:
	//			-------------UPDATE-----------
				
				System.out.println("ENTER THE ID YOU WANT TO UPDATE : ");
				id = sc.nextInt();
				cbean = new CrudBean();
				cbean.update();
				cbean.setId(id);
				cdao = new CrudDao();
				rowsEffected = cdao.Update(id,cbean);
				if (rowsEffected>0)
				{
					System.out.println("RECORD UPDATED : " +rowsEffected);
				} 
				else 
				{
					System.out.println("RECORD NOT UPDATED : " +rowsEffected);
				}
				break;
				
			case 3:
	//			-------------DELETE-----------
				
				System.out.println("ENTER ID YOU WANT TO DELETE : ");
				id = sc.nextInt();
				cdao = new CrudDao();
				rowsEffected = cdao.Delete(id);
				if(rowsEffected>0)
				{
					System.out.println("RECORD DELETED : " +rowsEffected);
				}
				else
				{
					System.out.println("RECORD NOT DELETED : " +rowsEffected);
				}
				break;
				
			case 4:
	//			-------------LIST-----------
				
				cdao = new CrudDao();
				ArrayList<CrudBean> list = cdao.ListAll();
				Iterator<CrudBean> itr = list.iterator();
				
				while(itr.hasNext())
				{
					CrudBean cBean = (CrudBean) itr.next();
					System.out.println(cBean.getId() + " " + cBean.getName() + " " + cBean.getAge());
				}
				break;
				
			case 0:
	//			-------------EXIT-----------
				
				System.exit(0);
				break;
				
			default:
	//			-------------DEFAULT-----------
				
				System.out.println("WRONG INPUT");	
			}
		}
	}
}
