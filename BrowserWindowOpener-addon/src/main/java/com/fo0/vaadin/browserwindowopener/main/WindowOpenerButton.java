package com.fo0.vaadin.browserwindowopener.main;

import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.button.MButton;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;

public class WindowOpenerButton extends MButton {

	private static final long serialVersionUID = 3235888364714347073L;

	private BrowserWindowOpener currentInstance = null;

	public WindowOpenerButton(PopupConfiguration config, LayoutListener layoutListener) {
		withListener(listener -> {
			try {
				Layout layout = layoutListener.event();
				// add layout to session and assign with id
				VaadinSession.getCurrent().setAttribute(config.getId(), layout);

				if (currentInstance != null) {
					// remove old extension
					removeExtension(currentInstance);
				}

				currentInstance = null;
				if (config.getClazz() != null) {
					currentInstance = new BrowserWindowOpener(config.getClazz());
				} else {
					currentInstance = new BrowserWindowOpener(BrowserPopupUI.class);
				}

				config.getParams().put("id", config.getId());

				for (Entry<String, String> e : config.getParams().entrySet()) {
					// check that key is always valid
					if (isValid(e.getKey()))
						currentInstance.setParameter(e.getKey(), e.getValue());

				}

				log("Generating features from config: " + StringUtils.join(config.getConfig(), ","));
				currentInstance.setFeatures(StringUtils.join(config.getConfig(), ","));

				currentInstance.extend(this);
			} catch (Exception e) {
				VaadinSession.getCurrent().setAttribute(config.getId(), null);
			}
		});
		click();
	}

	public WindowOpenerButton(PopupConfiguration config, Layout layout) {
		this(config, new LayoutListener() {

			@Override
			public Layout event() {
				return layout;
			}
		});
	}

	public WindowOpenerButton(LayoutListener layoutListener) {
		this(PopupConfiguration.builder().build(), layoutListener);
	}

	public WindowOpenerButton(Layout layoutListener) {
		this(PopupConfiguration.builder().build(), layoutListener);
	}

	private void log(String log) {
		System.out.println(WindowOpenerButton.class.getSimpleName() + " " + log);
	}

	private boolean isValid(String s) {
		return (s != null && !s.isEmpty()) ? true : false;
	}
}
