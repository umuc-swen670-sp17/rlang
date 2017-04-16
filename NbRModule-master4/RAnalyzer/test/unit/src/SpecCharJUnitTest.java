/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
//import org.antlr.v4.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.api.lexer.PartType;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.teamtwo.r.lexer.AntlrCharStream;
import org.teamtwo.r.lexer.NbRLexer;
import org.teamtwo.r.lexer.RLanguageHierarchy;
import org.teamtwo.r.lexer.RLexer;
import org.teamtwo.r.lexer.RTokenId;
import org.teamtwo.r.lexer.RTokenType;
import org.teamtwo.r.parser.NbParser;
import org.teamtwo.r.parser.RParser;

/**
 *
 * @author matth
 */
public class SpecCharJUnitTest extends RLanguageHierarchy {
    

    
    public SpecCharJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void SpecCharCheck() throws IOException {
        System.out.println("* VectorsJUnit4Test: ScalarMultiplicationCheck()");
        //assertEquals(  0, NbRLexer.scalarMultiplication(new int[] { 0, 0}, new int[] { 0, 0}));
        String input2 = "\\";
        CharStream cs2 = new ANTLRInputStream(input2);        
        RLexer lexer2 = new RLexer( cs2);
        CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
        RParser parser2 = new RParser(tokens2);
        System.out.println(parser2.prog().toString());
        assertEquals("[]",parser2.prog().toString());

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
