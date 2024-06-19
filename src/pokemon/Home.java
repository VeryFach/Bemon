package pokemon;

import java.util.ArrayList;
import java.util.List;

public class Home {
    public List<PlayerPokemon> monsters;
    public int potion[]=new int[]{0,0};
    public static int gold=500;
    public EnemyPokemon enemy = new EnemyPokemon("enemy", 1, Element.FIRE);

    public Home() {
        this.monsters = new ArrayList<>();
    }

    public boolean trainMonster(PlayerPokemon monster) {
        if(monster.curExp<monster.nextLvl){
            return false;
        }
        monster.level++;
        monster.maxHp+=50;
        monster.curHp=monster.maxHp;
        monster.curExp-=monster.nextLvl;
        return true;
    }

    public boolean isEvolve(PlayerPokemon monster) {
        if(monster.level%5!=0){
            return false;
        }
        
        return true;
    }
    public void Evolve(PlayerPokemon monster,int x){
        
        if(monster.element==Element.FIRE){
            switch(x){
                case 1:monster.element=Element.EARTH;break;
                case 2:monster.element=Element.WIND;break;
                default:break;
            }
        }else if(monster.element==Element.WIND){
            switch(x){
                case 1:monster.element=Element.FIRE;break;
                case 2:monster.element=Element.WATER;break;
            }
        }else if(monster.element==Element.WATER){
            switch(x){
                case 1:monster.element=Element.WIND;break;
                case 2:monster.element=Element.ICE;break;
            }
        }else if(monster.element==Element.ICE){
            switch(x){
                case 1:monster.element=Element.WATER;break;
                case 2:monster.element=Element.EARTH;break;
            }
        }else if(monster.element==Element.EARTH){
            switch(x){
                case 1:monster.element=Element.ICE;break;
                case 2:monster.element=Element.FIRE;break;
            }
        }
        monster.level++;
    }

    public void heal(List<PlayerPokemon> pokemon){
        for(PlayerPokemon p : pokemon){
            p.curHp=p.maxHp;
        }
    }
    public int getGold(){
        return this.gold;
    }
    public int getHpPotion(){
        return this.potion[0];
    }
    public int getEPotion(){
        return this.potion[1];
    }
    public void addHpPotion(){
        this.potion[0]++;
        this.gold-=100;
    }
}
