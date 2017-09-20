package com.demo.taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class AttributeTag extends TagSupport {

	private String user = null;
	
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().print("Hello, " + user + "!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (SKIP_BODY);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
