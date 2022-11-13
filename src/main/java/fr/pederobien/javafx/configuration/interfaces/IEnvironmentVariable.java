package fr.pederobien.javafx.configuration.interfaces;

import java.nio.file.Path;

public interface IEnvironmentVariable {

	/**
	 * @return The name of this environment variable.
	 */
	String getName();

	/**
	 * @return The path of this environment variable.
	 */
	Path getPath();

	/**
	 * Set the path of this environment variable.
	 * 
	 * @param path The new path of this environment variable.
	 */
	void setPath(Path path);
}
