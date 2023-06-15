package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.demo.common.MysqlConnection;
import com.example.demo.common.DAO.StudentDAO;

@Configuration
@PropertySource("classpath:database.properties")
public class StrConfig {
	@Bean
	public MysqlConnection getMysql(@Value("${db.name}")String name,@Value("${db.url}")String url,@Value("${db.pass}")String pass) {
		return new MysqlConnection(url, name, pass);
	}
	@Bean
	public StudentDAO getS(MysqlConnection m) {
		return new StudentDAO(m);
	}
}
