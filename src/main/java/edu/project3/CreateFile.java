package edu.project3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public final class CreateFile {
    private CreateFile() {
    }

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:ReturnCount"})
    private static String getResponseName(int code) {
        return switch (code / 100) {
            case 1 -> "Informational";
            case 2 -> "OK";
            case 3 -> "Redirection";
            case 4 -> "Not Found";
            case 5 -> "Internal Server Error";
            default -> "Unknown";
        };
    }

    public static String generateMarkdownReport(LogReport report) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("#### Общая информация\n");
        stringBuilder.append("| Метрика | Значение |\n");
        stringBuilder.append("|:-:|-:|\n");
        stringBuilder.append("| Файл | `").append(report.getFile()).append("` |\n");

        stringBuilder.append("| Начальная дата |");
        if (report.getFrom() == null) {
            stringBuilder.append("- |\n");
        } else {
            stringBuilder.append(report.getFrom().toLocalDate()).append("|\n");
        }

        stringBuilder.append("| Конечная дата |");
        if (report.getTo() == null) {
            stringBuilder.append("- |\n");
        } else {
            stringBuilder.append(report.getTo().toLocalDate()).append("|\n");
        }

        stringBuilder.append("| Количество запросов | ").append(report.getTotalNumberOfRequests()).append(" |\n");
        stringBuilder.append(String.format(
            "| Средний размер ответа |  %db |\n\n", report.getAverageServerResponseSize()));

        stringBuilder.append("#### Запрашиваемые ресурсы\n");
        stringBuilder.append("|     Ресурс      | Количество |\n");
        stringBuilder.append("|:-:|-:|\n");
        report.getMostRequestedResources().forEach((resource, count) ->
            stringBuilder.append(String.format("| `%s` | %d |\n", resource, count)));
        stringBuilder.append("\n");

        stringBuilder.append("#### Коды ответа\n");
        stringBuilder.append("| Код | Имя| Количество |\n");
        stringBuilder.append("|:-:|:-:|-:|\n");
        report.getMostCommonResponseCodes().forEach((code, count) ->
            stringBuilder.append(String.format("| %d | " + getResponseName(code) + "| %d |\n", code, count)));

        return stringBuilder.toString();
    }

    public static void createMarkdown(LogReport report) {
        String text = generateMarkdownReport(report);
        try (PrintWriter writer = new PrintWriter(new FileWriter("Log report.md"))) {
            writer.print(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateAdocReport(LogReport report) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("== Общая информация\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Метрика | Значение\n");
        stringBuilder.append("| Файл | `").append(report.getFile()).append("`\n");

        stringBuilder.append("| Начальная дата |");
        if (report.getFrom() == null) {
            stringBuilder.append("- \n");
        } else {
            stringBuilder.append(report.getFrom().toLocalDate()).append("\n");
        }

        stringBuilder.append("| Конечная дата |");
        if (report.getTo() == null) {
            stringBuilder.append("- \n");
        } else {
            stringBuilder.append(report.getTo().toLocalDate()).append("\n");
        }

        stringBuilder.append("| Количество запросов |").append(report.getTotalNumberOfRequests()).append("\n");
        stringBuilder.append(String.format(
            "| Средний размер ответа | %db  \n",
            report.getAverageServerResponseSize()
        ));
        stringBuilder.append("|===\n\n");

        stringBuilder.append("== Запрашиваемые ресурсы\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Ресурс | Количество\n");
        report.getMostRequestedResources().forEach((resource, count) ->
            stringBuilder.append(String.format("| `%s` | %d \n", resource, count)));
        stringBuilder.append("|===\n");

        stringBuilder.append("== Коды ответа\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Код | Имя | Количество \n");
        report.getMostCommonResponseCodes().forEach((code, count) ->
            stringBuilder.append(String.format("| %d | " + getResponseName(code) + "  |    %d    \n", code, count)));
        stringBuilder.append("|===\n");

        return stringBuilder.toString();
    }

    public static void createAdoc(LogReport report) {
        String text = generateAdocReport(report);

        try (PrintWriter writer = new PrintWriter(new FileWriter("Log report.adoc"))) {
            writer.print(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
