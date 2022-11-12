package fr.pederobien.javafx.configuration.impl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import fr.pederobien.javafx.configuration.events.ConfigurationFontChangePostEvent;
import fr.pederobien.javafx.configuration.events.ConfigurationLocaleChangePostEvent;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.utils.event.EventHandler;
import fr.pederobien.utils.event.EventManager;
import fr.pederobien.utils.event.IEventListener;

public class GuiConfigurationEventListener implements IEventListener {
	private Map<Action, List<Consumer<PropertyChangeEvent>>> actions;
	private IGuiConfiguration configuration;

	/**
	 * Creates an event listener associated to a GUI configuration.
	 * 
	 * @param configuration The configuration associated to this event listener.
	 */
	public GuiConfigurationEventListener(IGuiConfiguration configuration) {
		this.configuration = configuration;
		EventManager.registerListener(this);
		actions = new HashMap<Action, List<Consumer<PropertyChangeEvent>>>();
	}

	/**
	 * Register an action to execute for the given enumeration value.
	 * 
	 * @param action   The action type.
	 * @param consumer The code to execute.
	 */
	public void registerAction(Action action, Consumer<PropertyChangeEvent> consumer) {
		List<Consumer<PropertyChangeEvent>> toDo = actions.get(action);
		if (toDo == null) {
			toDo = new ArrayList<Consumer<PropertyChangeEvent>>();
			actions.put(action, toDo);
		}

		toDo.add(consumer);
	}

	/**
	 * @return The GUI configuration this internal property observes.
	 */
	public IGuiConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * Set the GUI configuration associated to this listener;
	 * 
	 * @param configuration The new GUI configuration associated to this
	 */
	public void setConfiguration(IGuiConfiguration configuration) {
		if (getConfiguration().equals(configuration))
			return;

		IGuiConfiguration oldConfiguration = getConfiguration();
		this.configuration = configuration;

		// Updating properties
		firePropertyChanged(Action.LOCALE_CHANGED, oldConfiguration.getLocale(), getConfiguration().getLocale());
		firePropertyChanged(Action.FONT_CHANGED, oldConfiguration.getFont(), getConfiguration().getFont());
	}

	public enum Action {
		/**
		 * Action to perform when the local of the configuration has changed.
		 */
		LOCALE_CHANGED("locale"),

		/**
		 * Action to perform when the font of the configuration has changed.
		 */
		FONT_CHANGED("font"),

		;

		private String propertyName;

		private Action(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * @return The name of the property associated to this action.
		 */
		public String getPropertyName() {
			return propertyName;
		}
	}

	@EventHandler
	public void onLanguageChanged(ConfigurationLocaleChangePostEvent event) {
		if (!event.getConfiguration().equals(configuration))
			return;

		firePropertyChanged(Action.LOCALE_CHANGED, event.getOldLocale(), event.getCurrentLocale());
	}

	@EventHandler
	public void onFontChanged(ConfigurationFontChangePostEvent event) {
		firePropertyChanged(Action.FONT_CHANGED, event.getOldFont(), event.getCurrentFont());
	}

	private void firePropertyChanged(Action action, Object oldValue, Object newValue) {
		if (newValue.equals(oldValue))
			return;

		List<Consumer<PropertyChangeEvent>> toDo = actions.get(action);
		if (toDo != null)
			for (Consumer<PropertyChangeEvent> consumer : toDo)
				consumer.accept(new PropertyChangeEvent(this, action.getPropertyName(), oldValue, newValue));
	}
}