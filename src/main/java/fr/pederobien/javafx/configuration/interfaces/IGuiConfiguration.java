package fr.pederobien.javafx.configuration.interfaces;

import java.util.Locale;

import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import javafx.scene.text.Font;

public interface IGuiConfiguration {

	/**
	 * @return The configuration name.
	 */
	String getName();

	/**
	 * Set the name of this configuration.
	 * 
	 * @param name The new configuration name.
	 */
	void setName(String name);

	/**
	 * @return The Font to use when creating a new graphical component.
	 */
	Font getFont();

	/**
	 * Set the font of this configuration. If graphical components have been created, then their font will be updated.
	 * 
	 * @param The new font of this configuration.
	 */
	void setFont(Font font);

	/**
	 * @return The locale of this configuration.
	 */
	Locale getLocale();

	/**
	 * Set the local of this configuration. If graphical components that display language sensitive message have been created, their
	 * message will be updated.
	 * 
	 * @param locale The new locale of this configuration.
	 */
	void setLocale(Locale locale);

	/**
	 * @return A object that store dictionaries used to display language sensitive messages.
	 */
	IDictionaryContext getDictionaryContext();
}
