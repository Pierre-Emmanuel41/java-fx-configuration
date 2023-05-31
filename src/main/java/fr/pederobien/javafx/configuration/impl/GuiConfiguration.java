package fr.pederobien.javafx.configuration.impl;

import java.util.Locale;

import fr.pederobien.dictionary.impl.DictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.events.FontChangePostEvent;
import fr.pederobien.javafx.configuration.events.FontChangePreEvent;
import fr.pederobien.javafx.configuration.events.LocalChangePreEvent;
import fr.pederobien.javafx.configuration.events.LocaleChangePostEvent;
import fr.pederobien.javafx.configuration.events.NameChangePostEvent;
import fr.pederobien.javafx.configuration.events.NameChangePreEvent;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.utils.event.EventManager;
import javafx.scene.text.Font;

public class GuiConfiguration implements IGuiConfiguration {
	private String name;
	private IDictionaryContext dictionaryContext;

	private Font font;
	private Locale locale;

	/**
	 * Creates a graphical user interface configuration.
	 * 
	 * @param name The configuration name.
	 */
	public GuiConfiguration(String name) {
		this.name = name;
		dictionaryContext = new DictionaryContext();
		font = Font.getDefault();
		locale = Locale.getDefault();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (getName().equals(name))
			return;

		String oldName = getName();
		Runnable update = () -> this.name = name;
		EventManager.callEvent(new NameChangePreEvent(this, getName(), name), update, new NameChangePostEvent(this, getName(), oldName));
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		if (getFont().equals(font))
			return;

		Font oldFont = getFont();
		Runnable update = () -> this.font = font;
		EventManager.callEvent(new FontChangePreEvent(this, getFont(), font), update, new FontChangePostEvent(this, oldFont, getFont()));
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setLocale(Locale locale) {
		if (getLocale().getLanguage().equals(locale.getLanguage()))
			return;

		Locale oldLocale = getLocale();
		Runnable update = () -> this.locale = locale;
		EventManager.callEvent(new LocalChangePreEvent(this, getLocale(), locale), update, new LocaleChangePostEvent(this, getLocale(), oldLocale));
	}

	@Override
	public IDictionaryContext getDictionaryContext() {
		return dictionaryContext;
	}
}
