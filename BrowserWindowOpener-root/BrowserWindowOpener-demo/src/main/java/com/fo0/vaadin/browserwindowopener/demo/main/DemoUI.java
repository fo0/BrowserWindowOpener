package com.fo0.vaadin.browserwindowopener.demo.main;

import javax.servlet.annotation.WebServlet;

import com.fo0.vaadin.browserwindowopener.main.PopupConfiguration;
import com.fo0.vaadin.browserwindowopener.main.WindowOpenerButton;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("demo")
@Title("BrowserWindowOpener Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		WindowOpenerButton btn = new WindowOpenerButton(PopupConfiguration.builder().build(), new MyLayout());
		setContent(btn);

	}
}