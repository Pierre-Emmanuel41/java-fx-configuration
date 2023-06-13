package fr.pederobien.javafx.configuration.impl.properties;

import java.util.function.Function;

import fr.pederobien.dictionary.interfaces.ICode;
import fr.pederobien.javafx.configuration.impl.GuiConfigurationEventListener;
import fr.pederobien.javafx.configuration.impl.components.SimpleListCell;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import javafx.scene.Node;
import javafx.scene.paint.Color;

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
	 * Creates a language property. A language property is a simple String property but whose the message is language sensitive.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 * 
	 * @return A new language property.
	 */
	public SimpleLanguageProperty newLanguageProperty(ICode code, Object... args) {
		return new SimpleLanguageProperty(listener, code, args);
	}

	/**
	 * @return A new font property synchronized with a GUI configuration.
	 */
	public SimpleFontProperty newFontProperty() {
		return new SimpleFontProperty(listener);
	}

	/**
	 * Creates a Tooltip property whose the font and the message is synchronized with a GUI configuration.
	 * 
	 * @param code The code associated to the message to display.
	 * @param args The message arguments if the message needs arguments.
	 * 
	 * @return A new Tooltip property.
	 */
	public SimpleTooltipProperty newTooltipProperty(ICode code, Object... args) {
		return new SimpleTooltipProperty(listener, code, args);
	}

	/**
	 * Create a cell view. If the entered color is null then the background is not updated when the mouse enter or exit the component.
	 * 
	 * @param constructor  The constructor that create the graphic of the cell.
	 * @param enteredColor Color when mouse entered in the cell.
	 */
	public <T> SimpleListCell<T> newListCell(Function<T, Node> constructor, Color enteredColor) {
		return new SimpleListCell<T>(constructor, enteredColor);
	}
}