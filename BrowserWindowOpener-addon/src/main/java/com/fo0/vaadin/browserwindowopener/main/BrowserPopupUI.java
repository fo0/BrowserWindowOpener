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
		Map<String, String[]> params = getURIParameters();

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

	private static Map<String, String[]> getURIParameters() {
		return VaadinService.getCurrentRequest().getParameterMap();

		// String params = VaadinService.getCurrentRequest().getPathInfo();
		// if (params != null) {
		// String[] msgs = params.split("/");
		// if (msgs.length > 0) {
		// return (List<String>) Arrays.asList(msgs);
		// } else {
		// return null;
		// }
		// }
		// return null;
	}

	private void log(String log) {
		System.out.println(BrowserPopupUI.class.getSimpleName() + " " + log);
	}
}
