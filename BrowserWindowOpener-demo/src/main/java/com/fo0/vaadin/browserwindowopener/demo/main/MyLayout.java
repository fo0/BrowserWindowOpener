package com.fo0.vaadin.browserwindowopener.demo.main;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class MyLayout extends VerticalLayout {

	private static final long serialVersionUID = 2130679115099617262L;

	public MyLayout() {
		addComponent(new Button("This is my custom Button"));
	}

}
