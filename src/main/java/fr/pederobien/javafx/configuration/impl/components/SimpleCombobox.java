package fr.pederobien.javafx.configuration.impl.components;

import fr.pederobien.javafx.configuration.impl.GuiHelper;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SimpleCombobox<T> extends ComboBox<T> {

	/**
	 * Creates a default ComboBox instance with an empty {@link #itemsProperty() items} list and default
	 * {@link #selectionModelProperty() selection model}.
	 */
	public SimpleCombobox() {
		getEditor().fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}

	/**
	 * Creates a default ComboBox instance with the provided items list and a default {@link #selectionModelProperty() selection
	 * model}.
	 * 
	 * @param items the list of items
	 */
	public SimpleCombobox(ObservableList<T> items) {
		super(items);
		getEditor().fontProperty().bind(GuiHelper.getPropertyHelper().newFontProperty());
	}
}
