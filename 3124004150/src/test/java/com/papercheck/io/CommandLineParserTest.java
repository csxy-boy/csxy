package com.papercheck.io;

import com.papercheck.exception.CommandLineArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineParserTest {

    @Test
    void threeValidArgumentsShouldPass() {
        assertDoesNotThrow(() -> CommandLineParser.validate(
                new String[]{"C:/tests/orig.txt", "C:/tests/copy.txt", "C:/tests/ans.txt"}));
    }

    @Test
    void nullArgumentsShouldThrow() {
        assertThrows(CommandLineArgumentException.class, () -> CommandLineParser.validate(null));
    }

    @Test
    void tooFewArgumentsShouldThrow() {
        assertThrows(CommandLineArgumentException.class,
                () -> CommandLineParser.validate(new String[]{"C:/tests/orig.txt"}));
    }

    @Test
    void tooManyArgumentsShouldThrow() {
        assertThrows(CommandLineArgumentException.class,
                () -> CommandLineParser.validate(new String[]{"a", "b", "c", "d"}));
    }

    @Test
    void blankArgumentShouldThrow() {
        assertThrows(CommandLineArgumentException.class,
                () -> CommandLineParser.validate(new String[]{" ", "b", "c"}));
    }
}
