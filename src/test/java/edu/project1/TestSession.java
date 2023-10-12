package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSession {
    private Session underTest;

    @BeforeEach
    void setUp() {
        underTest = new Session("hello");
    }

    @Test
    public void testSuccessfulGuess() {
        //given
        char symbol = 'h';
        GuessResult expectedResult = new GuessResult.SuccessfulGuess("h****", 0, 5);

        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testFailedGuess() {
        //given
        char symbol = 'w';
        GuessResult expectedResult = new GuessResult.FailedGuess("*****", 1, 5);

        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testWin() {
        //given
        char symbol = 'o';
        GuessResult expectedResult = new GuessResult.Win("hello", 0, 5);
        underTest.setUserAnswer("hell*");
        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testDefeat() {
        //given
        char symbol = 'w';
        GuessResult expectedResult = new GuessResult.Defeat("*****", 5, 5);
        underTest.setAttempts(4);
        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testSuccessfulGuessWithRepeatingLetter() {
        //given
        char symbol = 'l';
        GuessResult expectedResult = new GuessResult.SuccessfulGuess("**ll*", 0, 5);

        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testFailedGuessWithRepeatingLetter() {
        //given
        char symbol = 'l';
        GuessResult expectedResult = new GuessResult.FailedGuess("**ll*", 1, 5);
        underTest.setUserAnswer("**ll*");
        //when
        GuessResult actualResult = underTest.guess(symbol);

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

    @Test
    public void testExit() {
        //given
        GuessResult expectedResult = new GuessResult.Exit("*****", 0, 5);
        //when
        GuessResult actualResult = underTest.giveUp();

        //then
        assertEquals(actualResult, expectedResult);
        assertEquals(expectedResult.message(), actualResult.message());
    }

}
