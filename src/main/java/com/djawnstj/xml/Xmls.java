package com.djawnstj.xml;

import com.djawnstj.json.JsonString;
import com.djawnstj.resource.ResourceUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Xmls implements Iterable<Xml> {
    public static final String XML_RESOURCE_PATH = "target";

    private final List<Xml> xmls;

    public Xmls() {
        this.xmls = ResourceUtils.getResources(XML_RESOURCE_PATH)
                .stream()
                .filter(file -> ResourceUtils.hasExtension(file, "xml"))
                .map(Xml::new)
                .collect(Collectors.toList());
    }

    public List<JsonString> toJsonStrings() {
        return this.xmls.stream()
                .map(JsonString::from)
                .collect(Collectors.toList());
    }

    public void saveToJson() {
        this.toJsonStrings().forEach(JsonString::saveJsonFile);
    }

    public boolean isEmpty() {
        return this.xmls.isEmpty();
    }

    @Override
    public Iterator<Xml> iterator() {
        return this.xmls.iterator();
    }
}
