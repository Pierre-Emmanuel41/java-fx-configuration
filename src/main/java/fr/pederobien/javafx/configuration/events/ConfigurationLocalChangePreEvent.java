package fr.pederobien.javafx.configuration.events;

import java.util.Locale;
import java.util.StringJoiner;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.utils.ICancellable;

public class ConfigurationLocalChangePreEvent extends GuiConfigurationEvent implements ICancellable {
	private boolean isCancelled;
	private Locale currentLocale, newLocale;

	/**
	 * Creates an event thrown when the local of a GUI configuration is about to change.
	 * 
	 * @param configuration The configuration whose the locale is about to change.
	 * @param currentLocale The current locale of the configuration.
	 * @param newLocale     The new locale of the configuration.
	 */
	public ConfigurationLocalChangePreEvent(IGuiConfiguration configuration, Locale currentLocale, Locale newLocale) {
		super(configuration);
		this.currentLocale = currentLocale;
		this.newLocale = newLocale;
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
	 * @return The current locale of the configuration.
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	/**
	 * @return The new locale of the configuration.
	 */
	public Locale getNewLocale() {
		return newLocale;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{", "}");
		joiner.add("configuration=" + getConfiguration().getName());
		joiner.add("currentLocale=" + getCurrentLocale().getLanguage());
		joiner.add("newLocale=" + getNewLocale().getLanguage());
		return String.format("%s_%s", getName(), joiner);
	}
}
