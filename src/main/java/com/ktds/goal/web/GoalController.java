package com.ktds.goal.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.github.seccoding.web.mimetype.ExtFilter;
import io.github.seccoding.web.mimetype.ExtensionFilter;
import io.github.seccoding.web.mimetype.ExtensionFilterFactory;

@Controller
public class GoalController {
	
	@GetMapping("mygoal/write.go")
	private String viewMyGoalWirtePage() {
		return "mygoal/write";
	}

	@RequestMapping("mygoal/imageupload.go")
	@ResponseBody
	public Map<String, Object> fileUpload(MultipartHttpServletRequest multiFile
			, HttpServletRequest req) throws Exception {
		Map<String, Object> result = new HashMap<>();
		MultipartFile uploadFile = multiFile.getFile("upload");
		String uploadImagePath = "C:/Users/kei96/Documents/WeGo/src/main/webapp/WEB-INF/static/img/upload";

		if ( !uploadFile.isEmpty() ) {
			String extension = uploadFile.getContentType().split("/")[1];
			String fileName = UUID.randomUUID().toString() + "." + extension;
			String fileUrl = "/WeGo/img/upload/" + fileName;
			
			System.out.println(fileUrl);
			
			File uploadDir = new File(uploadImagePath);
			if ( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			File destFile = new File(uploadImagePath, fileName);
			
			try {
				uploadFile.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				if ( destFile.exists() ) {
					ExtensionFilter filter = ExtensionFilterFactory.getFilter(ExtFilter.APACHE_TIKA);
					boolean isImageFile = filter.doFilter(
							destFile.getAbsolutePath()
							,"image/jpg"
							,"image/bmp"
							,"image/jpeg"
							,"image/gif"
							,"image/png" );
					
					if ( !isImageFile ) {
						destFile.delete();
						throw new RuntimeException("이미지 파일이 아닙니다.");
					}
				}
			}
			result.put("uploaded", 1);
			result.put("fileName", fileName);
			result.put("url", fileUrl);

			return result;
		}
		throw new RuntimeException("파일이 존재하지 않습니다.");
	}
}
