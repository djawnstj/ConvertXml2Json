package com.djawnstj.resource;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceUtils {

    public static final String JAR_EXTENSION = ".jar";
    public static final String EXTENSION_SEPERATOR = ".";

    private ResourceUtils() {
    }

    public static List<File> getResources(final String path) {
        try {
            if (isNotJar()) {
                return getResourceCaseClassLoader(path);
            }

            return getResourcesCaseJar(path);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new RuntimeException("XML 리소스를 가져오는데 실패했습니다.", e);
        }
    }

    private static boolean isNotJar() {
        try {
            final URI codeLocation = ResourceUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            final Path basePath = Paths.get(codeLocation);

            return !basePath.getFileName().toString().endsWith(JAR_EXTENSION);
        } catch (NullPointerException | URISyntaxException e) {
            return false;
        }
    }

    private static List<File> getResourceCaseClassLoader(final String path) {
        try {
            final URL resource = ResourceUtils.class.getClassLoader().getResource(path);

            if (resource == null) {
                return Collections.emptyList();
            }

            final File file = new File(resource.toURI());

            if (!file.isDirectory()) {
                return Collections.emptyList();
            }

            return getResourcesInDirectory(file);
        } catch (URISyntaxException e) {
            throw new RuntimeException("XML 리소스를 가져오는데 실패했습니다.", e);
        }
    }

    private static List<File> getResourcesCaseJar(final String path) {
        try {
            final URI codeLocation = ResourceUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI();

            final Path targetPath = Paths.get(codeLocation).getParent().resolve(path);

            final File file = targetPath.toFile();

            if (!file.isDirectory()) {
                return Collections.emptyList();
            }

            return getResourcesInDirectory(file);
        } catch (URISyntaxException e) {
            throw new RuntimeException("XML 리소스를 가져오는데 실패했습니다.", e);
        }
    }

    private static List<File> getResourcesInDirectory(final File directory) {
        try (final Stream<Path> paths = Files.walk(directory.toPath())) {
            return paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("XML 리소스를 가져오는데 실패했습니다.", e);
        }
    }

    public static boolean hasExtension(final File file, final String extension) {
        return getExtension(file).equals(extension);
    }

    private static String getExtension(final File file) {
        final String fileName = file.getName();
        final int lastIndexOf = fileName.lastIndexOf(EXTENSION_SEPERATOR);

        if (lastIndexOf == -1) {
            return ""; // 확장자가 없는 경우
        }

        return fileName.substring(lastIndexOf + 1);
    }

    public static String getFileNameWithoutExtension(final File file) {
        final String fileName = file.getName();
        final int lastDotIndex = fileName.lastIndexOf(EXTENSION_SEPERATOR);

        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(0, lastDotIndex);
        } else {
            return fileName;
        }
    }

    public static void saveResourceFile(final String path, final String name, final String extension, final String content) {
        try {
            final Path directoryPath = Paths.get(path);
            Files.createDirectories(directoryPath);

            final String fileName = toFileName(name, extension);
            final File file = new File(directoryPath.toFile(), fileName);

            try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8))) {
                writer.write(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toFileName(final String name, final String extension) {
        return name + (extension.startsWith(EXTENSION_SEPERATOR) ? extension : EXTENSION_SEPERATOR + extension);
    }
}
