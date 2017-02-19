/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.lexer;

/**
 *
 * @author Steven
 */
public enum RTokenType
{
    COMPLEX(58, "type"),
    FLOAT(57, "type"),
    ID(60, "identifier"),
    HEX(55, "type"),
    USER_OP(61, "operator"),
    NL(62, "keyword"),
    INT(56, "number"),
    WS(63, "whitespace"),
    STRING(59, "string"),
    ESC(64,"string");
    //T__18(19, "operator");

    public int id;
    public String category;
    public String text;

    private RTokenType(int id, String category)
    {
        this.id = id;
        this.category = category;
    }

    public static RTokenType valueOf(int id)
    {
        RTokenType[] values = values();
        for (RTokenType value : values)
        {
            if (value.id == id)
            {
                return value;
            }
        }
        throw new IllegalArgumentException("The id " + id + " is not recognized");
    }
}
