package com.ktds.goal.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.common.util.DownloadUtil;

import io.github.seccoding.web.mimetype.ExtFilter;
import io.github.seccoding.web.mimetype.ExtensionFilter;
import io.github.seccoding.web.mimetype.ExtensionFilterFactory;

@Controller
public class GoalController {
	
	@Value("${upload.image.path}")
	private String uploadPath;
	
	@GetMapping("mygoal/write")
	private String viewMyGoalWirtePage() {
		return "mygoal/write";
	}

	@RequestMapping("mygoal/imageupload")
	@ResponseBody
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multiFile
			, HttpServletRequest req) throws Exception {
		Map<String, Object> result = new HashMap<>();
		MultipartFile uploadFile = multiFile.getFile("upload");

		if ( !uploadFile.isEmpty() ) {
			String fileName = UUID.randomUUID().toString();

			
			File uploadDir = new File(this.uploadPath);
			if ( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			File destFile = new File(this.uploadPath, fileName);
			
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
			result.put("uploaded", true);
			result.put("url", "/WeGo/mygoal/imagedownload/" + fileName);

			return result;
		}
		throw new RuntimeException("파일이 존재하지 않습니다.");
	}
	
	@RequestMapping("/mygoal/imagedownload/{fileName}")
	public void imageDownload(@PathVariable String fileName,HttpServletRequest req, HttpServletResponse res) {
		try {
			new DownloadUtil(this.uploadPath + File.separator + fileName).download(req, res, fileName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
