package com.oracle.ProjectTest.service;

import java.util.List;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;

public interface WriteService {

	List<Category> categoryList();

	int talrentWrite(Talent talent);

}
