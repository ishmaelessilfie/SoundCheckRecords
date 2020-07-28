/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Model;

/**
 *
 * @author Ish
 */
public enum CheeseType {
    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake"),
    UNKNOWN ("Unknown"),
    AWURADE ("Awurade"),
    NYAME ("Nyame"),
    MEDAWO ("Medawo"),
    ASE ("Ase");
    
    
    private final String name;

    private CheeseType(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    

    
    
    
}
