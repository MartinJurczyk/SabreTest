import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SabreIndexerTest {
    /**
     Write a Java program, which will index each letter in a text to words.
     Program does not have to take letter case into consideration.
     Words that occur many times in a text must be presented only once in output.
     List of words in which particular letter occurs, should be sorted alphabetically.
     **/

    @Test
    public void sabreTest() {
        // given
        SabreIndexer sabreIndexer = new SabreIndexer();
        String expected = "a: ala, javie, kota, ma\n" +
                "d: koduje\n" +
                "e: javie, koduje\n" +
                "i: javie\n" +
                "j: javie, koduje\n" +
                "k: koduje, kot, kota\n" +
                "l: ala\n" +
                "m: ma\n" +
                "o: koduje, kot, kota\n" +
                "t: kot, kota\n" +
                "u: koduje\n" +
                "v: javie\n" +
                "w: w\n";

        // when
        String result = sabreIndexer.indexText("ala ma kota, kot koduje w Javie Kota");

        // then
        assertEquals(expected, result);
    }

    @Test
    public void caseInsensitiveTest() {
        // given
        SabreIndexer sabreIndexer = new SabreIndexer();
        String expected = "a: a\n" +
                "b: b\n";

        // when
        String result = sabreIndexer.indexText("B A a b");

        // then
        assertEquals(expected, result);
    }

    @Test
    public void sortedTest() {
        // given
        SabreIndexer sabreIndexer = new SabreIndexer();
        String expected = "a: a, ba, ca, da\n" +
                "b: ba\n" +
                "c: ca\n" +
                "d: da\n";

        // when
        String result = sabreIndexer.indexText("a ba ca da");

        // then
        assertEquals(expected, result);
    }

    @Test
    public void duplicationInsensitiveTest() {
        // given
        SabreIndexer sabreIndexer = new SabreIndexer();
        String expected = "a: duplication\n" +
                "c: duplication\n" +
                "d: duplication\n" +
                "e: unique\n" +
                "i: duplication, unique\n" +
                "l: duplication\n" +
                "n: duplication, unique\n" +
                "o: duplication\n" +
                "p: duplication\n" +
                "q: unique\n" +
                "t: duplication\n" +
                "u: duplication, unique\n";

        // when
        String result = sabreIndexer.indexText("duplication unique duplication duplication");

        // then
        assertEquals(expected, result);
    }

    @Test
    public void ignoreSpecialMarksAndNumbersTest() {
        // given
        SabreIndexer sabreIndexer = new SabreIndexer();
        String expected = "a: aaa\n" +
                "d: word\n" +
                "e: money\n" +
                "g: string\n" +
                "i: string\n" +
                "m: money\n" +
                "n: money, string\n" +
                "o: money, word\n" +
                "r: string, word\n" +
                "s: string\n" +
                "t: string\n" +
                "w: word\n" +
                "y: money\n";

        // when
        String result = sabreIndexer.indexText("aaa //'string',1  $money!  (word083).");

        // then
        assertEquals(expected, result);
    }
}