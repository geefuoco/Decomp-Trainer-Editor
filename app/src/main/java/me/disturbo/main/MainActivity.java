package me.disturbo.main;

import me.disturbo.types.Trainer;
import me.disturbo.types.TrainerClass;
import me.disturbo.ui.MainFrame;

import javax.swing.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MainActivity {
    /*
            The MainActivity class creates the app frame and contains static references to objects accessed from multiple points of the app
    */

    public static MainFrame mainFrame;
    public static File projectDirectory = null;

    public static final int ITEMS_MAX = 4;
    public static final int PARTY_MAX = 6;
    public static final int NAME_MAX = 11;
    public static final int MOVES_MAX = 4;
    public static final int MAX_EV_TOTAL = 510;
    public static final int MAX_IV_TOTAL = 31*6;

    public static String currentTrainer = null;
    public static LinkedHashMap<String, Integer> trainerIndexes, partyIndexes;
    public static LinkedHashMap<String, TrainerClass> trainerClasses;
    public static LinkedList<String> items, natures, abilities;
    public static LinkedHashMap<String, String> moves, species;
    public static LinkedList<String> music, aiFlags, trainerPicList;
    public static LinkedHashMap<String, String> trainerPicPaths, pokemonPicPaths;
    public static LinkedHashMap<String, Trainer> loadedTrainers = new LinkedHashMap<>();

    public static void main(String[] args){
        // Ensure MainFrame runs on an Event Dispatch Thread
        SwingUtilities.invokeLater(() ->  mainFrame = new MainFrame());
    }
}
