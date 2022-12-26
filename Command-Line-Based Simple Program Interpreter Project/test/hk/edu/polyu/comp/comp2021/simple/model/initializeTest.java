package hk.edu.polyu.comp.comp2021.simple.model;

import hk.edu.polyu.comp.comp2021.simple.model.execution.Simple;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class initializeTest {

    private final Simple simple = new Simple();
    private String command01;
    private String variable01;
    private String binary01;
    private String unary01;
    private String assign01;
    private String print01;
    private String skip01;
    private String block01;
    private String condition01;
    private String loop01;
    private String program01;
    private String repeatVarDefine01;

    @BeforeEach
    public void prepare() {
        command01 = ""; // test false (this command does NOT exist)

        repeatVarDefine01 = "vardef vardef1 int x 0\n" +
                "block block1 vardef1\n" +
                "block block2 vardef1 block1\n" +
                "program program1 block2\n" +
                "execute program1\n";

        variable01 = "vardef\n" + // test false (incorrect command format)
                "vardef vardef1 int x 0\n" + // test true
                "vardef vardef4 bool flag true\n" + // test true
                "vardef vardef3 double y 0.1\n" + // test false (the data type must be int or bool)
                "vardef vardef3 int z -10\n" + // test true
                "vardef vardef5 int zzzzzzzzz 0\n" + // test false (the label may contain at most 8 characters)
                "vardef vardef3 int y true\n" + // test false (the expRef is invalid int value)
                "vardef vardef1 int z 100\n" + // test false (the variable has been defined)
                "vardef vardef2 int x 100\n" + // test false (the label has been defined)
                "vardef vardef5 bool flag2 lll\n" + // test false (the bool value not "ture" or "false")
                "vardef vardef6 int int 100000000\n" + // test false (the varName can NOT be "int", "bool", "true", "false" and all command names)
                "vardef vardef int 22x 9999\n" + // test false (the label can NOT be "int", "bool", "true", "false" and all command names.)
                "vardef 2ss int s#$5 1000"; // test false (the label start with digits)

        binary01 = "binexpr\n" + // test false (incorrect command format)
                "binexpr exp1 x * 20\n" + // test false (the expRef1 has not been defined)
                "vardef vardef1 int x 0\n" + // define x
                "binexpr exp1 x - y\n" + // test false (the expRef2 has not been defined)
                "vardef vardef2 int y 1\n" + // define y
                "vardef vardef3 bool flag true\n"+ // define flag
                "binexpr exp1 y * 3\n" + // test false (input expName has been defined)
                "binexpr exp11 x | y\n" + // test false (there has no this binary operators)
                "binexpr exp0 x % 2\n"+ // The following is testing the binary operators
                "binexpr exp1 x + 20\n" +
                "binexpr exp2 x - y\n" +
                "binexpr exp3 x * 2\n"+
                "binexpr exp4 x / 2\n" +
                "binexpr exp5 x > 100\n" +
                "binexpr exp6 x >= 10\n" +
                "binexpr exp7 x < 10000\n" +
                "binexpr exp8 x <= 99999\n" +
                "binexpr exp9 x == 30\n" +
                "binexpr exp10 x != 99\n" +
                "binexpr exp11 flag && true\n" +
                "binexpr exp12 flag || true\n" +
                "binexpr exp13 flag == true\n" +
                "binexpr exp14 flag != true\n"+
                "assign assign1 x exp1\n" +
                "assign assign2 x exp2\n" +
                "assign assign3 x exp3\n" +
                "assign assign4 x exp4\n" +
                "assign assign5 flag exp5\n" +
                "assign assign6 flag exp6\n" +
                "assign assign7 flag exp7\n" +
                "assign assign8 flag exp8\n" +
                "assign assign9 flag exp9\n" +
                "assign assign10 flag exp10\n" +
                "assign assign11 flag exp11\n" +
                "assign assign12 flag exp12\n" +
                "assign assign13 flag exp13\n" +
                "assign assign14 flag exp14\n" +
                "block block1 vardef1 vardef2 vardef3 assign1 assign2 assign3 assign4 assign5 assign6 assign7 assign8 assign9 assign10 assign11 assign12 assign13 assign14\n"+
                "program program1 block1\n" +
                "execute program1\n";

        unary01 =  "unexpr\n" + // test false (incorrect command format)
                "vardef vardef1 int x 0\n" + // define x
                "vardef vardef2 int y 1\n" + // define y
                "binexpr exp1 y * 3\n" + // define exp1
                "binexpr exp2 x * y\n" + // define exp2
                "unexpr exp5 ~ exp1\n" + // test true
                "unexpr exp2 ~ exp1\n" +  // test false (the expName has been defined)
                "unexpr exp3 # exy2\n" + // test false (the expRef1 has not been defined)
                "unexpr exp4 + exp1"; // test false (the uop do not belong to unary operator)

        assign01 = "assign\n" + // test false (incorrect command format)
                "vardef vardef1 int x 0\n" + // define x
                "binexpr exp1 x * 20\n" + // define exp1
                "assign assign2 x exp1\n" + // test true
                "assign assign2 x 2\n" + // test false (the label has been defined)
                "assign assign1 z exp1\n" + // test false (the variable has not been defined)
                "assign assign3 x exp2"; // test false (the expRef has not been defined)

        print01 = "print\n" + // test false (incorrect command format)
                "vardef vardef1 int x 0\n" + // define x
                "binexpr exp1 x * 20\n" + // define exp1
                "print print1 exp1\n" + // test true
                "print print2 exp2\n" + // test false (the expRef has not been defined)
                "print print1 exp1"; // test false (the label has been defined)

        skip01 = "skip\n" + // test false (incorrect command format)
                "skip skip1\n" + // test true
                "skip skip1"; // test false (the label has been defined)

        block01 = "block\n" + // test false (incorrect command format)
                "block block1 vardef1\n" + // test false (the statementLab1 has not been defined)
                "vardef vardef1 int x 0\n" + // define x
                "block block1 vardef1\n" + // test true
                "block block1 vardef1"; // test false (the label has been defined)

        condition01 = "if\n" + // test false (incorrect command format)
                "vardef vardef1 int x 0\n" + // define x
                "binexpr exp1 x * 20\n" + // define exp1
                "if if1 true vardef1 exp1\n" + // test true
                "if if2 exp5 vardef1 exp1\n" + // test false (the expRef has not been defined)
                "if if3 exp1 vardef2 exp1\n" + // test false (the statementLab1 has not been defined)
                "if if4 exp1 exp1 vardef2\n" + // test false (the statementLab2 has not been defined)
                "if if1 true vardef1 exp1"; // test false (the label has been define)

        loop01 = "while\n" + // test false (incorrect command format)
                "while while1 true block1\n" + // test false (the statementLab1 has not been defined)
                "vardef vardef1 int x 0\n" + // define x
                "while while2 false vardef1\n" + // test false
                "block block1 vardef1\n" + // define block1
                "while while1 true block1\n" + // test true
                "while while1 true vardef1\n" + // test false (the label has been defined)
                "while while2 block2 vardef1\n" + // test false (the expRef has not been defined)
                "while while3 false vardef1" +
                "program program1 while3\n" +
                "execute program";


        program01 = "program\n" + // test false (incorrect command format)
                "program program1 block1\n" + // test false (the statementLab has not been defined)
                "vardef vardef1 int x 0\n" + // define x
                "block block1 vardef1\n" + // define block1
                "program program1 block1\n" + // test true
                "program program1 vardef1"; // test false (the programName has been defined)

    }

    @Test
    public void testBasicCommandError01() {
        simple.run(command01);
    }

    @Test
    public void testRepeatVarDefine01() {
        String[] program01lines = repeatVarDefine01.split("\n");
        for (String program : program01lines) {
            simple.run(program);
        }
        initialize.Memory.clear();
    }
    @Test
    public void testVariableDefineError01() {
        String[] variable01lines = variable01.split("\n");
        for (String variable : variable01lines) {
            simple.run(variable);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testBinaryExpressionError01() {
        String[] binary01lines = binary01.split("\n");
        for (String binary : binary01lines) {
            simple.run(binary);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testUnaryExpressionError01() {
        String[] unary01lines = unary01.split("\n");
        for (String unary : unary01lines) {
            simple.run(unary);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testAssignmentDefineError01() {
        String[] assign01lines = assign01.split("\n");
        for (String assign : assign01lines) {
            simple.run(assign);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testPrintDefineError01() {
        String[] print01lines = print01.split("\n");
        for (String print : print01lines) {
            simple.run(print);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSkipDefineError() {
        String[] skip01lines = skip01.split("\n");
        for (String skip: skip01lines) {
            simple.run(skip);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testBlockDefineError01() {
        String[] block01lines = block01.split("\n");
        for (String block : block01lines) {
            simple.run(block);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testConditionDefineError01() {
        String[] condition01lines = condition01.split("\n");
        for (String condition : condition01lines) {
            simple.run(condition);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testLoopDefineError01() {
        String[] loop01lines = loop01.split("\n");
        for (String loop : loop01lines) {
            simple.run(loop);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramDefineError01() {
        String[] program01lines = program01.split("\n");
        for (String program : program01lines) {
            simple.run(program);
        }
        initialize.Memory.clear();
    }
}
