package org.example.src.units;


import java.util.List;
import java.util.Random;



public abstract class Unit {
    protected Random random;
    protected String name;
    protected int maxHealth , maxDamage ,currentHealth;

    public Unit(String name, int currentHealth , int maxDamage ,int maxHealth){
        this.name=name;
        this.currentHealth=currentHealth;
        this.maxDamage=maxDamage;
        this.maxHealth=maxHealth;
        this.random = new Random();
    }
    public Unit(List<String> list) {
        if (list.size() != 4) {
            throw new IllegalArgumentException("List must have 4 argument");
        }
        this.name = list.get(0);
        this.currentHealth = Integer.parseInt(list.get(1));
        this.maxDamage = Integer.parseInt(list.get(2));
        this.maxHealth = Integer.parseInt(list.get(3));
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

    public String toCSV(){
        String[] attributes = {
                name,
                String.valueOf(currentHealth),
                String.valueOf(maxDamage),
                String.valueOf(maxHealth),
        };
        return String.join(",",attributes);
    }
}
