package fr.pederobien.javafx.configuration.impl.environments;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import fr.pederobien.javafx.configuration.interfaces.IEnvironmentVariable;

public class EnvironmentVariables {
	/**
	 * A specific name for the environment variable that gather dictionaries.
	 */
	public static final String DICTIONARIES_ENV_VARIABLE_NAME = "DICTIONARIES_FOLDER";

	/**
	 * A specific name for the environment variable that gather images.
	 */
	public static final String IMAGES_ENV_VARIABLE_NAME = "IMAGES_FOLDER";

	private Map<String, IEnvironmentVariable> variables;

	/**
	 * Creates a new empty environment variables.
	 */
	public EnvironmentVariables() {
		variables = new HashMap<String, IEnvironmentVariable>();
	}

	/**
	 * Appends a new environment variable. If a variable was already registered for the name of the given variable then it is removed.
	 * 
	 * @param variable The new variable to add.
	 */
	public void add(IEnvironmentVariable variable) {
		variables.put(variable.getName(), variable);
	}

	/**
	 * Removes an environment variable.
	 * 
	 * @param variable The variable to remove.
	 */
	public void remove(IEnvironmentVariable variable) {
		variables.remove(variable.getName());
	}

	/**
	 * Removes a environment variable.
	 * 
	 * @param name The name of the variable to remove.
	 */
	public void remove(String name) {
		variables.remove(name);
	}

	/**
	 * Get the variable associated to the given name.
	 * 
	 * @param name The name of the variable to return.
	 * 
	 * @return An optional that contains the variable if registered, an empty optional otherwise.
	 */
	public Optional<IEnvironmentVariable> getVariable(String name) {
		return Optional.ofNullable(variables.get(name));
	}

	/**
	 * Get the path of the variable associated to the given name.
	 * 
	 * @param name The name of the variable whose the path must be returned.
	 * 
	 * @return The path of the variable if one is registered for the given name, null otherwise.
	 */
	public Path getPath(String name) {
		Optional<IEnvironmentVariable> optVariable = getVariable(name);
		return optVariable.isPresent() ? optVariable.get().getPath() : null;
	}
}
