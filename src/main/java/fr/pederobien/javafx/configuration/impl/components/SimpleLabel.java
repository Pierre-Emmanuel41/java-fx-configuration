package fr.pederobien.javafx.configuration.impl.components;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiHelper;
import fr.pederobien.javafx.configuration.impl.properties.SimpleLanguageProperty;
import fr.pederobien.javafx.configuration.impl.properties.SimpleTooltipProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class SimpleLabel extends Label {
	private SimpleLanguageProperty textProperty;
	private SimpleTooltipProperty tooltipProperty;

	/**
	 * Creates an empty label.
	 */
	public SimpleLabel() {
		super();
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates Label with supplied text.
	 * 
	 * @param text null text is treated as the empty string
	 */
	public SimpleLabel(String text) {
		super(text);

		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a Label with the supplied text and graphic.
	 * 
	 * @param text    null text is treated as the empty string
	 * @param graphic a null graphic is acceptable
	 */
	public SimpleLabel(String text, Node graphic) {
		super(text, graphic);
	}

	/**
	 * Creates a label whose the text is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public SimpleLabel(ICode code, Object... args) {
		setText(code, args);
	}

	/**
	 * Set the text of the label. The text is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public void setText(ICode code, Object... args) {
		if (textProperty == null) {
			textProperty = GuiHelper.getPropertyHelper().newLanguageProperty(code, args);
			textProperty().bind(textProperty);
		} else
			textProperty.setCode(code, args);
	}

	/**
	 * Set the Tooltip of this label.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public void setTooltip(ICode code, Object... args) {
		if (tooltipProperty == null) {
			tooltipProperty = GuiHelper.getPropertyHelper().newTooltipProperty(code, args);
			tooltipProperty().bind(tooltipProperty);
		} else
			tooltipProperty.setCode(code, args);
	}
}
