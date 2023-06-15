package com.example.demo.common.bean;

import java.io.Serializable;

import org.springframework.core.serializer.Serializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class StudentBean implements Serializable {
	private int no;
	private String name;
	private int score;
}
