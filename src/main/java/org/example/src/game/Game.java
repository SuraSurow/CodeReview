package org.example.src.game;





import org.example.src.units.Hero;
import org.example.src.units.Unit;

import java.util.Scanner;

public  class Game {
    protected static Hero hero ;
    protected static Unit enemy;

    protected static Scanner input = new Scanner(System.in);
    protected static void setHero(String name){
        hero = new Hero(name);
    }
    protected static void setEnemy(Unit obj){
        enemy = obj;
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
            if(enemy.isAlive() && hero.isAlive()){
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

    protected static void gameLoop(){
        while(hero.isAlive()){
            setEnemy(OpponentSpawner.spawn());
            fight();
            if(whoWin()==hero){
                hero.levelUp();
                System.out.println(
                        "\n\t|Excelent +1 Level|"+
                        "\n  ==============================="+
                        "\n===== !!! Start next fight !!! ===="+
                        "\n  ===============================\n"
                );
            }
            else{
                System.out.println("\n\tGAME OVER");
                return;
            }
        }
    }

    public static void start(){
        System.out.println("\nNazwij bohatera:");
        String name = input.nextLine();
        setHero(name);
        gameLoop();
        System.out.println("\nWinner IS :");
        whoWin().showStats();
    }
}
