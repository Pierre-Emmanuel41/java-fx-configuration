package fr.pederobien.javafx.configuration.events;

import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import javafx.scene.text.Font;

public class ConfigurationFontChangePostEvent extends GuiConfigurationEvent {
	private Font currentFont, oldFont;

	/**
	 * Creates an event thrown when the font of the given configuration has changed.
	 * 
	 * @param configuration The configuration whose the font has changed.
	 * @param currentFont   The current font of the configuration.
	 * @param oldFont       The old font of the configuration.
	 */
	public ConfigurationFontChangePostEvent(IGuiConfiguration configuration, Font currentFont, Font oldFont) {
		super(configuration);
		this.currentFont = currentFont;
		this.oldFont = oldFont;
	}

	/**
	 * @return The current font of the configuration.
	 */
	public Font getCurrentFont() {
		return currentFont;
	}

	/**
	 * @return The old font of the configuration.
	 */
	public Font getOldFont() {
		return oldFont;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("configuration=" + getConfiguration().getName());
		joiner.add(String.format("currentFont=[name=%s, size=%s]", getCurrentFont().getName(), getCurrentFont().getSize()));
		joiner.add(String.format("oldFont=[name=%s, size=%s]", getOldFont().getName(), getOldFont().getSize()));
		return String.format("%s_%s", getName(), joiner);
	}
}
