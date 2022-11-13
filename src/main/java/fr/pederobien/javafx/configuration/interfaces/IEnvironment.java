package fr.pederobien.javafx.configuration.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javafx.scene.image.Image;

public interface IEnvironment {

	/**
	 * Appends a new environment variable. If a variable was already registered for the name of the given variable then it is removed.
	 * 
	 * @param variable The new variable to add.
	 */
	void add(IEnvironmentVariable variable);

	/**
	 * Removes an environment variable.
	 * 
	 * @param variable The variable to remove.
	 */
	void remove(IEnvironmentVariable variable);

	/**
	 * Get the path of the variable associated to the given name.
	 * 
	 * @param name The name of the variable whose the path must be returned.
	 * 
	 * @return The path of the variable if one is registered for the given name, null otherwise.
	 */
	public Path getPath(String name);

	/**
	 * Try to find the dictionary file associated to the given dictionary name. If found, then the file is parsed and the dictionary
	 * is registered.
	 * 
	 * @param dictionaryName The dictionary name used to find its associated file.
	 * 
	 * @throws FileNotFoundException if there is no file associated to the given dictionary name.
	 */
	void registerDictionary(String dictionaryName) throws Exception;

	/**
	 * Try to find the file associated to given image name. If found, then the file is parsed and an image is returned.
	 * 
	 * @param imageName The image name to load.
	 * 
	 * @return The associated image.
	 * 
	 * @throws IOException If there is no file associated to the given dictionary name.
	 */
	Image loadImage(String imageName) throws IOException;
}
