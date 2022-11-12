package fr.pederobien.javafx.configuration.events;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;

public class GuiConfigurationEvent extends ProjectJavaFxConfigurationEvent {
	private IGuiConfiguration configuration;

	/**
	 * Creates a GUI configuration event.
	 * 
	 * @param configuration The configuration source involved in this event.
	 */
	public GuiConfigurationEvent(IGuiConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return The configuration involved in this event.
	 */
	public IGuiConfiguration getConfiguration() {
		return configuration;
	}
}
