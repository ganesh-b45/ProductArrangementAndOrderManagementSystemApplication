package org.product.arrangement.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConfig {
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static DBConfig db = null;

	private DBConfig() {
		try {

			Properties p = new Properties();
			p.load(PathHelper.fin);
			String driverClassName = p.getProperty("driver.classname");
//			System.out.println(driverClassName);
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			String url = p.getProperty("db.url");
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			if (conn != null) {
				System.out.println("Database is Connected");
			} else {
				System.out.println("Database is not connected");
			}
		} catch (Exception e) {
			System.err.println("Error is :" + e);
		}

	}

	public static DBConfig getDBInstance() {
		if (db == null) {
			db = new DBConfig();
		}
		return db;
	}

	public static Connection getConnection() {
		return conn;

	}

	public static PreparedStatement getPreparedStatement() {
		return stmt;
	}

	public static ResultSet getResultSet() {
		return rs;
	}

}