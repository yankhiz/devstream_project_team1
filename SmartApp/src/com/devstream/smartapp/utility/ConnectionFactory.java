package com.devstream.smartapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {

	// private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String CONNECTION_URL = "jdbc:postgres://10.15.1.80:5432/smart_team_1";
	private static final String DB_USER = "postgres";
	private static final String DB_PASWORD = "postgres";
	private static final String PSQL_DRIVER = "org.postgresql.Driver";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(PSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASWORD);
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}

}
