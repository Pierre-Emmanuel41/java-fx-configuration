package fr.pederobien.javafx.configuration.persistence.loaders;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.persistence.impl.Persistences;
import fr.pederobien.persistence.impl.xml.XmlPersistence;
import fr.pederobien.persistence.interfaces.IPersistence;

public class GuiConfigurationPersistence {
	private IGuiConfiguration configuration;
	private XmlPersistence<IGuiConfiguration> persistence;

	public GuiConfigurationPersistence(IGuiConfiguration configuration) {
		this.configuration = configuration;

		persistence = Persistences.xmlPersistence();
		persistence.register(persistence.adapt(new GuiConfigurationSerializerV10()));
	}

	/**
	 * Set the GUI configuration associated to this persistence.
	 * 
	 * @param configuration The new configuration of this persistence.
	 */
	public void setConfiguration(IGuiConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Save the GUI configuration at the specific path using XML format.
	 * 
	 * @param path The path leading to the folder that contains configuration files.
	 */
	public void serialize(String path) {
		try {
			persistence.serialize(configuration, IPersistence.LATEST, path.concat(String.format("/%s.xml", getConfiguration().getName())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the GUI configuration from the given path.
	 * 
	 * @param path The path leading to the configuration file. It should contains the file name and the extension.
	 */
	public void deserialize(String path) {
		try {
			persistence.deserialize(configuration, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return The GUI configuration associated to this persistence.
	 */
	public IGuiConfiguration getConfiguration() {
		return configuration;
	}
}
