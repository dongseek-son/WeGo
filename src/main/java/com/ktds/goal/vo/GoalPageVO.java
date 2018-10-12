package com.ktds.goal.vo;

import io.github.seccoding.web.pager.annotations.EndRow;
import io.github.seccoding.web.pager.annotations.StartRow;

public class GoalPageVO {
	
	private int page;
	
	private int size;

	@StartRow
	private int startRow;

	@EndRow
	private int endRow;
	
	public GoalPageVO() {
		
	}

	public GoalPageVO(int page, int size) {
		this.page = page;
		this.size = size;
		this.startRow = (page * size) + 1;
		this.endRow = (page * size) + size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

}
