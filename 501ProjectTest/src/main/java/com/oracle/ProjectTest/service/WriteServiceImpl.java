package com.oracle.ProjectTest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;
import com.oracle.ProjectTest.repository.WriteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WriteServiceImpl implements WriteService {
	private final WriteRepository writeRepository;
	@Override
	public List<Category> categoryList() {
		System.out.println("WriteServiceImpl.categoryList() Start");
		List<Category> categoryList = writeRepository.categoryList();
		System.out.println("WriteServiceImpl.categoryList() categoryList.size() -> " + categoryList.size());
		return categoryList;
	}
	@Override
	public List<Category> categoryList(int category1Value) {
		System.out.println("WriteServiceImpl.categoryList(category1Value) Start");
		List<Category> categoryList = writeRepository.categoryList(category1Value);
		System.out.println("WriteServiceImpl.categoryList(category1Value) categoryList.size() -> " + categoryList.size());
		return categoryList;
	}
	@Override
	public int talrentWrite(Talent talent) {
		System.out.println("WriteServiceImpl.talrentWrite() Start");
		int result = writeRepository.talrentWrite(talent);
		System.out.println("WriteServiceImpl.talrentWrite() result -> " + result);
		
		return result;
	}

}
