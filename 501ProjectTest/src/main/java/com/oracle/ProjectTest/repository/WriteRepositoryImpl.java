package com.oracle.ProjectTest.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;

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
		System.out.println("WriteRepositoryImpl.categoryList categoryList.size() -> " + categoryList.size());
		return categoryList;
	}
	@Override
	public List<Category> categoryList(int category1Value) {
		System.out.println("WriteRepositoryImpl.categoryList Start");
		List<Category> categoryList = null;
		try {
			categoryList = session.selectList("UpperCategoryList", category1Value);
		} catch (Exception e) {
			System.out.println("WriteRepositoryImpl.categoryList e.getMessage() -> " + e.getMessage());
		}
		System.out.println("WriteRepositoryImpl.categoryList categoryList.size() -> " + categoryList.size());
		return categoryList;
	}
	@Override
	public int talrentWrite(Talent talent) {
		System.out.println("WriteRepositoryImpl.categoryList Start");
		int result = 0;
		try {
			result = session.insert("insertTalent", talent);
		} catch (Exception e) {
			System.out.println("WriteRepositoryImpl.categoryList e.getMessage() -> " + e.getMessage());
		}
		System.out.println("WriteRepositoryImpl.categoryList result -> " + result);
		return result;
	}

}
