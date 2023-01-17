package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PossibleScoreCardMapTest {
    @Test
    void testConstructor() {
        PossibleScoreCardMap pscm = new PossibleScoreCardMap();
        pscm.use("FH", 100);
        assertEquals(0, pscm.getScore("FH"));
        pscm.use("Y");
        assertEquals(0, pscm.getScore("FH"));
    }

    @Test
    void testReset() {
        PossibleScoreCardMap pscm = new PossibleScoreCardMap();
        pscm.setScore("FH", 100);
        pscm.setScore("Y", 200);
        assertEquals(100, pscm.getScore("FH"));
        assertEquals(200, pscm.getScore("Y"));
        pscm.reset();
        assertEquals(0, pscm.getScore("FH"));
        assertEquals(0, pscm.getScore("Y"));
    }
}
