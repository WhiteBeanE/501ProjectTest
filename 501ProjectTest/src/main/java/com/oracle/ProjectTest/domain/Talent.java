package com.oracle.ProjectTest.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Talent {
	@Id
	private Long 	talentNo;
	private String 	sellerId;
	private int 	upperCategoryNo;
	private int 	lowerCategoryNo;
	private String 	mainImg;
	private String 	title;
	private String  content;
	private int  	bamboo;
	private int  	saleBamboo;
	private String 	summary;
	private String 	status;
	private int  	viewCount;
	private String 	regDate;
}
