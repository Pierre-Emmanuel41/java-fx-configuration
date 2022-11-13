package fr.pederobien.javafx.configuration.impl.environments;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.interfaces.IEnvironment;
import fr.pederobien.javafx.configuration.interfaces.IEnvironmentVariable;
import javafx.scene.image.Image;

public class Environment implements IEnvironment {
	private static final String FILE_PREFIX = "file";
	private static final String JAR_PREFIX = "jar";

	private IDictionaryContext dictionaryContext;
	private IEnvironment development, production, current;
	private EnvironmentVariables variables;

	/**
	 * Creates an environment for a software. The environment implementation depends on the source the software is running. If the
	 * source is an IDE then the environment is a development environment, if the source is a .jar file then the environment is a
	 * production environment.
	 * 
	 * @param dictionaryContext The context that gather dictionaries in order to display language sensitive messages to the user.
	 */
	public Environment(IDictionaryContext dictionaryContext) {
		this.dictionaryContext = dictionaryContext;
		String url = Environment.class.getResource(Environment.class.getSimpleName() + ".class").toExternalForm();
		Path jarPath = Paths.get(url.split("!")[0].substring(String.format("%s:%s:/", FILE_PREFIX, JAR_PREFIX).length()).replace("%20", " "));

		variables = new EnvironmentVariables();
		development = new DevelopmentEnvironment(variables, dictionaryContext);
		production = new ProductionEnvironment(variables, jarPath, dictionaryContext);

		current = url.startsWith(FILE_PREFIX) ? development : url.startsWith(JAR_PREFIX) ? production : null;
		if (current == null)
			throw new UnsupportedOperationException("Technical error, the environment is neither a development environment nor a production environment");
	}

	@Override
	public void add(IEnvironmentVariable variable) {
		current.add(variable);
	}

	@Override
	public void remove(IEnvironmentVariable variable) {
		current.remove(variable);
	}

	@Override
	public Path getPath(String name) {
		return current.getPath(name);
	}

	@Override
	public void registerDictionary(String dictionaryName) throws Exception {
		current.registerDictionary(dictionaryName);
	}

	@Override
	public Image loadImage(String imageName) throws IOException {
		return current.loadImage(imageName);
	}

	public void setDictionaryContext(IDictionaryContext context) {
		if (this.dictionaryContext.equals(dictionaryContext))
			return;

		Collection<IDictionary> oldDictionaries = this.dictionaryContext.getDictionaries().values();
		for (IDictionary dictionary : oldDictionaries)
			this.dictionaryContext.unregister(dictionary);

		Collection<IDictionary> newDictionaries = dictionaryContext.getDictionaries().values();
		for (IDictionary dictionary : newDictionaries)
			this.dictionaryContext.register(dictionary);
	}
}
