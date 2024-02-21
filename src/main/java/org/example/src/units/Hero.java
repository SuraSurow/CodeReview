package org.example.src.units;

public class Hero extends Unit{
    protected static int maxHeal = 5;
    protected static int currentLevel = 1;
    public Hero (String name){
        super(name,100,10);
    }

    @Override
    public void showStats(){
        System.out.println(
                        "Name:"+name
                        +"\tCurrent Health:"+currentHealth
                        +"\tMax Damage:"+maxDamage
                        +"\tMax Health:"+maxHealth
                        +"\tMax Heal:"+maxHeal
                        +"\tLevel:"+currentLevel
        );

    }

    public void levelUp(){
        currentLevel++;
        maxHeal+=2;
        maxHealth+=5;
        maxDamage+=2;
    }
    public int healPoint(){
        return random.nextInt(1, maxHeal);
    }
    public void getHeal() {
        int newHealth = currentHealth + healPoint();
        currentHealth = Math.min(newHealth, maxHealth);
    }
    public void giveUp()
    {
        currentHealth = 0;
    }
    public void showAction()
    {
        System.out.println(
                "1.Attack\t2.Heal\t3.Give Up"
        );
    }

}
