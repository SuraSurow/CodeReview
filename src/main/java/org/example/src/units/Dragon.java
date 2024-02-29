package org.example.src.units;


import java.util.List;

public class Dragon extends Unit {
    public Dragon()
    {
        super("Dragon",50,20,50);
    }
    public Dragon(List<String> list){
        super(list);
    }
}
