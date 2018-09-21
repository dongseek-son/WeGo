package com.ktds.common.authority;

public interface Authority {

	int ADMIN = 0;
	int DEFAULT = 1;
	int BLOCK = 2;
	int WITHDRAWAL = 3;
	
	String[] AUTHORITIES = {"ROLE_ADMIN", "ROLE_DEFAULT", "ROLE_BLOCK", "ROLE_WITHDRAWAL"};
	
}
