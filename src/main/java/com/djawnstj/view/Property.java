package com.djawnstj.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.w3c.dom.Node;

import java.util.Objects;

public class Property {
    private final String name;
    private final String type;
    private final String value;

    @JsonCreator
    public Property(
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type,
            @JsonProperty("value") final String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public static Property from(final Node attribute) {

        final String propertyName = attribute.getLocalName();
        String type = "string";
        String value = attribute.getNodeValue();

        switch(propertyName.toUpperCase()) {
            case "ID":
            case "LAYOUT_ABOVE":
            case "LAYOUT_ALIGNBASELINE":
            case "LAYOUT_ALIGNBOTTOM":
            case "LAYOUT_ALIGNEND":
            case "LAYOUT_ALIGNLEFT":
            case "LAYOUT_ALIGNRIGHT":
            case "LAYOUT_ALIGNSTART":
            case "LAYOUT_ALIGNTOP":
            case "LAYOUT_BELOW":
            case "LAYOUT_TOENDOF":
            case "LAYOUT_TOLEFTOF":
            case "LAYOUT_TORIGHTOF":
            case "LAYOUT_TOSTARTOF":
                type = "ref";
                try {
                    value = value.split("/")[1];
                } catch (Exception ignored) {}
                break;
            case "BACKGROUND":
            case "TEXTCOLOR":
                type = "color";
                break;
            case "LAYOUT_WIDTH":
            case "LAYOUT_HEIGHT":
            case "PADDING_LEFT":
            case "PADDING_RIGHT":
            case "PADDING_TOP":
            case "PADDING_BOTTOM":
            case "PADDING":
            case "TEXTSIZE":
            case "LAYOUT_MARGINLEFT":
            case "LAYOUT_MARGINRIGHT":
            case "LAYOUT_MARGINTOP":
            case "LAYOUT_MARGINBOTTOM":
            case "LAYOUT_MARGIN":
            case "MINWIDTH":
            case "MINHEIGHT":
                type = "dimen";
                break;
            case "LAYOUT_ALIGNWITHPARENTIFMISSING":
            case "LAYOUT_CENTERHORIZONTAL":
            case "LAYOUT_CENTERINPARENT":
            case "LAYOUT_CENTERVERTICAL":
            case "LAYOUT_ALIGNPARENTBOTTOM":
            case "LAYOUT_ALIGNPARENTEND":
            case "LAYOUT_ALIGNPARENTLEFT":
            case "LAYOUT_ALIGNPARENTRIGHT":
            case "LAYOUT_ALIGNPARENTSTART":
            case "LAYOUT_ALIGNPARENTTOP":
                type = "BOOLEAN";
                break;
            case "LAYOUT_WEIGHT":
                type = "float";
                break;
            case "SCALETYPE":
                value = splitCamelCase(value).replaceAll(" ", "_");
                break;
        }

        return new Property(propertyName, type, value);
    }

    private static String splitCamelCase(final String s) {
        return s.replaceAll(
            String.format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
            ),
            " "
        );
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Property property = (Property) o;
        return Objects.equals(name, property.name) && Objects.equals(type, property.type) && Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, value);
    }
}
