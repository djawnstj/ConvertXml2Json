package com.djawnstj.xml;

import com.djawnstj.resource.ResourceUtils;
import com.djawnstj.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlTest {

    @DisplayName("xml 을 json 문자열로 변환한다")
    @Test
    public void toJsonString() throws Exception {
        // given
        final List<File> xmls = ResourceUtils.getResources("test-target");
        final Xml xml = new Xml(xmls.get(0));

        // when
        final String actual = xml.toJsonString();

        // then
        final String expectedString = "{\n" +
                "  \"views\": [\n" +
                "    {\n" +
                "      \"views\": [],\n" +
                "      \"properties\": [\n" +
                "        {\n" +
                "          \"value\": \"TextView\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"name\": \"text\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"wrap_content\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_width\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"wrap_content\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_height\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"button\",\n" +
                "          \"type\": \"ref\",\n" +
                "          \"name\": \"layout_below\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentLeft\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentStart\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"textView\",\n" +
                "          \"type\": \"ref\",\n" +
                "          \"name\": \"id\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"80px\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"textSize\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"widget\": \"TextView\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"views\": [],\n" +
                "      \"properties\": [\n" +
                "        {\n" +
                "          \"value\": \"180px\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_width\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"180px\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_height\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"@mipmap/ic_launcher\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"name\": \"src\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"textView\",\n" +
                "          \"type\": \"ref\",\n" +
                "          \"name\": \"layout_below\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentLeft\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentStart\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"imageView\",\n" +
                "          \"type\": \"ref\",\n" +
                "          \"name\": \"id\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"widget\": \"ImageView\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"views\": [],\n" +
                "      \"properties\": [\n" +
                "        {\n" +
                "          \"value\": \"\\uC2DC\\uC791\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"name\": \"text\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"wrap_content\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_width\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"wrap_content\",\n" +
                "          \"type\": \"dimen\",\n" +
                "          \"name\": \"layout_height\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"button\",\n" +
                "          \"type\": \"ref\",\n" +
                "          \"name\": \"id\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentTop\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentLeft\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"true\",\n" +
                "          \"type\": \"BOOLEAN\",\n" +
                "          \"name\": \"layout_alignParentStart\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"widget\": \"Button\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"properties\": [\n" +
                "    {\n" +
                "      \"value\": \"activity_main\",\n" +
                "      \"type\": \"ref\",\n" +
                "      \"name\": \"id\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"value\": \"match_parent\",\n" +
                "      \"type\": \"dimen\",\n" +
                "      \"name\": \"layout_width\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"value\": \"match_parent\",\n" +
                "      \"type\": \"dimen\",\n" +
                "      \"name\": \"layout_height\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"widget\": \"RelativeLayout\"\n" +
                "}\n";

        final ObjectMapper objectMapper = new ObjectMapper();
        final View expected = objectMapper.readValue(expectedString, View.class);
        final View expected1 = objectMapper.readValue(actual, View.class);

        assertEquals(expected1, expected);
    }

}
