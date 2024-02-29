package org.example.src.units;


import java.util.List;

public class Goblin extends Unit {
    public Goblin()
    {
        super("Goblin",30,15,30);
    }
    public Goblin(List<String> list){
        super(list);
    }
}
