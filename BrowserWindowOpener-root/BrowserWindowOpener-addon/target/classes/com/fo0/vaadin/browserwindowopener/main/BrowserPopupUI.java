package com.fo0.vaadin.browserwindowopener.main;

import java.util.Arrays;
import java.util.List;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;

public class BrowserPopupUI extends UI {

	private static final long serialVersionUID = -5821181446613235080L;

	@Override
	protected void init(VaadinRequest request) {
		// fetch id from path
		String id = getURIParameters().get(1);
		log("found id param in path: " + id);

		// check if id is in session
		Layout layout = (Layout) VaadinSession.getCurrent().getAttribute(id);
		log("found layout in vaadin session: " + layout);

		// unregister from session
		VaadinSession.getCurrent().setAttribute(id, null);
		log("removed layout from vaadin session " + layout);

		// set as content
		setContent(layout);
	}

	private static List<String> getURIParameters() {
		String params = VaadinService.getCurrentRequest().getPathInfo();
		if (params != null) {
			String[] msgs = params.split("/");
			if (msgs.length > 0) {
				return (List<String>) Arrays.asList(msgs);
			} else {
				return null;
			}
		}
		return null;
	}

	private void log(String log) {
		System.out.println(BrowserPopupUI.class.getSimpleName() + " " + log);
	}
}
