package fr.pederobien.javafx.configuration.impl.components;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiHelper;
import fr.pederobien.javafx.configuration.impl.properties.SimpleLanguageProperty;
import fr.pederobien.javafx.configuration.impl.properties.SimpleTooltipProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class SimpleTextField extends TextField {
	private SimpleLanguageProperty textProperty;
	private SimpleLanguageProperty promptTextProperty;
	private SimpleTooltipProperty tooltipProperty;

	/**
	 * Creates a {@code TextField} with empty text content.
	 */
	public SimpleTextField() {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a {@code TextField} with initial text content.
	 *
	 * @param text A string for text content.
	 */
	public SimpleTextField(String text) {
		super(text);
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a text field whose the text is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public SimpleTextField(ICode code, Object... args) {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
		setText(code, args);
	}

	/**
	 * Creates a simple text field whose the text property is bidirectionally bound with the given text property.
	 * 
	 * @param textProperty The text field text property.
	 */
	public SimpleTextField(StringProperty textProperty) {
		fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
		textProperty().bindBidirectional(textProperty);
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
	 * Set the prompt text to display. The text is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 */
	public void setPromptText(ICode code, Object... args) {
		if (promptTextProperty == null) {
			promptTextProperty = GuiHelper.getPropertyHelper().newLanguageProperty(code, args);
			promptTextProperty().bind(promptTextProperty);
		} else
			promptTextProperty.setCode(code, args);
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
