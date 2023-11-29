package edu.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateFileTest {
    Path path = Path.of("src/main/resources/project3/file.txt");
    private LogReport logReport;

    @BeforeEach
    void setUp() throws IOException {

        Files.createDirectories(path.getParent());
        Files.createFile(path);

        List<String> lines = new ArrayList<>();
        lines.add(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"");
        lines.add(
            "217.168.17.5 - - [17/May/2015:08:05:34 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.8.10.3)\"");
        lines.add(
            "217.168.17.5 - - [17/May/2015:08:05:12 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 3316 \"-\" \"-\"");
        lines.add(
            "188.138.60.101 - - [17/May/2015:08:05:49 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ignored) {
        }

        logReport = new LogReport(path.toString(), null, null);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (path.getParent().toFile().listFiles() == null) {
            return;
        }
        for (File file : Objects.requireNonNull(path.getParent().toFile().listFiles())) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
        Files.deleteIfExists(path.getParent());
    }

    @Test
    void generateMarkdownReport_shouldGenerateCorrectMarkdown() {
        String expectedMarkdown = "#### Общая информация\n" +
            "| Метрика | Значение |\n" +
            "|:-:|-:|\n" +
            "| Файл | `file.txt` |\n" +
            "| Начальная дата |- |\n" +
            "| Конечная дата |- |\n" +
            "| Количество запросов | 4 |\n" +
            "| Средний размер ответа |  951b |\n\n" +
            "#### Запрашиваемые ресурсы\n" +
            "|     Ресурс      | Количество |\n" +
            "|:-:|-:|\n" +
            "| `/downloads/product_1` | 2 |\n" +
            "| `/downloads/product_2` | 2 |\n\n" +
            "#### Коды ответа\n" +
            "| Код | Имя| Количество |\n" +
            "|:-:|:-:|-:|\n" +
            "| 304 | Redirection| 2 |\n" +
            "| 200 | OK| 2 |\n";

        String generatedMarkdown = CreateFile.generateMarkdownReport(logReport);
        assertEquals(expectedMarkdown, generatedMarkdown);
    }

    @Test
    void createMarkdown_shouldCreateMarkdownFile() {
        Path outputPath = Path.of("Log report.md");

        CreateFile.createMarkdown(logReport);

        assertTrue(Files.exists(outputPath));

    }
}
