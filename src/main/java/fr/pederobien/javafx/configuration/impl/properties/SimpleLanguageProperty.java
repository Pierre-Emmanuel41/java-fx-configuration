package fr.pederobien.javafx.configuration.impl.properties;

import fr.pederobien.dictionary.impl.MessageEvent;
import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener.Action;
import javafx.beans.property.SimpleStringProperty;

public class SimpleLanguageProperty extends SimpleStringProperty {
	private GuiConfigurationEventListener listener;
	private ICode code;
	private Object[] args;

	/**
	 * Creates a language property. A language property is a simple String property but whose the message is language sensitive.
	 * 
	 * @param listener The listener that updates this language property.
	 * @param code     The code associated to the message to display.
	 * @param args     The message arguments if the message needs arguments.
	 */
	public SimpleLanguageProperty(GuiConfigurationEventListener listener, ICode code, Object... args) {
		this.listener = listener;
		this.code = code;
		this.args = args;

		listener.registerAction(Action.LOCALE_CHANGED, o -> update());
		update();
	}

	/**
	 * @return The code associated to this property.
	 */
	public ICode getCode() {
		return code;
	}

	/**
	 * Set the code associated to this property. This will automatically change the displayed text.
	 * 
	 * @param code The new code associated to the message to display.
	 * @param args The new arguments array associated to the code.
	 */
	public void setCode(ICode code, Object... args) {
		this.code = code;
		this.args = args;
		update();
	}

	/**
	 * @return An array that contains arguments for message that need parameters.
	 */
	public Object[] getArgs() {
		return args;
	}

	private void update() {
		IDictionaryContext context = listener.getConfiguration().getDictionaryContext();
		setValue(context.getMessage(new MessageEvent(listener.getConfiguration().getLocale(), getCode(), getArgs())));
	}
}
