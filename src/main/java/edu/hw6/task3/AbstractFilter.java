package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:ConstantName")
@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path entry);

    AbstractFilter regularFile = Files::isRegularFile;
    AbstractFilter readable = Files::isReadable;
    AbstractFilter writable = Files::isWritable;

    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }

    static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter smallerThan(long size) {
        return path -> {
            try {
                return Files.size(path) < size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter sizeEqualsTo(long size) {
        return path -> {
            try {
                return Files.size(path) == size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String extension) {
        return path -> path.toString().toLowerCase()
            .endsWith(extension.substring(extension.lastIndexOf('.')).toLowerCase());
    }

    static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile(regex).matcher(path.getFileName().toString()).find();
    }

    static AbstractFilter magicNumber(byte... magicBytes) {
        return path -> {
            try {
                byte[] actualBytes = Files.readAllBytes(path);
                if (actualBytes.length < magicBytes.length) {
                    return false;
                }
                for (int i = 0; i < magicBytes.length; i++) {
                    if (actualBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }
}

