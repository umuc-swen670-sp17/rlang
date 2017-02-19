/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.parser;

import javax.swing.event.ChangeListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.teamtwo.r.lexer.RLexer;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;

/**
 *
 * @author constantin Drabo
 * Modified by Steven
 *
 */
public class NbParser extends Parser {

    private Snapshot snapshot;
    private RParser rparser;

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent event) throws ParseException {
        System.out.println("I am in the parser");
        this.snapshot = snapshot;
        //System.out.println(snapshot.getText().toString());
        //org.antlr.v4.runtime.ANTLRInputStream input = new ANTLRInputStream("G");
        org.antlr.v4.runtime.ANTLRInputStream input = new ANTLRInputStream(snapshot.getText().toString().replace("\\","\\\\"));
       // org.antlr.runtime.ANTLRStringStream input  =  new ANTLRStringStream(snapshot.getText().toString());
        //  System.out.println("Input is " + input.size() + " and the letter is " + input.substring(0, input.size() - 1));
        //input = input.replace(' ', '*');
        //System.out.println(input);
        RLexer lexer = new RLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        RFilter filter = new RFilter(tokens);
        filter.stream(); // call start rule: stream
        tokens.reset();
        
        rparser = new RParser(tokens);
        try {
            rparser.prog();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        return new REditorParserResult(rparser, snapshot);
    }

    @Override
    public void addChangeListener(ChangeListener cl) {
    }

    @Override
    public void removeChangeListener(ChangeListener cl) {
    }

    public static class REditorParserResult extends Result {

        private RParser rparser;
        private boolean valid = true;

        public REditorParserResult(RParser rparser, Snapshot snapshot) {
            super(snapshot);
            this.rparser = rparser;
        }

        public RParser getRparser() {
            return rparser;
        }

        @Override
        protected void invalidate() {
            valid = false;
        }

    }

}