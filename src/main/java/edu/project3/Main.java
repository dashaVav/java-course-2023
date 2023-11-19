
package edu.project3;

import java.util.Objects;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class Main {
    private Main() {
    }

    private static final String PATH_STRING = "path";
    private static final String FROM_STRING = "from";
    private static final String TO_STRING = "to";
    private static final String FORMAT_STRING = "format";

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder().longOpt(PATH_STRING).hasArg().build());
        options.addOption(Option.builder().longOpt(FROM_STRING).hasArg().build());
        options.addOption(Option.builder().longOpt(TO_STRING).hasArg().build());
        options.addOption(Option.builder().longOpt(FORMAT_STRING).hasArg().build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String pathValue = cmd.getOptionValue(PATH_STRING);
            String fromValue = cmd.getOptionValue(FROM_STRING);
            String toValue = cmd.getOptionValue(TO_STRING);
            String formatValue = cmd.getOptionValue(FORMAT_STRING);

            LogReport report = new LogReport(pathValue, fromValue, toValue);
            if (formatValue == null) {
                System.out.println(CreateFile.generateMarkdownReport(report));
            }

            if (Objects.equals(formatValue, "markdown")) {
                CreateFile.createMarkdown(report);
            }

            if (Objects.equals(formatValue, "adoc")) {
                CreateFile.createAdoc((report));
            }

        } catch (ParseException e) {
            throw new RuntimeException("Invalid arguments");
        }
    }
}
