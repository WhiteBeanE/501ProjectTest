package com.oracle.ProjectTest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.ProjectTest.domain.Category;
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
		return categoryList;
	}

}
