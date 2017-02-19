/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.lexer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.netbeans.api.lexer.PartType;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.spi.lexer.LexerInput;

/**
 *
 * @author constantin Drabo
 */
public class NbRLexer implements Lexer<RTokenId> {

    private LexerRestartInfo<RTokenId> info;
    private RLexer rlexer;

    public NbRLexer(LexerRestartInfo<RTokenId> info) {

        this.info = info;
        //System.out.println(info.input());
       // LexerInput modInput = new LexerInput();
       // AntlrCharStream charStream = new AntlrCharStream(modInput.input(), "REditor", true);        
        AntlrCharStream charStream = new AntlrCharStream(info.input(), "REditor", true);
        //new ANTLRInputStream(snapshot.getText().toString().replace("\\","\\\\")
        rlexer = new RLexer(charStream);
    }

    @Override
    public org.netbeans.api.lexer.Token<RTokenId> nextToken() {
        org.netbeans.api.lexer.Token result = null;
        RTokenId tokenId = RLanguageHierarchy.getToken(RLexer.WS);

       // RTokenId tokenId = RLanguageHierarchy.getToken(token.getType());

        try {
            org.antlr.v4.runtime.Token token = rlexer.nextToken();  
            //result = token.text().subSequence(1, token.length() - 1);
            if (token.getType() != RLexer.EOF) {
                tokenId = RLanguageHierarchy.getToken(token.getType());
                if (tokenId != null) {
                    result = info.tokenFactory().createToken(tokenId);
                } else {
                    result = info.tokenFactory().createToken(RLanguageHierarchy.getToken(RLexer.WS));
                }
            } else if (info.input().readLength() > 0) {

                tokenId = RLanguageHierarchy.getToken(RLexer.WS);
                result = info.tokenFactory().createToken(tokenId, info.input().readLength(), PartType.MIDDLE);
            }
            return result;
        }
        catch (Exception e) {
            if (info.input().readLength() > 0) {
                tokenId = RLanguageHierarchy.getToken(RLexer.WS);
                result = info.tokenFactory().createToken(tokenId, info.input().readLength(), PartType.MIDDLE);
            }
            else if (info.input().readLength() == 0) {
                 //result = token.text().subSequence(1, token.length() - 1);
                result = info.tokenFactory().createToken(RLanguageHierarchy.getToken(RLexer.WS));                 
                 //tokenId = RLanguageHierarchy.getToken(RLexer.LS);
                 //result = info.tokenFactory().createToken(tokenId, info.input().readLength()); 
                 //result = info.tokenFactory().createToken(tokenId);
                 //result = info.tokenFactory().createToken(RLanguageHierarchy.getToken(RLexer.LS));  
                //tokenId = RLanguageHierarchy.getToken(RLexer.WS);
                //result = info.tokenFactory().createToken(tokenId, info.input().readLength(), PartType.MIDDLE);                 
            }
            else {
                result = info.tokenFactory().createToken(RLanguageHierarchy.getToken(RLexer.WS));
            }
            //org.antlr.v4.runtime.Token token = rlexer.nextToken();  
            //tokenId = RLanguageHierarchy.getToken(RLexer.WS);
            //result = info.tokenFactory().createToken(tokenId, info.input().readLength(), PartType.MIDDLE); 
            return result;
        }
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

    public RLexer getRlexer() {
        return rlexer;
    }

    public void setRlexer(RLexer rlexer) {
        this.rlexer = rlexer;
    }
}

