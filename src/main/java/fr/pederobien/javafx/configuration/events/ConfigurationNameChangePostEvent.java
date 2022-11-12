package fr.pederobien.javafx.configuration.events;

import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;

public class ConfigurationNameChangePostEvent extends GuiConfigurationEvent {
	private String currentName, oldName;

	/**
	 * Creates an event thrown when the name of a GUI configuration has changed.
	 * 
	 * @param configuration The configuration whose the name has changed.
	 * @param currentName   The current name of the configuration.
	 * @param oldName       The old name of the configuration.
	 */
	public ConfigurationNameChangePostEvent(IGuiConfiguration configuration, String currentName, String oldName) {
		super(configuration);
		this.currentName = currentName;
		this.oldName = oldName;
	}

	/**
	 * @return The current name of the configuration.
	 */
	public String getCurrentName() {
		return currentName;
	}

	/**
	 * @return The old name of the configuration.
	 */
	public String getOldName() {
		return oldName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("currentName=" + getConfiguration().getName());
		joiner.add("oldName=" + getOldName());
		return String.format("%s_%s", getOldName(), joiner);
	}
}
