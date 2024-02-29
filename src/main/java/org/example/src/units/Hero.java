package org.example.src.units;


import java.util.List;


public class Hero extends Unit{
    protected static int maxHeal;
    protected static int currentLevel;
    public Hero (String name){
        super(name,100,10,100);
        maxHeal = 5;
        currentLevel = 1;
    }
    public Hero(List<String> list) {
        super(
                list.get(0),
                Integer.parseInt(list.get(1)),
                Integer.parseInt(list.get(2)),
                Integer.parseInt(list.get(3))
        );
        if (list.size() != 6) {
            throw new IllegalArgumentException("List must have 6 argument");
        }
        maxHeal = Integer.parseInt(list.get(4));
        currentLevel = Integer.parseInt(list.get(5));
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
    @Override
    public String toCSV(){
        String[] attributes = {
                name,
                String.valueOf(currentHealth),
                String.valueOf(maxDamage),
                String.valueOf(maxHealth),
                String.valueOf(maxHeal),
                String.valueOf(currentLevel)
        };
        return String.join(",",attributes);
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
                "1.Attack\t2.Heal\t3.Give Up\t4.Save Game"
        );
    }

}
