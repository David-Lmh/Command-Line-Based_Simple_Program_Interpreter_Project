package hk.edu.polyu.comp.comp2021.simple.model;

import hk.edu.polyu.comp.comp2021.simple.model.execution.Simple;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class bonusTest {
    private static final Simple simple = new Simple();
    private String debug01, instrument01, instrument02;

    @BeforeEach
    public void prepare()
    {
        debug01 = "debug\n" +
                "vardef vardef1 int x 0\n" +
                "binexpr exp1 x % 2\n" +
                "binexpr exp2 exp1 == 0\n" +
                "print print1 x\n" +
                "skip skip1\n" +
                "if if1 exp2 print1 skip1\n" +
                "binexpr exp3 x + 1\n" +
                "assign assign1 x exp3\n" +
                "block block1 if1 assign1\n" +
                "binexpr exp4 x <= 10\n" +
                "while while1 exp4 block1\n" +
                "block block2 vardef1 while1\n" +
                "program program1 block2\n" +
                "togglebreakpoint program1 if1\n" +
                "debug program1\n" +
                "debug program2\n" +
                "debug block1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program1 x\n" +
                "debug program1\n" +
                "inspect program2 x\n" +
                "inspect program1 y\n" +
                "inspect program2\n" +
                "togglebreakpoint program1 if1\n" +
                "togglebreakpoint program2 if\n" +
                "togglebreakpoint program1\n" +
                "togglebreakpoint block1 print4\n" +
                "togglebreakpoint program1 if4";

        instrument01 = "debug\n" +
                "vardef vardef1 int x 0\n" +
                "binexpr exp1 x % 2\n" +
                "binexpr exp2 exp1 == 0\n" +
                "print print1 x\n" +
                "skip skip1\n" +
                "if if1 exp2 print1 skip1\n" +
                "binexpr exp3 x + 1\n" +
                "assign assign1 x exp3\n" +
                "block block1 if1 assign1\n" +
                "binexpr exp4 x <= 20\n" +
                "while while1 exp4 block1\n" +
                "block block2 vardef1 while1\n" +
                "program program1 block2\n" +
                "instrument program1 block1 after 5\n" +
                "instrument program1 block1 after\n" +
                "instrument program3 block1 after 5\n" +
                "instrument program1 block4 after 5\n" +
                "instrument if1 block1 after 5\n" +
                "instrument program1 block1 at 5\n" +
                "instrument program1 block1 before 5\n" +
                "instrument program1 block1 before x" +
                "execute program1\n";
        instrument02 = "vardef vardef1 int x 0\n" +
                "binexpr exp1 x % 2\n" +
                "binexpr exp2 exp1 == 0\n" +
                "print print1 x\n" +
                "skip skip1\n" +
                "if if1 exp2 print1 skip1\n" +
                "binexpr exp3 x + 1\n" +
                "assign assign1 x exp3\n" +
                "block block1 if1 assign1\n" +
                "binexpr exp4 x <= 10\n" +
                "while while1 exp4 block1\n" +
                "block block2 vardef1 while1\n" +
                "program program1 block2\n" +
                "instrument program1 exp4 before 1\n" +
                "execute program1";
    }

    @Test
    public void debugTest01() {
        String[] debug01lines = debug01.split("\n");
        for (String debug : debug01lines) {
            simple.run(debug);
        }
        initialize.Memory.clear();
    }

    @Test
    public void instrument01() {
        String[] instrument01lines = instrument01.split("\n");
        for (String instrument : instrument01lines) {
            simple.run(instrument);
        }
        initialize.Memory.clear();
    }

    @Test
    public void instrument02()
    {
        String[] instrument01lines = instrument02.split("\n");
        for (String instrument : instrument01lines) {
            simple.run(instrument);
        }
        initialize.Memory.clear();
    }
}
