package com.sudoku.model;

public class Space {

    private Integer actual;         //valor atual naquele espaço
    private final int expected;     //numero esperado
    private final boolean fixed;    //numero fixo

    public Space(final int expected, final boolean fixed){
        this.expected = expected;
        this.fixed = fixed;

        if(fixed){
            this.actual = expected;
        }
    }
    
    public Integer getActual(){
        return this.actual;
    }

    public void setActual(final Integer actual){
        if(fixed) return;
        this.actual = actual;
    }

    public void clearSpace(){
        setActual(null);
    }

    public boolean getFixed(){
        return this.fixed;
    }

    public int getExpected(){
        return this.expected;
    }
}
