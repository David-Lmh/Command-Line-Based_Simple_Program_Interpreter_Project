package hk.edu.polyu.comp.comp2021.simple.model;

import hk.edu.polyu.comp.comp2021.simple.model.execution.Simple;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTest {
    private static final Simple simple = new Simple();
    private String execute01, execute02, execute03, execute04, execute05, execute06, execute07, execute08;
    private String list01, list02;
    private String store01, store02, store03;
    private String load01, load02;

    @BeforeEach
    public void prepare() {
        execute01 = "execute\n" + // test false (incorrect command format)
                "vardef vardef1 int x 100\n" + // define vardef1
                "execute program1\n" + // test false (there is no this program)
                "program program1 vardef1\n" + // define program1
                "execute vardef1"; // test false (invalid programName)

        list01 = "list\n" + // test false (incorrect command format)
                "vardef vardef1 int x 100\n" + // define vardef1
                "list program1\n" + // test false (there is no this program)
                "program program1 vardef1\n" + // define program1
                "list vardef1"; // test false (invalid programName)

        store01 = "store\n" + // test false (incorrect command format)
                "vardef vardef1 int x 100\n" + // define vardef1
                "store program1 d:\\a.simple\n" + // test false (undefined programName)
                "program program1 vardef1\n" + // define program1
                "store program1 d:\\a.aaaaaa"; // test false (invalid path)

        load01 = "load\n" + // test false (incorrect command format)
                "load d:\\a.simple program1\n" + // test false (the simple file does NOT exist)
                "load d:\\a.aaaa program1\n" + // test false (invalid path)
                "vardef vardef1 int x 100\n" + // define vardef1
                "program program1 vardef1\n" + // define program1
                "load d:\\a.simple program1"; // test false (the programName has been defined)

        execute02 = "vardef =car1 int 3bo 0\n" + // test false (can only contain English letters and digits)
                "vardef vardef1 int x 0\n" +
                "binexpr exp1 x % 2\n" +
                "binexpr exp2 exp1 == 0\n" +
                "binexpr exp9 exp1 >= 0\n" +
                "binexpr exp10 exp1 <= 0\n" +
                "binexpr exp11 exp1 < 0\n" +
                "binexpr exp12 exp1 > 0\n" +
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
                "execute program1";

        execute03 = "vardef vardef1 int x 100\n" +
                "binexpr exp1 x * 3\n" +
                "binexpr exp2 x * 2\n" +
                "assign assign1 x exp1\n" +
                "assign assign2 x exp2\n" +
                "print print1 x\n" +
                "block block1 vardef1 assign1 assign2 print1\n" +
                "program program1 block1\n" +
                "debug program1";

        execute04 = "vardef vardef1 int x 100\n" +
                "vardef vardef2 bool y true\n" +
                "binexpr exp1 x * y\n" +
                "program program1 exp1\n" +
                "execute program1";

        execute05 = "vardef vardef1 int x 100\n" +
                "binexpr exp1 x * 200000\n" +
                "assign assign1 x exp1\n" +
                "block block1 assign1\n" +
                "binexpr exp2 x < 100000\n" +
                "binexpr exp3 x >= 2000\n" +
                "if if1 exp3 block1 assign1\n" +
                "program program1 if1\n" +
                "execute program1\n" +
                "debug program1\n";


        execute06 = "vardef vardef1 int x 2222\n" +
                "vardef vardef2 int y 1000000000000\n" +
                "binexpr exp1 y / 3\n" +
                "binexpr exp2 x - y\n" +
                "unexpr exp5 ~ exp1\n" +
                "unexpr exp3 ~ exp1\n" +
                "unexpr exp6 # exp2\n" +
                "unexpr exp7 ! true\n" +
                "unexpr exp9 ! false\n" +
                "unexpr exp8 # true\n" +
                "unexpr exp4 ! exp1\n" +
                "block block1 vardef1 vardef2 exp5 exp3 exp6 exp4 exp8 exp7\n" +
                "program program1 block1\n" +
                "execute program1";

        execute07 = "vardef vardef1 int x 100\n" +
                "assign assign1 x 1999999\n" +
                "block block1 vardef1 assign1\n" +
                "program program1 block1\n" +
                "execute program1";

        list02 = "vardef vardef1 int x 0\n" +
                "binexpr exp1 x % 2\n" +
                "unexpr exp5 ~ exp1\n" +
                "binexpr exp2 exp5 == 0\n" +
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
                "execute program1\n" +
                "list program1";

        store02 = "vardef vardef1 int x 100\n" +
                "binexpr exp1 x * 3\n" +
                "binexpr exp2 x * 2\n" +
                "assign assign1 x exp1\n" +
                "assign assign2 x exp2\n" +
                "print print1 x\n" +
                "block block1 vardef1 assign1 assign2 print1\n" +
                "program program1 block1\n" +
                "store program1 d:\\pro1.simple";

        store03 = "vardef vardef1 int x 100\n" +
                "binexpr exp1 x * 3\n" +
                "assign assign1 x exp1\n" +
                "block block1 vardef1 assign1\n" +
                "program program1 block1\n" +
                "store program1 d:\\pro2.simple";

        load02 = "load d:\\pro3.simple program3\n" +
                "load d:\\pro2.simple program2\n" +
                "load d:\\pro1.simple program1\n" +
                "execute program1";
    }

    @BeforeEach
    public void deleteTestFile() {
        try
        {
            File writeName = new File("d:\\pro3.simple");
            FileWriter writer = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(writer);
            String pro3 = "vardef vardef1 int x 100\n" +
                    "binexpr exp1 x * 3\n" +
                    "assign assign1 x exp1\n" +
                    "block block1 vardef1 assign1";
            for (String value : pro3.split("\n"))
            {
                out.write(value+"\n");
                out.flush();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testSimpleExecute01(){
        String[] execute01lines = execute01.split("\n");
        for (String execute : execute01lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramList01() {
        String[] list01lines = list01.split("\n");
        for (String list : list01lines) {
            simple.run(list);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramStore01() {
        String[] store01lines = store01.split("\n");
        for (String store : store01lines) {
            simple.run(store);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramLoad01() {
        String[] load01lines = load01.split("\n");
        for (String load : load01lines) {
            simple.run(load);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute02(){
        String[] execute02lines = execute02.split("\n");
        for (String execute : execute02lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute03(){
        String[] execute03lines = execute03.split("\n");
        for (String execute : execute03lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute04(){
        String[] execute04lines = execute04.split("\n");
        for (String execute : execute04lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute05(){
        String[] execute05lines = execute05.split("\n");
        for (String execute : execute05lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute06(){
        String[] execute06lines = execute06.split("\n");
        for (String execute : execute06lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testSimpleExecute07(){
        String[] execute07lines = execute07.split("\n");
        for (String execute : execute07lines) {
            simple.run(execute);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramList02() {
        String[] list02lines = list02.split("\n");
        for (String list : list02lines) {
            simple.run(list);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramStore02() {
        String[] store02lines = store02.split("\n");
        for (String store : store02lines) {
            simple.run(store);
        }
        initialize.Memory.clear();
    }

    @Test
    public void testProgramStore03() throws IOException {
        String[] store03lines = store03.split("\n");
        for (String store : store03lines) {
            simple.run(store);
        }
        initialize.Memory.clear();
    }


    @Test
    public void testProgramLoad02() {
        String[] load02lines = load02.split("\n");
        for (String load : load02lines) {
            simple.run(load);
        }
        initialize.Memory.clear();
    }
}