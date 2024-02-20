package org.example.src.units;

public class Hero extends Unit{
    protected int maxHeal;
    public Hero (String name){
        super(name,100,10);
        this.maxHeal = 5;
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
