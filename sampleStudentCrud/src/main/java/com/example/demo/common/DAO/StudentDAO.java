package com.example.demo.common.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.MysqlConnection;
import com.example.demo.common.DTO.StudentCreateDTO;
import com.example.demo.common.bean.StudentBean;

public class StudentDAO {
	private MysqlConnection c;
	public StudentDAO(MysqlConnection c) {
		this.c=c;
	}
	public List<StudentBean> getAll() throws SQLException , ClassNotFoundException{
		c.open();
		Statement s=c.getStatement();
		ResultSet rs=s.executeQuery("select * from student;");
		List<StudentBean> beans=new ArrayList<StudentBean>();
		while(rs.next()) {
			StudentBean b=new StudentBean(rs.getInt(1),rs.getString(2),rs.getInt(3));
			beans.add(b);
		}
		c.close();
		rs.close();
		return beans;
	}
	public StudentBean detail(int id) throws SQLException , ClassNotFoundException{
		c.open();
		PreparedStatement s=c.getPrepareStatement("select * from student where no=?;");
		s.setInt(1, id);
		ResultSet rs=s.executeQuery();
		StudentBean b=null;
		while(rs.next()) {
			b=new StudentBean(rs.getInt(1),rs.getString(2),rs.getInt(3));
		}
		c.close();
		rs.close();
		return b;
	}
	public String insert(StudentCreateDTO dto) throws SQLException , ClassNotFoundException{
		String message="Error Occour";
		c.open();
		PreparedStatement s=c.getPrepareStatement("insert into student(name,score) values(?,?);");
		s.setString(1, dto.getName());
		s.setInt(2, dto.getScore());
		int rs=s.executeUpdate();
		if(rs>0) {
			message = "added Successfully";
		}
		c.close();
		return message;
	}
	public String update(StudentCreateDTO dto,int id) throws SQLException , ClassNotFoundException{
		String message="Error Occour";
		c.open();
		PreparedStatement s=c.getPrepareStatement("update student set name=?,score=? where no=?;");
		s.setString(1, dto.getName());
		s.setInt(2, dto.getScore());
		s.setInt(3, id);
		
		int rs=s.executeUpdate();
		if(rs>0) {
			message = "Updated Successfully";
		}
		c.close();
		return message;
	}
	public String delete(int id) throws SQLException , ClassNotFoundException{
		String message="Error Occour";
		c.open();
		PreparedStatement s=c.getPrepareStatement("delete from student where no=?;");
		s.setInt(1, id);
		int rs=s.executeUpdate();
		if(rs>0) {
			message = "Deleted Successfully";
		}
		c.close();
		return message;
	}
	public String toString() {
		return c.getUrl() +" \n "+c.getName()+" \n "+c.getPass();
	}
}
