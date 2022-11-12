package fr.pederobien.javafx.configuration.impl;

import fr.pederobien.javafx.configuration.impl.properties.PropertyHelper;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;

public class GuiHelper {
	private static IGuiConfiguration configuration;
	private static final PropertyHelper PROPERTY_HELPER;

	static {
		configuration = new GuiConfiguration("Default");
		PROPERTY_HELPER = new PropertyHelper(configuration);
	}

	/**
	 * @return The configuration of this helper.
	 */
	public static IGuiConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * Set the GUI configuration associated to this GUI helper.
	 * 
	 * @param configuration The new configuration of this helper.
	 */
	public static void setConfiguration(IGuiConfiguration configuration) {
		GuiHelper.configuration = configuration;
		PROPERTY_HELPER.setConfiguration(configuration);
	}

	/**
	 * @return The property helper that creates properties synchronized with a GUI configuration.
	 */
	public static PropertyHelper getPropertyHelper() {
		return PROPERTY_HELPER;
	}
}
