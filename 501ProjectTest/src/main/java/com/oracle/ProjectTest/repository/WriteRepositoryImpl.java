package com.oracle.ProjectTest.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.ProjectTest.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WriteRepositoryImpl implements WriteRepository {
		private final SqlSession session;
	@Override
	public List<Category> categoryList() {
		System.out.println("WriteRepositoryImpl.categoryList Start");
		List<Category> categoryList = null;
		try {
			categoryList = session.selectList("CategoryList");
		} catch (Exception e) {
			System.out.println("WriteRepositoryImpl.categoryList e.getMessage() -> " + e.getMessage());
		}
		return categoryList;
	}

}
