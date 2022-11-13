package fr.pederobien.javafx.configuration.impl.components;

import java.util.List;
import java.util.function.Function;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SimpleListView<T> {
	private Pane root;
	private ObjectProperty<ObservableList<T>> items;
	private ItemListObserver observer;

	/**
	 * Creates a simple list view with a specific list of item to display.
	 * 
	 * @param orientation The orientation of the list view.
	 * @param items       A list of items to display.
	 * @param viewFactory the factory used to create a view for each model element.
	 */
	public SimpleListView(Orientation orientation, ObservableList<T> items, Function<T, Node> viewFactory) {
		root = orientation == Orientation.HORIZONTAL ? new HBox() : new VBox();
		this.items = new SimpleObjectProperty<ObservableList<T>>(items);

		for (T element : items)
			root.getChildren().add(viewFactory.apply(element));

		observer = new ItemListObserver(root, viewFactory);
		getItems().addListener(observer);
	}

	/**
	 * Creates a simple list view.
	 * 
	 * @param orientation The orientation of the list view.
	 * @param viewFactory the factory used to create a view for each model element.
	 */
	public SimpleListView(Orientation orientation, Function<T, Node> viewFactory) {
		this(orientation, FXCollections.observableArrayList(), viewFactory);
	}

	/**
	 * @return The graphical component that contains a view for each model element.
	 */
	public Pane getRoot() {
		return root;
	}

	/**
	 * @return The list of model element to display.
	 */
	public final ObservableList<T> getItems() {
		return items.get();
	}

	/**
	 * @return The item property that contains the list of model elements.
	 */
	public ObjectProperty<ObservableList<T>> itemsProperty() {
		return items;
	}

	private class ItemListObserver implements ListChangeListener<T> {
		private Pane root;
		private Function<T, Node> viewFactory;

		private ItemListObserver(Pane root, Function<T, Node> viewFactory) {
			this.root = root;
			this.viewFactory = viewFactory;
		}

		@Override
		public void onChanged(Change<? extends T> change) {
			while (change.next()) {
				// Case 1: Elements added
				if (change.wasAdded()) {
					List<? extends T> added = change.getAddedSubList();
					for (int i = change.getFrom(); i < change.getTo(); i++)
						root.getChildren().add(i, viewFactory.apply(added.get(i)));
				}

				// Case 2: Elements removed
				else if (change.wasRemoved()) {
					for (int i = change.getFrom(); i < change.getTo(); i++)
						root.getChildren().remove(i);
				}

				// Case 3: Elements replaced
				else if (change.wasReplaced()) {
					List<? extends T> added = change.getAddedSubList();
					for (int i = change.getFrom(); i < change.getTo(); i++)
						root.getChildren().set(i, viewFactory.apply(added.get(i)));
				}

				// Case 4: Elements permuted
				else if (change.wasPermutated()) {
					for (int i = change.getFrom(); i < change.getTo(); i++) {
						Node first = root.getChildren().get(i);
						Node second = root.getChildren().get(change.getPermutation(i));
						root.getChildren().set(i, second);
						root.getChildren().set(change.getPermutation(i), first);
					}
				}
			}
		}
	}
}
