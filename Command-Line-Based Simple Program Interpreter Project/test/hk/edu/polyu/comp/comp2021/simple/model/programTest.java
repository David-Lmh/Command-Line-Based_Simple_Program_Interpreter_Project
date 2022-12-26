package hk.edu.polyu.comp.comp2021.simple.model;

import hk.edu.polyu.comp.comp2021.simple.model.execution.Simple;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class programTest
{
    String program1,program2,program3;
    @BeforeEach
    public void prepare()
    {
        program1 = "vardef vardef1 int x 100\n" + // Find the smaller one between x and y (let x be the smaller one else swap them)
                "vardef vardef2 int y -100\n" +
                "vardef vardef3 int tmp 0\n" +
                "binexpr exp1 x > y\n" +
                "assign assign1 tmp x\n" +
                "assign assign2 x y\n" +
                "assign assign3 y tmp\n" +
                "block swap assign1 assign2 assign3\n" +
                "skip skip1\n" +
                "if if1 exp1 swap skip1\n" +
                "print print1 x\n" +
                "print print2 y\n" +
                "block min vardef1 vardef2 vardef3 if1 print1 print2\n" +
                "program program1 min\n" +
                "execute program1";

        program2 = "vardef vardef1 int x 24\n" + // Find the common factors of x and y
                "vardef vardef2 int y 32\n" +
                "vardef vardef3 int i x\n" +
                "binexpr exp0 i > 0\n" +
                "binexpr exp1 x % i\n" +
                "binexpr exp2 y % i\n" +
                "binexpr exp3 exp1 == 0\n" +
                "binexpr exp4 exp2 == 0\n" +
                "binexpr exp5 exp3 && exp4\n" +
                "print print1 i\n" +
                "skip skip1\n" +
                "if if1 exp5 print1 skip1\n" +
                "binexpr exp6 i - 1\n" +
                "assign assign1 i exp6\n" +
                "block block1 if1 assign1\n" +
                "while while1 exp0 block1\n" +
                "block block2 vardef1 vardef2 vardef3 while1\n" +
                "program factors block2\n" +
                "execute factors";

    }

    @Test
    public void testProgram1()
    {
        String[] lines = program1.split("\n");
        for (String line : lines)
            Simple.run(line);
        initialize.Memory.clear();
    }

    @Test
    public void testProgram2()
    {
        String[] lines = program2.split("\n");
        for (String line : lines)
            Simple.run(line);
        initialize.Memory.clear();
    }
}
