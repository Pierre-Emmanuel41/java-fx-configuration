package fr.pederobien.javafx.configuration.persistence.loaders;

import org.w3c.dom.Element;

import fr.pederobien.javafx.configuration.interfaces.IGuiConfiguration;
import fr.pederobien.javafx.configuration.persistence.GuiConfigurationXmlTag;

public class GuiConfigurationSerializerV10 extends AbstractGuiConfigurationSerializer {

	public GuiConfigurationSerializerV10() {
		super(1.0);
	}

	@Override
	public boolean serialize(IGuiConfiguration element, Element root) {
		Element name = createElement(GuiConfigurationXmlTag.NAME);
		name.appendChild(createTextNode(element.getName()));
		root.appendChild(name);

		Element locale = createElement(GuiConfigurationXmlTag.LOCALE);
		locale.appendChild(createTextNode(element.getLocale().getLanguage()));
		root.appendChild(locale);

		Element font = createElement(GuiConfigurationXmlTag.FONT);
		setAttribute(font, GuiConfigurationXmlTag.FONT_FAMILY, element.getFont().getFamily());
		setAttribute(font, GuiConfigurationXmlTag.FONT_SIZE, element.getFont().getSize());
		root.appendChild(font);
		return true;
	}

	@Override
	public boolean deserialize(IGuiConfiguration element, Element root) {
		setName(element, root);
		setLocale(element, root);
		setFont(element, root);
		return true;
	}
}
