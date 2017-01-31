package com.sistemacontatos.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionDatabase {

	private static ConnectionDatabase instance;

	private Connection conexao;
	private PreparedStatement stm;

	private ConnectionDatabase() {
	}

	public static ConnectionDatabase getInstance() {

		if (instance == null) {
			instance = new ConnectionDatabase();
		}

		return instance;

	}

	public void openConnection() throws SQLException {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/contatos", "root", "R00T");

		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			throw new SQLException(e);
		}
	}

	public void closeConnection() {
		try {
			stm.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// update, insert, delete
	public PreparedStatement preparandoStatement(String sql) throws SQLException{
		try {
			openConnection();
			stm = conexao.prepareStatement(sql);
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		return stm;
	}

	public void executaUpdate(String sql, PreparedStatement stm) throws SQLException{
		try {
			stm.execute();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			throw new SQLException(e);
		}
	}

	// por enquanto fica void
	public ResultSet executaQuery(String sql, PreparedStatement stm) throws SQLException{
		ResultSet rs = null;
		try {
			rs = stm.executeQuery();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		return rs;
	}

}
