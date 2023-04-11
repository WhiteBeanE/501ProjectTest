package com.oracle.ProjectTest.domain;


import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Category {
	@Id
	private Long categoryNo;
	private String item;
	private Long upperCategoryNo;
}
