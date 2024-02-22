package org.example.src.game;





import org.example.src.units.Hero;
import org.example.src.units.Imp;
import org.example.src.units.Unit;

import java.util.Scanner;

public  class Game {
    protected static Hero hero ;
    protected static Imp enemy;

    protected static Scanner input = new Scanner(System.in);




    protected static void setHero(String name){
        hero = new Hero(name);
    }
    protected static void setEnemy(){
        enemy = new Imp();
    }
    protected static void getAction(){
        upper:
        while(true) {
            int choice = input.nextInt();
            try {
                switch (choice) {
                    case 1 :{
                        hero.giveHit(enemy);
                        break upper;
                    }
                    case 2 :{
                        hero.getHeal();
                        break upper;
                    }
                    case 3 :{
                        hero.giveUp();
                        break upper;
                    }
                    default : {
                        throw new IllegalArgumentException("Incorrect select!!!TRY AGAIN!!!");
                    }
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
            if(enemy.isAlive()){
                enemy.giveHit(hero);
            }
    }

    protected static void fight(){
        while( hero.isAlive() && enemy.isAlive()) {
            enemy.showStats();
            hero.showStats();
            hero.showAction();
            getAction();
        }
    }
    protected static Unit whoWin(){
        if(hero.isAlive())return hero;
        else return enemy;
    }

    public static void start(){
        System.out.println("\nNazwij bohatera:");
        String name = input.nextLine();
        setHero(name);
        setEnemy();
        fight();
        System.out.println("\nWinner IS :");
        whoWin().showStats();
    }
}
