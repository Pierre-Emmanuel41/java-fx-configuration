package fr.pederobien.javafx.configuration.impl.properties;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;

public class PropertyHelper {
	private GuiConfigurationEventListener listener;

	/**
	 * Creates a property helper based on this GUI configuration.
	 * 
	 * @param configuration The configuration used to create properties.
	 */
	public PropertyHelper(IGuiConfiguration configuration) {
		listener = new GuiConfigurationEventListener(configuration);
	}

	/**
	 * Set the GUI configuration associated to this property helper.
	 * 
	 * @param configuration The new configuration of the helper.
	 */
	public void setConfiguration(IGuiConfiguration configuration) {
		listener.setConfiguration(configuration);
	}

	/**
	 * Create a string property based on the gui configuration associated to this helper. If the local parameter in the gui
	 * configuration changes then this property is automatically updated.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 * 
	 * @return the created language property.
	 */
	public SimpleLanguageProperty languageProperty(ICode code, Object... args) {
		return new SimpleLanguageProperty(listener, code, args);
	}

	/**
	 * Create a font property based on the gui configuration associated to this helper. If the font parameter in the gui configuration
	 * changes then this font property is automatically updated.
	 * 
	 * @return the created font property.
	 */
	public SimpleFontProperty fontProperty() {
		return new SimpleFontProperty(listener);
	}

	/**
	 * Create a tooltip property based on the gui configuration associated to this helper. if the font or local parameter in the gui
	 * configuration changes then this property is also updated.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 * 
	 * @return the created tooltip property.
	 */
	public SimpleTooltipProperty tooltipProperty(ICode code, Object... args) {
		return new SimpleTooltipProperty(listener, code, args);
	}
}