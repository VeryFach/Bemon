package pokemon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameProgress {
    public static void saveProgress(List<PlayerPokemon> pokemon,Home home ,String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(home.getGold()+","+home.getHpPotion()+","+home.getEPotion());
            writer.newLine();
            for (PlayerPokemon Pokemon : pokemon) {
                writer.write(Pokemon.getName() + "," + Pokemon.getLevel() + "," + Pokemon.getEP() + "," + Pokemon.getElement()+","+Pokemon.getSicon()+","+Pokemon.getSbicon());
                writer.newLine();
            }
            
        } catch (IOException e) {
            System.out.println("Failed to save game progress: " + e.getMessage());
        }
    }

    public static List<PlayerPokemon> loadProgress(Home home,String filename) {
        List<PlayerPokemon> monsters = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String item;
            item=reader.readLine();
            String[] isi = item.split(",");
            home.gold=Integer.parseInt(isi[0]);
            home.potion[0]=Integer.parseInt(isi[1]);
            home.potion[1]=Integer.parseInt(isi[2]);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                PlayerPokemon monster = new PlayerPokemon(data[0], Integer.parseInt(data[1]), Element.valueOf(data[3]),data[4],data[5]);
                monster.setEP(Integer.parseInt(data[2]));
                monsters.add(monster);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to load game progress: " + e.getMessage());
        }
        return monsters;
    }
    
}