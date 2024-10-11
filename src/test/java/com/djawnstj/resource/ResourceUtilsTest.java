package com.djawnstj.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceUtilsTest {

    @DisplayName("resources 폴더 내 파라미터로 받은 폴더 내부의 파일들을 반환 한다")
    @Test
    public void getResources() throws Exception {
        // given
        final String path = "test-file";

        // when
        final List<File> resources = ResourceUtils.getResources(path);

        // then
        assertAll(
                () -> assertEquals(1, resources.size()),
                () -> assertEquals(resources.get(0).getName(), "target1.txt")
        );
    }

}
