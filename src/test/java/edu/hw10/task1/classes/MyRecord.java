package edu.hw10.task1.classes;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record MyRecord(@Max(12) @Min(11) int intValue, @Max(1) double doubleValue, @NotNull String string) {
}
