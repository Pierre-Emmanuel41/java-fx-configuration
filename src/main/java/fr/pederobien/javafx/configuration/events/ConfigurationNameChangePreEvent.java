package fr.pederobien.javafx.configuration.events;

import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.utils.ICancellable;

public class ConfigurationNameChangePreEvent extends GuiConfigurationEvent implements ICancellable {
	private boolean isCancelled;
	private String currentName, newName;

	/**
	 * Creates an event thrown when the name of a GUI configuration is about to change.
	 * 
	 * @param configuration The configuration whose the name is about to change.
	 * @param currentName   The current name of the configuration.
	 * @param newName       The new name of the configuration.
	 */
	public ConfigurationNameChangePreEvent(IGuiConfiguration configuration, String currentName, String newName) {
		super(configuration);
		this.currentName = currentName;
		this.newName = newName;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	/**
	 * @return The current name of the configuration.
	 */
	public String getCurrentName() {
		return currentName;
	}

	/**
	 * @return The new name of the configuration.
	 */
	public String getNewName() {
		return newName;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("currentName=" + getConfiguration().getName());
		joiner.add("newName=" + getNewName());
		return String.format("%s_%s", getNewName(), joiner);
	}
}
