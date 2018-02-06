package com.fo0.vaadin.browserwindowopener.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.Builder;
import lombok.Data;

/**
 * Documentation on:
 * {@link https://vaadin.com/docs/framework/advanced/advanced-windows.html} <br>
 * <br>
 * status=0|1 <br>
 * Whether the status bar at the bottom of the window should be enabled.<br>
 * <br>
 * [parameter]##<br>
 * scrollbars<br>
 * Enables scrollbars in the window if the document area is bigger than the view
 * area of the window.<br>
 * <br>
 * resizable<br>
 * Allows the user to resize the browser window (no effect for tabs).<br>
 * <br>
 * menubar<br>
 * Enables the browser menu bar.<br>
 * <br>
 * location<br>
 * Enables the location bar.<br>
 * <br>
 * toolbar<br>
 * Enables the browser toolbar.<br>
 * <br>
 * height=value<br>
 * Specifies the height of the window in pixels.<br>
 * <br>
 * width=value<br>
 * Specifies the width of the window in pixels.<br>
 * 
 * @author max
 *
 */
@Data
@Builder
public class PopupConfiguration {

	@Builder.Default
	private String id = RandomStringUtils.randomAlphanumeric(20);

	@Builder.Default
	private int height = 400;

	@Builder.Default
	private int width = 600;

	private boolean status;
	private boolean resizable;
	private boolean menubar;
	private boolean location;
	private boolean toolbar;

	protected List<String> getConfig() {
		try {
			List<String> list = new ArrayList<String>();
			list.add("height=" + String.valueOf(height));
			list.add("width=" + String.valueOf(width));
			list.add("status=" + String.valueOf(status));

			if (resizable)
				list.add("resizable");

			if (menubar)

				list.add("menubar");
			if (location)
				list.add("location");

			if (toolbar)
				list.add("toolbar");

			return list;
		} catch (Exception e) {
			System.err.println("failed to generate popup configuration " + e);
			e.printStackTrace();
		}
		return null;
	}

}