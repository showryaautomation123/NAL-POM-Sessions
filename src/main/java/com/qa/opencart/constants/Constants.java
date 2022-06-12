package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public static final int DEFAULT_ELEMENT_TIMEOUT=10;
	public static final int DEFAULT_TIMEOUT=5;
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static final List<String> EXPECTED_ACCOUNTSPAGE_SECTION_LIST= Arrays.asList("My Account", 
			"My Orders", 
			"My Affiliate Account", 
			"Newsletter");
	public static final String LOGOUT_SUCCESS_MESSAGE = "Account Logout";
	public static final String PRODUCTINFO_PAGE_DESCRIPTION="Quickly set up a video conference with the built-in iSight camera.";

	public static final String PRODUCTINFO_PAGE_HEADER_DESCRIPTION="Designed for life on the road";
	public static final String PRODUCTINFO_PAGE_HEADER2_DESCRIPTION="Latest Intel mobile architecture";
	public static final CharSequence ACCOUNT_REGISTER_SUCCESS_MESSG =  "Your Account Has Been Created";;
}
