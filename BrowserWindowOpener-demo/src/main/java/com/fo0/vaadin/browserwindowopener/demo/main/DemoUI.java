package com.fo0.vaadin.browserwindowopener.demo.main;

import javax.servlet.annotation.WebServlet;

import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.fo0.vaadin.browserwindowopener.main.PopupConfiguration;
import com.fo0.vaadin.browserwindowopener.main.WindowOpenerButton;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

@Theme("demo")
@Title("Browserwindowopener Add-on Demo")
@SuppressWarnings("serial")
@Push(PushMode.AUTOMATIC)
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		WindowOpenerButton btn = new WindowOpenerButton(PopupConfiguration.builder().build(), new MyLayout());
		btn.setIcon(VaadinIcons.BROWSER);
		btn.setCaption("Custom Browser Popup");

		WindowOpenerButton btn2 = new WindowOpenerButton(PopupConfiguration.builder().build(),
				new MVerticalLayout(new MLabel("This is my content")));
		btn2.setIcon(VaadinIcons.BROWSER);
		btn2.setCaption("Custom Browser Popup 2");

		setContent(new MVerticalLayout(
				new MLabel("Please care, that a current bug is that your first open needs >two< clicks"), btn, btn2));
	}
}
