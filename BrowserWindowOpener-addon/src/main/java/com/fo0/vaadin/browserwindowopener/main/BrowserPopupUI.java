package com.fo0.vaadin.browserwindowopener.main;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;

@Push(PushMode.AUTOMATIC)
public class BrowserPopupUI extends UI {

	private static final long serialVersionUID = -5821181446613235080L;

	@Override
	protected void init(VaadinRequest request) {
		// fetch id from path
		Map<String, String[]> params = getParams();

		String id = StringUtils.join(params.get("id"), "-");

		log("found id param in path: " + id);
		// check if id is in session
		Layout layout = (Layout) VaadinSession.getCurrent().getAttribute(id);
		log("found layout in vaadin session: " + layout);

		// unregister from session
		log("removed layout from vaadin session " + layout);

		// set as content
		setContent(layout);
		VaadinSession.getCurrent().setAttribute(id, null);
	}

	public String getParams(String key) {
		return VaadinService.getCurrentRequest().getParameter(key);
	}

	public Map<String, String[]> getParams() {
		return VaadinService.getCurrentRequest().getParameterMap();
	}

	private void log(String log) {
		System.out.println(BrowserPopupUI.class.getSimpleName() + " " + log);
	}
}
