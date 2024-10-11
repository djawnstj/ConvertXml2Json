package com.djawnstj.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class View {
    private final String widget;
    private final List<Property> properties = new ArrayList<>();
    private final List<View> views = new ArrayList<>();

    @JsonCreator
    public View(
            @JsonProperty("widget") final String widget,
            @JsonProperty("properties") final List<Property> properties,
            @JsonProperty("views") final List<View> views) {
        this.widget = widget;
        this.properties.addAll(properties);
        this.views.addAll(views);
    }

    public static View from(final Element node) {
        final String nodeName = createNodeName(node);
        final List<Property> properties = createProperties(node);
        final ArrayList<View> views = createChildren(node);

        return new View(nodeName, properties, views);
    }

    private static String createNodeName(final Element node) {
        if (isViewNode(node)) {
            return "android.view.View";
        }

        return node.getTagName();
    }

    private static List<Property> createProperties(final Element node) {
        final List<Property> properties = new ArrayList<>();
        final NamedNodeMap attributes = node.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {
            final Node attr = attributes.item(i);

            if (attr.getNodeName().startsWith("xmlns:")) {
                continue;
            }

            properties.add(Property.from(attr));
        }

        return properties;
    }

    private static ArrayList<View> createChildren(final Element node) {
        final ArrayList<View> views = new ArrayList<>();
        final NodeList children = node.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);

            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            views.add(View.from((Element) child));
        }

        return views;
    }

    private static boolean isViewNode(final Element node) {
        return node.getTagName().equals("view");
    }

    public String getWidget() {
        return widget;
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(this.properties);
    }

    public List<View> getViews() {
        return Collections.unmodifiableList(this.views);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final View view = (View) o;

        final boolean isEqualProperties = new HashSet<>(properties)
                .equals(new HashSet<>(view.properties));

        return Objects.equals(widget, view.widget) && isEqualProperties && Objects.equals(views, view.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widget, properties, views);
    }
}
