import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void sampleZero() {
        assertEquals("NO", Result.isValid("aabbcd"));
    }

    @Test
    void sampleOne() {
        assertEquals("NO", Result.isValid("aabbccddeefghi"));
    }

    @Test
    void sampleTwo() {
        assertEquals("YES", Result.isValid("abcdefghhgfedecba"));
    }

    @Test
    void sampleAbc() {
        assertEquals("YES", Result.isValid("abc"));
    }

    @Test
    void sampleAbcc() {
        assertEquals("YES", Result.isValid("abcc"));
    }

    @Test
    void sampleAbccc() {
        assertEquals("NO", Result.isValid("abccc"));
    }
}
