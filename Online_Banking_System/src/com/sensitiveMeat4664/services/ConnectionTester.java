package com.sensitiveMeat4664.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.sensitiveMeat4664.utility.DBConnectionUtil;

public class ConnectionTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			if(conn != null) System.out.println("connected");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
