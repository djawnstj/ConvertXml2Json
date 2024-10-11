package com.djawnstj.xml;

import com.djawnstj.mapper.JsonWriter;
import com.djawnstj.resource.ResourceUtils;
import com.djawnstj.view.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Xml {
    private final String fileName;
    private final String path;
    private final File content;

    public Xml(final File content) {
        this.fileName = ResourceUtils.getFileNameWithoutExtension(content);
        this.path = content.getParent();
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return this.path;
    }

    public String toJsonString() {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            final DocumentBuilder builder = factory.newDocumentBuilder();

            final Document document = builder.parse(this.content);
            final View rootView = View.from(document.getDocumentElement());

            return JsonWriter.INSTANCE.writeValueAsString(rootView);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("XML 파싱 구성 중 에러가 발생했습니다.", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("XML 파일을 JSON 으로 변경 중 에러가 발생했습니다.", e);
        } catch (IOException | SAXException e) {
            throw new RuntimeException("XML 파싱 중 에러가 발생했습니다.", e);
        }
    }
}
