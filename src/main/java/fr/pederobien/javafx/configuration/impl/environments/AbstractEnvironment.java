package fr.pederobien.javafx.configuration.impl.environments;

import java.io.IOException;
import java.nio.file.Path;

import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.interfaces.IEnvironment;
import fr.pederobien.javafx.configuration.interfaces.IEnvironmentVariable;
import javafx.scene.image.Image;

public abstract class AbstractEnvironment implements IEnvironment {
	private static final char SLASH = '\\';
	private static final char FORWARD_SLASH = '/';
	private IDictionaryContext dictionaryContext;
	private EnvironmentVariables variables;

	/**
	 * Creates a abstract software environment.
	 * 
	 * @param variables         A map that store variables for this environment.
	 * @param dictionaryContext The context that gather dictionaries in order to display language sensitive messages.
	 */
	protected AbstractEnvironment(EnvironmentVariables variables, IDictionaryContext dictionaryContext) {
		this.variables = variables;
		this.dictionaryContext = dictionaryContext;
	}

	@Override
	public void add(IEnvironmentVariable variable) {
		variables.add(variable);
	}

	@Override
	public void remove(IEnvironmentVariable variable) {
		variables.remove(variable);
	}

	@Override
	public Path getPath(String name) {
		return variables.getPath(name);
	}

	@Override
	public void registerDictionary(String dictionaryName) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Image loadImage(String imageName) throws IOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return The context that gather dictionaries in order to display language sensitive messages.
	 */
	protected IDictionaryContext getDictionaryContext() {
		return dictionaryContext;
	}

	/**
	 * Get the path associated to the given dictionary name.
	 * 
	 * @param name The name of the dictionary used to get the path associated to its file.
	 * 
	 * @return The path leading to the dictionary file.
	 */
	protected String getDictionaryPath(String name) {
		Path dictionaries = variables.getPath(EnvironmentVariables.DICTIONARIES_ENV_VARIABLE_NAME);
		if (dictionaries == null)
			throw new IllegalStateException(String.format("There is no environment variables associated to '%s'", EnvironmentVariables.DICTIONARIES_ENV_VARIABLE_NAME));

		return dictionaries.resolve(name).toString().replace(SLASH, FORWARD_SLASH);
	}

	/**
	 * Get the path associated to the given image name.
	 * 
	 * @param name The image name used to get the path associated to its file.
	 * 
	 * @return The path leading to the image file.
	 */
	protected String getImagePath(String name) {
		Path images = variables.getPath(EnvironmentVariables.IMAGES_ENV_VARIABLE_NAME);
		if (images == null)
			throw new IllegalStateException(String.format("There is no environment variables associated to '%s'", EnvironmentVariables.IMAGES_ENV_VARIABLE_NAME));

		return images.resolve(name).toString().replace(SLASH, FORWARD_SLASH);
	}
}
