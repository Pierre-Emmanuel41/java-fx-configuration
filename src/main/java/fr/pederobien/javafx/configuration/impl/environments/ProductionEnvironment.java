package fr.pederobien.javafx.configuration.impl.environments;

import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.JarFile;

import fr.pederobien.dictionary.impl.JarXmlDictionaryParser;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.interfaces.IEnvironment;
import javafx.scene.image.Image;

public class ProductionEnvironment extends AbstractEnvironment implements IEnvironment {
	private JarXmlDictionaryParser dictionaryParser;
	private Path jarPath;

	/**
	 * Creates a environment when the software is running from a jar file.
	 * 
	 * @param variables         A map that store variables for this environment.
	 * @param jarPath           The path leading to the jar file.
	 * @param dictionaryContext The context that gather dictionaries in order to display language sensitive messages.
	 */
	public ProductionEnvironment(EnvironmentVariables variables, Path jarPath, IDictionaryContext dictionaryContext) {
		super(variables, dictionaryContext);
		this.jarPath = jarPath;
		dictionaryParser = new JarXmlDictionaryParser(jarPath);
	}

	@Override
	public void registerDictionary(String dictionaryName) throws Exception {
		getDictionaryContext().register(dictionaryParser.parse(getDictionaryPath(dictionaryName)));
	}

	@Override
	public Image loadImage(String imageName) throws IOException {
		JarFile jarFile = new JarFile(jarPath.toFile());
		Image image = new Image(jarFile.getInputStream(jarFile.getEntry(getImagePath(imageName))));
		jarFile.close();
		return image;
	}
}
