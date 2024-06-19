package pokemon;

import java.util.Random;

public abstract class Pokemon {
    protected String name;
    protected int level;
    protected int maxHp;
    protected int curHp;
    protected int curExp;
    protected int nextLvl = 100;
    protected Element element;
    protected static Random random = new Random();

    public Pokemon(String name, int level, Element element) {
        this.name = name;
        this.level = level;
        this.element = element;
        this.maxHp = calculateInitialHP(level);
        this.curHp = maxHp;
    }

    protected int calculateInitialHP(int level) {
        return 100 + (level - 1) * 50;
    }

    public abstract void attack(Pokemon enemy);

    public abstract void takeDamage(int damage);

    public abstract void gainExperience(int exp);

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Element getElement() {
        return element;
    }

    public int getCurHp() {
        return curHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurExp() {
        return curExp;
    }

    public int getNextLvl() {
        return nextLvl;
    }
}