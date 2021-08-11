package com.everis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					//SAZ Eval
					//"jdbc:sqlserver://172.18.191.37:1433;databasename=EDB_ABI_001;user=atm_test;password=brasil@2021");
					//GU QA
					"jdbc:sqlserver://172.18.191.11:1433;databasename=EDB_ABI_001;user=atm_test;password=brasil@2021");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
