package com.oracle.ProjectTest.domain;


import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Category {
	@Id
	private Long category_no;
	private String item;
	private Long upper_category_no;
}
