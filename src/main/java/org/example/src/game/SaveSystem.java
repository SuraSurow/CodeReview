package org.example.src.game;

import org.example.src.units.*;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SaveSystem {
    protected static  File heroFile;
    protected static Scanner heroFileScanner;

    protected static  File unitFile;
    protected static Scanner unitFileScanner;

    public SaveSystem() throws IOException {
        heroFile = new File("hero.csv");
        unitFile = new File("unit.csv");
        if (!heroFile.exists()) {
            heroFile.createNewFile();
        }
        if (!unitFile.exists()) {
            unitFile.createNewFile();
        }

        heroFileScanner = new Scanner(heroFile);
        unitFileScanner = new Scanner(unitFile);
    }

    public void save(Unit unit ,Hero hero) throws IOException {
        save(unit,unitFile);
        save(hero,heroFile);
    }
    private void save(Unit object , File unitFile) throws IOException {
        try {
            FileWriter writer = new FileWriter(unitFile);
            writer.write(object.toCSV());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error with save");
            System.out.println(unitFile.getCanonicalPath());
        }
    }
    public Unit loadUnit(){
        List<String>unitArrayList = loadFile(unitFileScanner);
        return switch (unitArrayList.getFirst()) {
            case "Dragon" -> new Dragon(unitArrayList);
            case "Goblin" -> new Goblin(unitArrayList);
            case "Imp" -> new Imp(unitArrayList);
            default -> null;
        };
    }
    public Hero loadHero(){
        List<String>heroArrayList = loadFile(heroFileScanner);
        return new Hero(heroArrayList);
    }
    private List<String> loadFile(Scanner scanner) {
        List<String> data = new ArrayList<>();
        scanner.useDelimiter(",");
        try {
            if (!scanner.hasNext())throw new IndexOutOfBoundsException("\tFile is empty!!!\n\tDo not have game to load!!!");
            while (scanner.hasNext()) {
                String token = scanner.next();
                data.add(token);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Dont have next token to load" + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error of scanner" + e.getMessage());
        }
        finally {
            scanner.close();
            scanner.useDelimiter("\\p{javaWhitespace}+");
        }
        return data;
    }
}
