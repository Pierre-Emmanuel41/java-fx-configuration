package fr.pederobien.javafx.configuration.persistence;

public enum GuiConfigurationXmlTag {

	/**
	 * The configuration's name.
	 */
	NAME("name"),

	/**
	 * The configuration's locale.
	 */
	LOCALE("locale"),

	/**
	 * The configuration's font.
	 */
	FONT("font"),

	/**
	 * The font's family.
	 */
	FONT_FAMILY("fontFamily"),

	/**
	 * The font's size.
	 */
	FONT_SIZE("fontSize")

	;

	private String name;

	private GuiConfigurationXmlTag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
