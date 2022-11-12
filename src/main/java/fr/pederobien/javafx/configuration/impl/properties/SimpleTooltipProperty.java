package fr.pederobien.javafx.configuration.impl.properties;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tooltip;

public class SimpleTooltipProperty extends SimpleObjectProperty<Tooltip> {
	private SimpleLanguageProperty textProperty;

	/**
	 * Creates a Tooltip property whose the font and the message is synchronized with a GUI configuration.
	 * 
	 * @param listener The listener that updates this font property.
	 * @param code     The code associated to the message to display.
	 * @param args     The message arguments if the message needs arguments.
	 */
	public SimpleTooltipProperty(GuiConfigurationEventListener listener, ICode code, Object... args) {
		super(new Tooltip());

		textProperty = new SimpleLanguageProperty(listener, code, args);
		getValue().fontProperty().bind(new SimpleFontProperty(listener));
		getValue().textProperty().bind(textProperty);
	}

	/**
	 * Set the code associated to this tooltip. This will automatically change the displayed text.
	 * 
	 * @param code The new code associated to the message to display.
	 * @param args The new arguments array associated to the code.
	 */
	public void setMessageCode(ICode code, Object... args) {
		textProperty.setCode(code, args);
	}
}