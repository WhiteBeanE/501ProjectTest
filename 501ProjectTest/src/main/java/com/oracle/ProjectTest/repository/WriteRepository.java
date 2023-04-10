package com.oracle.ProjectTest.repository;

import java.util.List;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;

public interface WriteRepository {

	List<Category> categoryList();

	int talrentWrite(Talent talent);

}
