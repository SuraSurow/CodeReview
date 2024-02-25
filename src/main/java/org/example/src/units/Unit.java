package org.example.src.units;

import java.util.Random;



public abstract class Unit {
    protected Random random;
    protected final String name;
    protected int maxHealth , maxDamage ,currentHealth;

    public Unit(String name, int maxHealth, int maxDamage ){
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.name = name;
        this.maxDamage = maxDamage;
        this.random = new Random();
    }
    public void showStats(){
        System.out.printf(
                "Name: %1$s\tCurrent Health: %2$s\tMax Damage: %3$s%n",
                name , currentHealth , maxDamage
        );
    }
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void getHit(int Damage)
    {
        currentHealth-=Damage;
    }
    public void giveHit(Unit object) {
        System.out.printf(
                "\t %1$s attack %2$s%n", this.name,object.name
        );
        int damage = random.nextInt(0, maxDamage);
        if (damage == 0) {
            System.out.print("\tMISS!!!\n");
        } else {
            object.getHit(damage);
            System.out.print("\tDMG = " + damage + "\n");
        }
    }
}
