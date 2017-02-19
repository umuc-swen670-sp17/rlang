/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author Steven
 */
public class RTokenId implements TokenId
{

    private static final Language<RTokenId> language = new RLanguageHierarchy().language();
    private final String name;
    private final String primaryCategory;
    private final int id;

    public RTokenId(String name, String primaryCategory, int id)
    {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.id = id;
    }

    @Override
    public String name()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return name;
    }

    @Override
    public int ordinal()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return id;
    }

    @Override
    public String primaryCategory()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return primaryCategory;
    }
    
    public static Language<RTokenId> getLanguage()
    {
        return language;
    }
}
