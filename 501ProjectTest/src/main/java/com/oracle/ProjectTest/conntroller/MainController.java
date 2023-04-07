package com.oracle.ProjectTest.conntroller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Member;
import com.oracle.ProjectTest.service.WriteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	
	private final WriteService writeService; 
	
	@GetMapping("/write")
	public String writeFrom(Member member, Model model) {
		System.out.println("MainController.writeFrom() Start");
		// 카테고리 가져오기
		// List<Category> categoryList = writeService.categoryList();
		model.addAttribute("member", member);
		return "writeForm";
	}
		
	@PostMapping("/ckUpload/content")
	@ResponseBody
	public void fileUpload(HttpServletRequest request, HttpServletResponse response,
							 MultipartHttpServletRequest multiFile) throws Exception{
		JSONObject json = new JSONObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		if(file != null) {
			//if(file.getSize() > 0 && StringUti)
		}
	}
	
}
