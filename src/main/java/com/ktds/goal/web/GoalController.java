package com.ktds.goal.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.session.Session;
import com.ktds.common.util.DownloadUtil;
import com.ktds.goal.service.GoalService;
import com.ktds.goal.vo.GoalVO;
import com.ktds.goal.vo.GoalVOForForm;
import com.ktds.member.vo.MemberVO;
import com.nhncorp.lucy.security.xss.XssFilter;

import io.github.seccoding.web.mimetype.ExtFilter;
import io.github.seccoding.web.mimetype.ExtensionFilter;
import io.github.seccoding.web.mimetype.ExtensionFilterFactory;

@Controller
public class GoalController {
	
	@Autowired
	private GoalService goalService;
	
	@Value("${upload.image.path}")
	private String uploadPath;
	
	@GetMapping("mygoal/write")
	private ModelAndView viewMyGoalWritePage() {
		ModelAndView view = new ModelAndView("mygoal/write");
		view.addObject("parentGoal", this.goalService.readOneGoal("MS-2018101010-000007"));
		return view;
	}
	
	@PostMapping("mygoal/write")
	public ModelAndView doMyGoalWriteAction(@ModelAttribute GoalVOForForm goalVOForForm
			, @SessionAttribute(name=Session.USER) MemberVO memberVO
			, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("mygoal/write");
		
		String sessionToken = (String)request.getSession().getAttribute(Session.CSRF);
		if ( !goalVOForForm.getToken().equals(sessionToken) ) {
			throw new RuntimeException("잘못된 접근 입니다.");
		}
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		goalVOForForm.setTitle( filter.doFilter(goalVOForForm.getTitle()) );
		goalVOForForm.setDetail( filter.doFilter(goalVOForForm.getDetail()) );
		
		goalVOForForm.setEmail(memberVO.getEmail());
		
		this.goalService.createGoal(goalVOForForm);
		return view;
	}

	@RequestMapping("mygoal/imageupload")
	@ResponseBody
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multiFile) throws Exception {
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
	
	@RequestMapping("/mygoal/detail")
	public ModelAndView viewMyGoalDetailPage(@SessionAttribute(name=Session.USER) MemberVO memberVO) {
		ModelAndView view = new ModelAndView("mygoal/detail");
		GoalVO goalVO = this.goalService.readLatestModifyGoalByEmail(memberVO.getEmail());
		view.addObject("goalVO", goalVO);
		view.addObject("parentGoal", this.goalService.readParentGoal(goalVO.getId()));
		view.addObject("childrenGoalList", this.goalService.readChildrenGoalList(goalVO.getId()));
		return view;
	}
	
	@RequestMapping("/mygoal/detail/{goalId}")
	public ModelAndView viewMyGoalDetailPage(@PathVariable String goalId) {
		ModelAndView view = new ModelAndView("mygoal/detail");
		GoalVO goalVO = this.goalService.readOneGoal(goalId);
		view.addObject("goalVO", goalVO);
		view.addObject("parentGoal", this.goalService.readParentGoal(goalVO.getId()));
		view.addObject("childrenGoalList", this.goalService.readChildrenGoalList(goalVO.getId()));
		return view;
	}
	
}
