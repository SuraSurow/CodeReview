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
        System.out.println(
                "Name: "+name
                +"\tCurrent Health:"+currentHealth
                +"\tMax Damage:"+maxDamage
        );
    }
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void getHit(int Damage)
    {
        currentHealth-=Damage;
    }
    public void giveHit(Unit object){
        System.out.println(
                "\t"
                +this.name
                +" attack "
                +object.name
        );
        int damage = random.nextInt(0,maxDamage);
        if ( damage == 0) {
            System.out.print("\tMISS!!!\n");
        }
        else {
            object.getHit(damage);
            System.out.print("\tDMG = "+damage+"\n");
        }
    }
    public Random giveRandom(){
        return random;
    }
}
