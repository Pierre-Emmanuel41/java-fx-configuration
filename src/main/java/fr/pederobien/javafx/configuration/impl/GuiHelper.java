package fr.pederobien.javafx.configuration.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import fr.pederobien.javafx.configuration.impl.environments.Environment;
import fr.pederobien.javafx.configuration.impl.properties.PropertyHelper;
import fr.pederobien.javafx.configuration.interfaces.IEnvironmentVariable;
import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.javafx.configuration.persistence.loaders.GuiConfigurationPersistence;
import javafx.scene.image.Image;

public class GuiHelper {
	private static IGuiConfiguration configuration;
	private static final Environment ENVIRONMENT;
	private static final PropertyHelper PROPERTY_HELPER;
	private static final GuiConfigurationPersistence PERSISTENCE;

	static {
		configuration = new GuiConfiguration("Default");
		ENVIRONMENT = new Environment(configuration.getDictionaryContext());
		PROPERTY_HELPER = new PropertyHelper(configuration);
		PERSISTENCE = new GuiConfigurationPersistence(configuration);
	}

	/**
	 * @return The configuration of this helper.
	 */
	public static IGuiConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * Set the GUI configuration associated to this GUI helper.
	 * 
	 * @param configuration The new configuration of this helper.
	 */
	public static void setConfiguration(IGuiConfiguration configuration) {
		GuiHelper.configuration = configuration;
		ENVIRONMENT.setDictionaryContext(configuration.getDictionaryContext());
		PROPERTY_HELPER.setConfiguration(configuration);
		PERSISTENCE.setConfiguration(configuration);
	}

	/**
	 * Appends a new environment variable. If a variable was already registered for the name of the given variable then it is removed.
	 * 
	 * @param variable The new variable to add.
	 */
	public static void add(IEnvironmentVariable variable) {
		ENVIRONMENT.add(variable);
	}

	/**
	 * Removes an environment variable.
	 * 
	 * @param variable The variable to remove.
	 */
	public static void remove(IEnvironmentVariable variable) {
		ENVIRONMENT.remove(variable);
	}

	/**
	 * Get the path of the variable associated to the given name.
	 * 
	 * @param name The name of the variable whose the path must be returned.
	 * 
	 * @return The path of the variable if one is registered for the given name, null otherwise.
	 */
	public static Path getPath(String name) {
		return ENVIRONMENT.getPath(name);
	}

	/**
	 * Try to find the dictionary file associated to the given dictionary name. If found, then the file is parsed and the dictionary
	 * is registered.
	 * 
	 * @param dictionaryName The dictionary name used to find its associated file.
	 * 
	 * @throws FileNotFoundException if there is no file associated to the given dictionary name.
	 */
	public static void registerDictionary(String dictionaryName) throws Exception {
		ENVIRONMENT.registerDictionary(dictionaryName);
	}

	/**
	 * Try to find the file associated to given image name. If found, then the file is parsed and an image is returned.
	 * 
	 * @param imageName The image name to load.
	 * 
	 * @return The associated image.
	 * 
	 * @throws IOException If there is no file associated to the given dictionary name.
	 */
	public static Image loadImage(String imageName) throws IOException {
		return ENVIRONMENT.loadImage(imageName);
	}

	/**
	 * @return The property helper that creates properties synchronized with a GUI configuration.
	 */
	public static PropertyHelper getPropertyHelper() {
		return PROPERTY_HELPER;
	}

	/**
	 * Updates the current GUI configuration parameters from the given path.
	 * 
	 * @param path          The path leading to the folder that contains configuration files.
	 * @param configuration The configuration to update.
	 */
	public static IGuiConfiguration deserialize(String path, IGuiConfiguration configuration) {
		setConfiguration(configuration);
		PERSISTENCE.deserialize(String.format("%s/%s.xml", path, configuration.getName()));
		return getConfiguration();
	}

	/**
	 * Saves the GUI configuration parameters at the specific path using XML format.
	 * 
	 * @param path The path leading to the folder that contains configuration files.
	 */
	public static void serialize(String path) {
		PERSISTENCE.serialize(path);
	}
}
