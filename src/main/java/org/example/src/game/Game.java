package org.example.src.game;
import org.example.src.units.*;


import java.io.IOException;
import java.util.Scanner;

public  class Game {
    protected static Hero hero;
    protected static Unit enemy;

    protected static SaveSystem saveSystem;

    static {
        try {
            saveSystem = new SaveSystem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Scanner input = new Scanner(System.in);

    protected static void setHero(String name) {
        hero = new Hero(name);
    }

    protected static void setEnemy(Unit obj) {
        enemy = obj;
    }

    protected static void getEnemyAction(){
        if (enemy.isAlive() && hero.isAlive() && !SaveSystem.getIsSave()) {
            enemy.giveHit(hero);
        }
    }

    protected static void getAction() {
        int choice = input.nextInt();
        SaveSystem.setIsSave(false);
        try {
            switch (choice) {
                case 1 -> hero.giveHit(enemy);
                case 2 -> hero.getHeal();
                case 3 -> hero.giveUp();
                case 4 -> {
                    saveSystem.save(enemy, hero);
                    SaveSystem.setIsSave(true);
                }
                default -> throw new IllegalArgumentException("Incorrect select!!!TRY AGAIN!!!");
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void fight() {
        while (hero.isAlive() && enemy.isAlive()) {
            enemy.showStats();
            hero.showStats();
            hero.showAction();
            getAction();
            getEnemyAction();
        }
    }

    protected static Unit whoWin() {
        if (hero.isAlive()) return hero;
        else return enemy;
    }

    protected static void gameLoop() {
        while (hero.isAlive()) {
            if(enemy == null || !enemy.isAlive()) {
                setEnemy(OpponentSpawner.spawn());
            }
            fight();
            if (whoWin() == hero) {
                hero.levelUp();
                System.out.println(
                        """

                                \t|Excelent +1 Level|
                                  ===============================
                                ===== !!! Start next fight !!! ====
                                  ===============================
                                """
                );
            } else {
                System.out.println("\n\tGAME OVER");
                return;
            }
        }
    }

    public static void startNewGame() {
        System.out.println("\nName a Hero:");
        String name = input.nextLine();
        setHero(name);
        gameLoop();
        System.out.println("\nWinner IS :");
        whoWin().showStats();
    }

    public static void startGame() {
        label:
        while (true) {
            System.out.println("\nDo you wanna load previous game ? [Y]es/[N]o/[E]xit:");
            try {
                String choice = input.nextLine();
                if (choice != null) {
                    choice = choice.toUpperCase();
                    switch (choice) {
                        case "Y":
                            loadGame();
                            return;
                        case "N":
                            startNewGame();
                            return;
                        case "E":
                            break label;
                        default:
                            throw new IllegalArgumentException("\n\tIncorrect select!!!TRY AGAIN!!!");
                    }
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(e.getMessage() + ("\n\tTry new game or Exit!!!"));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("EXIT ,GOODBYE!!!");
    }


    public static void loadGame() {
        hero = saveSystem.loadHero();
        enemy = saveSystem.loadUnit();
        gameLoop();
        System.out.println("\nWinner IS :");
        whoWin().showStats();
    }
}


