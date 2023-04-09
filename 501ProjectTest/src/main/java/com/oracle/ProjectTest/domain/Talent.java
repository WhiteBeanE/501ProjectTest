package com.oracle.ProjectTest.domain;


import javax.persistence.Id;

import lombok.Data;

@Data
public class Talent {
	@Id
	private Long 	TALENT_NO;
	private String 	SELLER_ID;
	private int 	UPPER_CATEGORY_NO;
	private int 	LOWER_CATEGORY_NO;
	private String 	MAIN_IMG;
	private String 	TITLE;
	private String  CONTENT;
	private int  	BAMBOO;
	private int  	SALE_BAMBOO;
	private String 	SUMMARY;
	private String 	STATUS;
	private int  	VIEW_COUNT;
	private String 	REG_DATE;
}
