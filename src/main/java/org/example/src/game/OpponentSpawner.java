package org.example.src.game;

import org.example.src.units.Dragon;
import org.example.src.units.Goblin;
import org.example.src.units.Imp;
import org.example.src.units.Unit;

import java.util.Random;

public class OpponentSpawner {

    protected static Random random = new Random(System.currentTimeMillis());
    protected static Unit opponent;
    public static Unit spawn(){
        int probability = random.nextInt(0,100);
        if ( probability < 60)
            return opponent = new Imp();
        else if ( probability < 90)
            return opponent = new Goblin();
        else
            return opponent = new Dragon();
    }

}
