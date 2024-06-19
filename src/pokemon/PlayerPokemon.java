package pokemon;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class PlayerPokemon extends Pokemon implements BattleAction {
    private ImageIcon icon;
    private ImageIcon bIcon;
    private String sicon;
    private String sbicon;
    private int specialAttackDamage = 30;
    private Home home = new Home();
    private boolean wonLastCycle = false;
    private Map<String, Integer> inventory = new HashMap<>();

    public PlayerPokemon(String name, int level, Element element, String icon, String bicon) {
        super(name, level, element);
        this.sicon = icon;
        this.sbicon = bicon;
        setBicon(bicon);
        setIcon(icon);
    }

    @Override
    public void attack(Pokemon enemy) {
        int damage = this.level*10;
        enemy.takeDamage(damage);
    }

    public void elementalAttack(Pokemon enemy) {
        int damage;
        if (this.element.isStrongerThan(enemy.getElement())) {
            damage = (this.level*10)*2;
        } else if(this.element==enemy.element){
            damage = this.level*10;
        }else {
            damage = (this.level*10)/2;
        }
        enemy.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        this.curHp -= damage;
        if (this.curHp < 0) {
            this.curHp = 0;
        }
        wonLastCycle = false;
    }

    public void setIcon(String s) {
        this.icon = new ImageIcon(getClass().getResource(s));
    }

    public void setBicon(String s) {
        this.bIcon = new ImageIcon(getClass().getResource(s));
    }

    public int getEP() {
        return this.curExp;
    }

    public int getNext() {
        return this.nextLvl;
    }

    public void setIcon(ImageIcon i) {
        this.icon = i;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public void setBIcon(ImageIcon i) {
        this.bIcon = i;
    }

    public ImageIcon getBIcon() {
        return this.bIcon;
    }

    public void setEP(int exp) {
        this.curExp = exp;
    }

    public String getSicon() {
        return this.sicon;
    }

    public String getSbicon() {
        return this.sbicon;
    }

    public void gainCoins(int coins) {
        Home.gold += coins;
    }

    public boolean flee() {
        return random.nextInt(100) < 30;
    }

    public boolean useHealingPotion(int potion) {
        if (potion > 0) {
            this.curHp += 50;
            if (this.curHp > this.maxHp) {
                this.curHp = this.maxHp;
            }
            
            return true;
        } else {
            return false;
        }
    }

    public boolean useElementalPotion(int potion, Pokemon enemy) {
        
        int potions = potion;
        if (potions > 0) {
            int damage = 30;
            if (this.element.isStrongerThan(enemy.element)) {
                damage *= 2;
            }
            enemy.takeDamage(damage);
            
            return true;
        } else {
            return false;
        }
    }

    public void specialAttack(Pokemon enemy) {
        int baseDamage = wonLastCycle ? specialAttackDamage * 2 : specialAttackDamage;
        int damage = calculateDamage(baseDamage, enemy.getElement());
        enemy.takeDamage(damage);
        this.curHp -= damage / 2;
        if (this.curHp < 0) {
            this.curHp = 1;
        }
        wonLastCycle = true;
    }

    @Override
    public void gainExperience(int exp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int calculateDamage(int baseDamage, Element enemyElement) {
        int damage = baseDamage + (level * 2);
        if (this.element.isStrongerThan(enemyElement)) {
            damage *= 2;
        }
        int minDamage = damage - 3;
        int maxDamage = damage + 3;
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
}