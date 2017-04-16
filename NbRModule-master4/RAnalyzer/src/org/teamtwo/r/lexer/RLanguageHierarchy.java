/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.lexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author Steven
 */
public class RLanguageHierarchy extends LanguageHierarchy<RTokenId>
{

    private static List<RTokenId> tokens = new ArrayList<RTokenId>();
    private static Map<Integer, RTokenId> idToToken = new HashMap<Integer, RTokenId>();

    static
    {
        RTokenType[] tokenTypes = RTokenType.values();
        for (RTokenType tokenType : tokenTypes)
        {
            tokens.add(new RTokenId(tokenType.name(), tokenType.category, tokenType.id));
        }
        for (RTokenId token : tokens)
        {
            idToToken.put(token.ordinal(), token);
        }
    }

    public static synchronized RTokenId getToken(int id)
    {
        return idToToken.get(id);
    }

    @Override
    protected synchronized Collection<RTokenId> createTokenIds()
    {
        return tokens;
    }

    @Override
    protected Lexer<RTokenId> createLexer(LexerRestartInfo<RTokenId> info)
    {
        return new NbRLexer(info);
    }

    @Override
    protected String mimeType()
    {
        return "text/x-r";
    }
}
