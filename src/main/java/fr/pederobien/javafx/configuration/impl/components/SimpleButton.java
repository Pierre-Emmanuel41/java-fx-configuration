package fr.pederobien.javafx.configuration.impl.components;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiHelper;
import fr.pederobien.javafx.configuration.impl.properties.SimpleLanguageProperty;
import fr.pederobien.javafx.configuration.impl.properties.SimpleTooltipProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SimpleButton extends Button {
	private SimpleLanguageProperty textProperty;
	private SimpleTooltipProperty tooltipProperty;

	/**
	 * Creates a button with an empty string for its label.
	 */
	public SimpleButton() {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a button with the specified text as its label.
	 *
	 * @param text A text string for its label.
	 */
	public SimpleButton(String text) {
		super(text);
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a button with the specified text and icon for its label.
	 *
	 * @param text    A text string for its label.
	 * @param graphic the icon for its label.
	 */
	public SimpleButton(String text, Node graphic) {
		super(text, graphic);
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Set the text of the button. The text is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public SimpleButton(ICode code, Object... args) {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
		setText(code, args);
	}

	/**
	 * Creates a simple button whose the text property is bound with the given text property.
	 * 
	 * @param textProperty The button text property.
	 */
	public SimpleButton(StringProperty textProperty) {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
		textProperty().bind(textProperty);
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
