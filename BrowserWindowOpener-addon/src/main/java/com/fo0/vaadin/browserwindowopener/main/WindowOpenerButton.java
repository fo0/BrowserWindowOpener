package com.fo0.vaadin.browserwindowopener.main;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.button.MButton;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;

public class WindowOpenerButton extends MButton {

	private static final long serialVersionUID = 3235888364714347073L;

	public WindowOpenerButton(PopupConfiguration config, Layout layout) {
		VaadinSession.getCurrent().setAttribute(config.getId(), layout);
		BrowserWindowOpener opener = new BrowserWindowOpener(BrowserPopupUI.class, config.getId());
		log("Generating features from config: " + StringUtils.join(config.getConfig(), ","));
		opener.setFeatures(StringUtils.join(config.getConfig(), ","));
		opener.extend(this);
	}

	public WindowOpenerButton(Layout layout) {
		this(PopupConfiguration.builder().build(), layout);
	}

	private void log(String log) {
		System.out.println(WindowOpenerButton.class.getSimpleName() + " " + log);
	}
}
