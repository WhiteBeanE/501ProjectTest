package com.oracle.ProjectTest.configration;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FileUploadConfig {
	
	 @Bean
	    public CommonsMultipartResolver multipartResolver() throws IOException {
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	        resolver.setMaxUploadSize(52428800); // 50MB
	        resolver.setMaxInMemorySize(1048576); // 1MB
//	        resolver.setUploadTempDir(new FileSystemResource(System.getProperty("java.io.tmpdir")));
//	        resolver.setDefaultEncoding("UTF-8");
//	        resolver.setResolveLazily(true);
	        return resolver;
	    }

	    @Bean
	    public ServletFileUpload fileUpload() {
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setFileSizeMax(52428800); // 50MB
	        upload.setSizeMax(52428800); // 50MB
	        return upload;
	    }
	    
	    // 에디터5
    	@Bean
    	MappingJackson2JsonView jsonView() {
    		return new MappingJackson2JsonView();
    	}
	    
	    
	    
}
