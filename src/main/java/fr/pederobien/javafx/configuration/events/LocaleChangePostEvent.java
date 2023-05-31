package fr.pederobien.javafx.configuration.events;

import java.util.Locale;
import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;

public class LocaleChangePostEvent extends GuiConfigurationEvent {
	private Locale currentLocale, oldLocale;

	/**
	 * Creates an event thrown when the local of a GUI configuration has changed.
	 * 
	 * @param configuration The configuration whose the locale has changed.
	 * @param currentLocale The current locale of the configuration.
	 * @param oldLocale     The old locale of the configuration.
	 */
	public LocaleChangePostEvent(IGuiConfiguration configuration, Locale currentLocale, Locale oldLocale) {
		super(configuration);
		this.currentLocale = currentLocale;
		this.oldLocale = oldLocale;
	}

	/**
	 * @return The current locale of the configuration.
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	/**
	 * @return The old locale of the configuration.
	 */
	public Locale getOldLocale() {
		return oldLocale;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("configuration=" + getConfiguration().getName());
		joiner.add("currentLocale=" + getCurrentLocale().getLanguage());
		joiner.add("oldLocale=" + getOldLocale().getLanguage());
		return String.format("%s_%s", getName(), joiner);
	}
}
