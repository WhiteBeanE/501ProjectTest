package com.oracle.ProjectTest.service;

import java.util.List;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;

public interface WriteService {

	List<Category> categoryList();

	List<Category> categoryList(int category1Value);

	int talrentWrite(Talent talent);

}
