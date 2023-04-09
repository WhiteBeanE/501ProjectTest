package com.oracle.ProjectTest.domain;

import lombok.Data;

@Data
public class Category {
	private Long category_no;
	private String item;
	private Long upper_category_no;
}
