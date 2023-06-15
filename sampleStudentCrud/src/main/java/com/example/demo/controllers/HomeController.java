package com.example.demo.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.demo.common.MysqlConnection;
import com.example.demo.common.DAO.StudentDAO;
import com.example.demo.common.DTO.StudentCreateDTO;
import com.example.demo.common.bean.StudentBean;


@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private StudentDAO conn;
	@GetMapping
	public ResponseEntity<List<StudentBean>> index() {
		List<StudentBean> a=null;
		try {
			a=conn.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(a);
	}
	@GetMapping("{id}")
	public ResponseEntity<StudentBean> put(@PathVariable int id) throws ClassNotFoundException, SQLException,Exception {
		StudentBean message=conn.detail(id);
		if(message==null) {
			 return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity.ok(message);
	}
	@PostMapping
	public String post(@RequestBody StudentCreateDTO m) throws ClassNotFoundException, SQLException {
		String message=conn.insert(m);
		return message;
	}
	@PutMapping("{id}")
	public ResponseEntity<String> put(@PathVariable int id,@RequestBody StudentCreateDTO m) throws ClassNotFoundException, SQLException {
		String message=conn.update(m,id);
		return ResponseEntity.ok(message);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable int id) throws ClassNotFoundException, SQLException {
		String message=conn.delete(id);
		return ResponseEntity.ok(message);
	}
}
