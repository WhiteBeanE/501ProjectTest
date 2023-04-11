package com.oracle.ProjectTest.conntroller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ProjectTest.domain.Category;
import com.oracle.ProjectTest.domain.Talent;
import com.oracle.ProjectTest.service.WriteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	
	private final WriteService writeService; 
	
	// 등록 페이지
	@GetMapping("/writeFrom5")
	public String writeFrom5( Model model) {
		System.out.println("MainController.writeFrom5() Start");
		// 카테고리 가져오기
		List<Category> categoryList = writeService.categoryList();
		System.out.println("MainController.writeFrom5() categoryList.size() -> " + categoryList.size());
		// model.addAttribute("member", member);
		model.addAttribute("categoryList", categoryList);
		return "writeForm5";
	}
	
	@PostMapping(value = "/image/upload")
	public ModelAndView image(MultipartHttpServletRequest request) throws Exception {
		
		// 이미지 업로드시 10mb이하 크기만 업로드 가능
		long maxFileSize = 10 * 1024 * 1024; // 10mb
		if (request.getFile("upload").getSize() > maxFileSize) {
		    return null;
		}
		
		// ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
		// modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
		// jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단 
		// WebConfig 파일에 MappingJackson2JsonView 객체를 리턴하는 jsonView 매서드를 만들어서 bean으로 등록해야 함 
		ModelAndView mav = new ModelAndView("jsonView");

		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함
		MultipartFile uploadFile = request.getFile("upload");
		
		// 파일의 오리지널 네임
		String originalFileName = uploadFile.getOriginalFilename();
		System.out.println("MainController.image() 파일의 오리지널 네임 -> " + originalFileName);
		
        // 파일의 확장자 추출
		String ext = originalFileName.substring(originalFileName.indexOf("."));
		System.out.println("MainController.image() 파일의 확장자 -> " + ext);
		
        // 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
		String newFileName = UUID.randomUUID() + ext;
		System.out.println("MainController.image() 서버에 저장될 파일 이름 -> " + newFileName);

		// 이미지를 현재 경로와 연관된 파일에 저장하기 위해 현재 경로를 알아냄
		String realPath = request.getServletContext().getRealPath("/");
		System.out.println("MainController.image() 현재 파일 경로 -> " + realPath);

		// 현재경로/upload/파일명이 저장 경로
		String savePath = realPath + "upload/" + newFileName;
		System.out.println("MainController.image() 파일 저장 경로 + 파일 이름 -> " + savePath);
		
		// 해당 파일 경로에 폴더가 없을시 폴더 생성
		String uploadPathf = realPath + "upload/";
		File fileDirectory = new File(uploadPathf);
		if(!fileDirectory.exists()) {
			// 신규 폴더(Directory)생성
			fileDirectory.mkdirs();
		}
		
		// 브라우저에서 이미지 불러올 때 절대 경로로 불러오면 보안의 위험 있어 상대경로를 쓰거나 이미지 불러오는 jsp 또는 클래스 파일을 만들어 가져오는 식으로 우회해야 함
		// 때문에 savePath와 별개로 상대 경로인 uploadPath 만들어줌
		String uploadPath = "./upload/" + newFileName; 
		System.out.println("MainController.image() 보안을 위한 상대 경로 출력 -> " + uploadPath);

		// 저장 경로로 파일 객체 생성
		File file = new File(savePath);

		// 파일 업로드
		uploadFile.transferTo(file);

		// uploaded, url 값을 modelandview를 통해 보냄
		mav.addObject("uploaded", true); // 업로드 완료
		mav.addObject("url", uploadPath); // 업로드 파일의 경로

		return mav;
	}
	
	@PostMapping("/write")
	public String talrentWrite(Talent talent) {
		System.out.println("MainController.talrentWrite() Start");
		int result = writeService.talrentWrite(talent);
		String imgString = talent.getMainImg();
		System.out.println("talent.getTALENT_NO() -> " + talent.getTalentNo());
		System.out.println("talent.getSELLER_ID() -> " + talent.getSellerId());
		System.out.println("talent.getTITLE() -> " + talent.getTitle());
		System.out.println("talent.getMAIN_IMG() -> " + talent.getMainImg());
		System.out.println("talent.getCONTENT() -> " + talent.getContent());
		return "redirect:/";
	}
	
	 @PostMapping("/mainImage/upload")
	 @ResponseBody
	 public ModelAndView uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request) throws Exception {
		 	System.out.println("MainController.uploadImage() Start");
	        // 이미지 업로드시 10mb이하 크기만 업로드 가능
	        long maxFileSize = 10 * 1024 * 1024; // 10mb
	        if (file.getSize() > maxFileSize) {
	            return null;
	        }
	        
	        ModelAndView mav = new ModelAndView("jsonView");

	        // 파일의 오리지널 네임
	        String originalFileName = file.getOriginalFilename();
	        
	        // 파일의 확장자 추출
	        String ext = originalFileName.substring(originalFileName.indexOf("."));

	        // 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
	        String newFileName = UUID.randomUUID() + ext;

	        // 이미지를 현재 경로와 연관된 파일에 저장하기 위해 현재 경로를 알아냄
	        String realPath = request.getServletContext().getRealPath("/mainImage/");

	        // 현재경로/upload/파일명이 저장 경로
	        String savePath = realPath + newFileName;
	        System.out.println("Controller.uploadImage() 파일 저장 경로 + 파일 이름 -> " + savePath);

	        // 브라우저에서 이미지 불러올 때 절대 경로로 불러오면 보안의 위험 있어 상대경로를 쓰거나 이미지 불러오는 jsp 또는 클래스 파일을 만들어 가져오는 식으로 우회해야 함
	        // 때문에 savePath와 별개로 상대 경로인 uploadPath 만들어줌
	        String uploadPath = "./mainImage/" + newFileName;
	        System.out.println("Controller.uploadImage() 보안을 위한 상대 경로 출력 -> " + uploadPath);

	        // 저장 경로로 파일 객체 생성
	        File saveFile = new File(savePath);

	        // 파일 업로드
	        file.transferTo(saveFile);

	        // uploaded, url 값을 modelandview를 통해 보냄
	        mav.addObject("uploaded", true); // 업로드 완료
	        mav.addObject("url", uploadPath); // 업로드 파일의 경로

	        return mav;
	    }
	 
	 
	 
	 
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/writeFrom4")
	public String writeFrom4( Model model) {
		System.out.println("MainController.writeFrom4() Start");
		// 카테고리 가져오기
		List<Category> categoryList = writeService.categoryList();
		System.out.println("MainController.writeFrom4() categoryList.size() -> " + categoryList.size());
		model.addAttribute("categoryList", categoryList);
		return "writeForm4";
	}
	
	// ck 에디터에서 파일 업로드
	@RequestMapping(value="/mine/imageUpload.do", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam MultipartFile upload) throws Exception {
		log.info("post CKEditor img upload");
		
		// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 인코딩
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			
			String fileName = upload.getOriginalFilename();  // 파일 이름 가져오기
			byte[] bytes = upload.getBytes();
			
			// 업로드 경로
			String rootPath = req.getSession().getServletContext().getRealPath("/");
			String filePath = rootPath + "uploads\\" + uid + upload.getOriginalFilename();
			
			// String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
			
			out = new FileOutputStream(new File(filePath));
			out.write(bytes);
			out.flush();  // out에 저장된 데이터를 전송하고 초기화
			
			String callback = req.getParameter("CKEditorFuncNum");
			printWriter = res.getWriter();
			String fileUrl = "/ckUpload/" + uid + "_" + fileName;  // 작성화면
			
			// 업로드시 메시지 출력
			printWriter.println("<script type='text/javascript'>"
					+ "window.parent.CKEDITOR.tools.callFunction("
					+ callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
					+"</script>");
			
			printWriter.flush();
			
		} catch (IOException e) { e.printStackTrace();
		} finally {
			try {
				if(out != null) { out.close(); }
				if(printWriter != null) { printWriter.close(); }
			} catch(IOException e) { e.printStackTrace(); }
		}
		
		return; 
	}	
	
	
	
	
	
	
	// xxxxxxx
	//  cKeditor 서버로 전송된 이미지 뿌려주기
	@RequestMapping(value="/mine/ckImgSubmit.do")
	public void ckSubmit(@RequestParam(value="uid") String uid
			, @RequestParam(value="fileName") String fileName
			, HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		
		//서버에 저장된 이미지 경로
		String path = request.getSession().getServletContext().getRealPath("/") + "uploads\\";
		
		String sDirPath = path + uid + "_" + fileName;
		
		File imgFile = new File(sDirPath);
		
		//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
		if(imgFile.isFile()){
			byte[] buf = new byte[1024];
			int readByte = 0;
			int length = 0;
			byte[] imgBuf = null;
			
			FileInputStream fileInputStream = null;
			ByteArrayOutputStream outputStream = null;
			ServletOutputStream out = null;
			
			try{
				fileInputStream = new FileInputStream(imgFile);
				outputStream = new ByteArrayOutputStream();
				out = response.getOutputStream();
				
				while((readByte = fileInputStream.read(buf)) != -1){
					outputStream.write(buf, 0, readByte);
				}
				
				imgBuf = outputStream.toByteArray();
				length = imgBuf.length;
				out.write(imgBuf, 0, length);
				out.flush();
				
			}catch(IOException e){
				log.info(e.getMessage());
			}finally {
				outputStream.close();
				fileInputStream.close();
				out.close();
			}
		}
	}
//	@PostMapping("/ckUpload/content")
//	@ResponseBody
//	public void fileUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request) {
//		System.out.println("MainController.fileUpload() Start");
//		UUID uid = UUID.randomUUID();
//		String rootPath = request.getSession().getServletContext().getRealPath("/");
//	    String filePath = rootPath + "uploads\\" + uid + file.getOriginalFilename();
//	    int maxSize = 20 * 1024 * 1024;
//	    
//	    System.out.println("MainController.fileUpload() rootPath -> " + rootPath);
//	    System.out.println("MainController.fileUpload() filePath -> " + filePath);
//	    try {
//	    	 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
//
//	         // 최대 업로드 용량 제한 설정
//	         multipartResolver.setMaxUploadSize(maxSize);
//
//	         if (multipartResolver.isMultipart(request)) {
//	             File dest = new File(filePath);
//	             file.transferTo(dest);
//	         }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
	
}
