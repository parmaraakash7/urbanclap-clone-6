
package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.*;

public class ConnectionModel {
	static public int FIVE_KM = 2000;
	static public Connection getConnection() throws Exception {
 		Connection c = null;
 		//c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/urbanclap-clone6","postgres", "aakash@123");
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
           
		    c = DriverManager.getConnection(dbUrl, username, password);
		return c;
		
	}
}