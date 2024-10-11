package com.djawnstj.json;

import com.djawnstj.resource.ResourceUtils;
import com.djawnstj.xml.Xml;

import java.nio.file.Paths;

public class JsonString {
    public static final String EXTENSION = "json";
    public static final String OUTPUT_PATH = "output";

    private final String fileName;
    private final String path;
    private final String content;

    public JsonString(final String fileName, final String path, final String content) {
        this.fileName = fileName;
        this.path = path;
        this.content = content;
    }

    public void saveJsonFile() {
        ResourceUtils.saveResourceFile(path, fileName, EXTENSION, content);
    }

    public static JsonString from(final Xml xml) {
        return new JsonString(xml.getFileName(), getOutputPath(xml), xml.toJsonString());
    }

    private static String getOutputPath(final Xml xml) {
        return Paths.get(xml.getPath()).getParent().resolve(OUTPUT_PATH).toString();
    }
}
