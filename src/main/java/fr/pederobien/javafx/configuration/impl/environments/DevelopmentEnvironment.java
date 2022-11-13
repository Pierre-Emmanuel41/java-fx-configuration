package fr.pederobien.javafx.configuration.impl.environments;

import java.io.FileInputStream;
import java.io.IOException;

import fr.pederobien.dictionary.impl.XmlDictionaryParser;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.javafx.configuration.interfaces.IEnvironment;
import javafx.scene.image.Image;

public class DevelopmentEnvironment extends AbstractEnvironment implements IEnvironment {
	private XmlDictionaryParser dictionaryParser;

	/**
	 * Creates a environment when the software is running from an IDE.
	 * 
	 * @param variables         A map that store variables for this environment.
	 * @param dictionaryContext The context that gather dictionaries in order to display language sensitive messages.
	 */
	public DevelopmentEnvironment(EnvironmentVariables variables, IDictionaryContext dictionaryContext) {
		super(variables, dictionaryContext);
		dictionaryParser = new XmlDictionaryParser();
	}

	@Override
	public void registerDictionary(String dictionaryName) throws Exception {
		getDictionaryContext().register(dictionaryParser.parse(getDictionaryPath(dictionaryName)));
	}

	@Override
	public Image loadImage(String imageName) throws IOException {
		return new Image(new FileInputStream(getImagePath(imageName)));
	}
}
