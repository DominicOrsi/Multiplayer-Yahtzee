package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ScoreSlotMapTest {
    @Test
    void testConstructor() {
        System.out.println("Testing to make sure that each case is accounted for on creation.");
        ScoresMap scores = new ScoresMap();
        List<String> cases =
                new ArrayList<>(
                        Arrays.asList(
                                "1", "2", "3", "4", "5", "6", "3K", "4K", "SS", "LS", "FH", "Y",
                                "C"));

        for (String string : scores.getCases()) {
            assertTrue(cases.contains(string));
        }
    }

    @Test
    void testUse() {
        System.out.println("Ensuring that each slot can be used and gets used properly.");
        ScoresMap scores = new ScoresMap();
        for (String string : scores.getCases()) {
            scores.use(string, 10);
            assertTrue(scores.isUsed(string));
            assertEquals(10, scores.getScore(string));
        }
    }
}
