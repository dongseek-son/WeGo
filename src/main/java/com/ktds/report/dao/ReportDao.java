package com.ktds.report.dao;

import java.util.List;

import com.ktds.report.vo.ReportVO;

public interface ReportDao {

	public int insertReport(ReportVO reportVO);
	
	public List<ReportVO> selectReportList();
	
	public List<ReportVO> selectReportListByEmail(String email);
	
	public List<ReportVO> selectReportListByReportedEmail(String email);

}
