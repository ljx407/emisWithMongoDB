package com.demo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BodyTag extends BodyTagSupport {

	private String user = null;
	
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().print("Hello, " + user + "!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (EVAL_BODY_BUFFERED);
	}

	public int doAfterBody() throws JspException {
		try {
			getBodyContent().writeOut(getPreviousOut());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doAfterBody();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
