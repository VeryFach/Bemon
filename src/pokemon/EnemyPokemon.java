package pokemon;

public class EnemyPokemon extends Pokemon {
    private int specialAttackDamage = 15;
    private boolean wonLastCycle = false;
    public EnemyPokemon(String name, int level, Element element) {
        super(name, level, element);
    }

    @Override
    public void attack(Pokemon enemy) {
        int damage = this.level*10;
        enemy.takeDamage(damage);
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
    }

    @Override
    public void gainExperience(int exp) {
        this.curExp += exp;
        if (this.curExp >= this.nextLvl) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.curExp = 0;
        this.nextLvl = 100; 
        this.maxHp = calculateInitialHP(this.level);
        this.curHp = this.maxHp; 
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