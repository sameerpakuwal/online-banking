package com.vastika.sr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root", "Nepal1432$");

	}

}
