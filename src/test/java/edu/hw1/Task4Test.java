package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    void testFixStringWithEvenLength() {
        // given
        String str = "123456";

        // when
        String actualResult = Task4.fixString(str);

        // then
        assertThat(actualResult).isEqualTo("214365");
    }

    @Test
    void testFixStringWithOddLength() {
        // given
        String str = "badce";

        // when
        String actualResult = Task4.fixString(str);

        // then
        assertThat(actualResult).isEqualTo("abcde");
    }

    @Test
    void testFixStringWithText() {
        // given
        String str = "hTsii  s aimex dpus rtni.g";

        // when
        String actualResult = Task4.fixString(str);

        // then
        assertThat(actualResult).isEqualTo("This is a mixed up string.");
    }

    @Test
    void testFixStringWithEmptyString() {
        // given
        String str = "";

        // when
        String actualResult = Task4.fixString(str);

        // then
        assertThat(actualResult).isEqualTo("");
    }

    @Test
    void testFixStringWithNull() {
        assertThrows(NullPointerException.class, () -> Task4.fixString(null));
    }
}
