package com.example.demo.common;

import java.sql.*;

import lombok.Getter;

@Getter
public class MysqlConnection {
	private Connection con;
	private String url;
	private String name;
	private String pass;
	public MysqlConnection(String url, String name, String pass) {
		super();
		this.url = url;
		this.name = name;
		this.pass = pass;
	}
	public void open() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url, name, pass);
	}
	public Statement getStatement() throws SQLException {
		return con.createStatement();
	}
	public PreparedStatement getPrepareStatement(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public void close() throws SQLException {
		con.close();
	}
	@Override
	public String toString() {
		return url +" \n "+name+" \n "+pass;
	}
}
