package fr.pederobien.javafx.configuration.events;

import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.utils.ICancellable;
import javafx.scene.text.Font;

public class ConfigurationFontChangePreEvent extends GuiConfigurationEvent implements ICancellable {
	private boolean isCancelled;
	private Font currentFont, newFont;

	/**
	 * Creates an event when the font of a configuration is about to change.
	 * 
	 * @param configuration The configuration whose the font is about to change.
	 * @param currentFont   The current font of the configuration.
	 * @param newFont       The new font of the configuration.
	 */
	public ConfigurationFontChangePreEvent(IGuiConfiguration configuration, Font currentFont, Font newFont) {
		super(configuration);
		this.currentFont = currentFont;
		this.newFont = newFont;
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
	 * @return The current font of the configuration.
	 */
	public Font getCurrentFont() {
		return currentFont;
	}

	/**
	 * @return The new font of the configuration.
	 */
	public Font getNewFont() {
		return newFont;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("configuration=" + getConfiguration().getName());
		joiner.add(String.format("currentFont=[name=%s, size=%s]", getCurrentFont().getName(), getCurrentFont().getSize()));
		joiner.add(String.format("newFont=[name=%s, size=%s]", getNewFont().getName(), getNewFont().getSize()));
		return String.format("%s_%s", getName(), joiner);
	}
}
