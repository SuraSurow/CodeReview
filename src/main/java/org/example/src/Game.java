package org.example.src;





import org.example.src.units.Hero;
import org.example.src.units.Imp;
import org.example.src.units.Unit;

import java.util.Scanner;

public class Game {
    protected Hero hero;
    protected Imp enemy;

    protected Scanner input;

    public Game(){
        input = new Scanner(System.in);
    }

    protected void setHero(String name){
        hero = new Hero(name);
    }
    protected void setEnemy(){
        enemy = new Imp();
    }
    protected int getChoice ( Scanner input){
        int choice;
        do{
            choice = input.nextInt();
        }while( choice < 0 || choice > 3 );
        return choice;
    }

    protected void fight(){
        while( hero.isAlive() && enemy.isAlive()) {
            enemy.showStats();
            hero.showStats();
            hero.showAction();
            int user = getChoice(input);
            switch (user) {
                case 1: {
                    hero.giveHit(enemy);
                    break;
                }
                case 2: {
                    hero.getHeal();
                    break;
                }
                case 3: {
                    hero.giveUp();
                    break;
                }
            }
            enemy.giveHit(hero);
        }
    }
    protected Unit whoWin(){
        if(hero.isAlive())return hero;
        else return enemy;
    }

    public void start(){
        System.out.println("\nNazwij bohatera:");
        String name = input.nextLine();
        setHero(name);
        setEnemy();
        fight();
        System.out.println("\nWinner IS :");
        whoWin().showStats();
    }
}

