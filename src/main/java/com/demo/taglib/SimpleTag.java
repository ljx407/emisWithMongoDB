package com.demo.taglib;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTag extends SimpleTagSupport {
	public void doTag() {
		try {
			getJspContext().getOut().print("Hello, World!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
