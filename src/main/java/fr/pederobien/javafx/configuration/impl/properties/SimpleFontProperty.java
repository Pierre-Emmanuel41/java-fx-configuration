package fr.pederobien.javafx.configuration.impl.properties;

import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener.Action;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Font;

public class SimpleFontProperty extends SimpleObjectProperty<Font> {

	/**
	 * Creates a font property synchronized with a GUI configuration.
	 * 
	 * @param listener The listener that updates this font property.
	 */
	public SimpleFontProperty(GuiConfigurationEventListener listener) {
		super(listener.getConfiguration().getFont());
		listener.registerAction(Action.FONT_CHANGED, e -> setValue((Font) e.getNewValue()));
	}
}
