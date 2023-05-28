package me.disturbo.data;

import me.disturbo.data.composers.PartiesComposer;
import me.disturbo.data.composers.TrainersComposer;
import me.disturbo.data.indexers.PartyIndexer;
import me.disturbo.data.indexers.TrainerIndexer;
import me.disturbo.data.parsers.*;
import me.disturbo.main.MainActivity;
import me.disturbo.types.Party;
import me.disturbo.types.Trainer;
import me.disturbo.types.TrainerClass;

import java.io.*;
import java.util.*;

public class DataManager {

    private static final String include_constants = new StringBuilder()
                                                .append(MainActivity.projectDirectory)
                                                .append(File.separator)
                                                .append("include")
                                                .append(File.separator)
                                                .append("constants")
                                                .append(File.separator)
                                                .toString();

    private static final String src_data_text = new StringBuilder()
                                            .append(MainActivity.projectDirectory)
                                            .append(File.separator)
                                            .append("src")
                                            .append(File.separator)
                                            .append("data")
                                            .append(File.separator)
                                            .append("text")
                                            .append(File.separator)
                                            .toString();


    private static final File trainerClasses = new File(
        new StringBuilder()
            .append(src_data_text)
            .append("trainer_class_names.h")
            .toString()
    );

    private static final File items = new File(
        new StringBuilder()
            .append(include_constants)
            .append("items.h")
            .toString()
    );

    private static final File moves = new File(
        new StringBuilder()
            .append(src_data_text)
            .append("move_names.h")
            .toString()
    );

    private static final File species = new File(
        new StringBuilder()
            .append(src_data_text)
            .append("species_names.h")
            .toString()
    );

    private static final File music = new File(
        new StringBuilder()
            .append(include_constants)
            .append("trainers.h")
            .toString()
    );

    private static final File aiFlags = new File(
        new StringBuilder()
            .append(include_constants)
            .append("battle_ai.h")
            .toString()
    );


    private static final File pokemonPicsPaths = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)  
            .append(File.separator)  
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("graphics")
            .append(File.separator)
            .append("pokemon.h")
            .toString()
    );

    private static final File pokemonPicTable = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)  
            .append(File.separator)  
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("pokemon_graphics")
            .append(File.separator)
            .append("front_pic_table.h")
            .toString()
    );
    private static final File trainerPicList = new File(
        new StringBuilder()
            .append(include_constants)
            .append("trainers.h")
            .toString()
    );

    private static final File trainerPicsPaths = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)  
            .append(File.separator)  
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("graphics")
            .append(File.separator)
            .append("trainers.h")
            .toString()
    );

    private static final File trainerPicTable = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)  
            .append(File.separator)  
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("trainer_graphics")
            .append(File.separator)
            .append("front_pic_tables.h")
            .toString()
    );

    private static final File trainers = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)
            .append(File.separator)
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("trainers.h")
            .toString()
    );

    private static final File parties = new File(
        new StringBuilder()
            .append(MainActivity.projectDirectory)
            .append(File.separator)
            .append("src")
            .append(File.separator)
            .append("data")
            .append(File.separator)
            .append("trainer_parties.h")
            .toString()
    );

    public static final LinkedHashMap<String, TrainerClass> loadTrainerClasses(){
        return (new TrainerClassesParser()).parse(trainerClasses, new LinkedHashMap<>());
    }

    public static final LinkedList<String> loadItems(){
        return (new ItemsParser()).parse(items, new LinkedList<>());
    }

    public static final LinkedHashMap<String, String> loadMoves(){
        return (new MovesParser()).parse(moves, new LinkedHashMap<>());
    }

    public static final LinkedHashMap<String, String> loadSpecies(){
        return (new SpeciesParser()).parse(species, new LinkedHashMap<>());
    }


    public static final LinkedList<String> loadMusic(){
        return (new MusicParser()).parse(music, new LinkedList<>());
    }

    public static final LinkedList<String> loadAiFlags(){
        return (new AiFlagsParser()).parse(aiFlags, new LinkedList<>());
    }

    public static final LinkedList<String> loadTrainerPicsList(){
        return (new TrainerPicsListParser()).parse(trainerPicList, new LinkedList<>());
    }

    public static final LinkedHashMap<String, String> loadTrainerPicsPaths(){
        LinkedHashMap<String, String> picTable = loadTrainerPicTable();
        return (new TrainerPicsPathsParser(picTable)).parse(trainerPicsPaths, new LinkedHashMap<>());
    }

    private static final LinkedHashMap<String, String> loadTrainerPicTable(){
        return (new TrainerPicTableParser()).parse(trainerPicTable, new LinkedHashMap<>());
    }

    public static final LinkedHashMap<String, String> loadPokemonPicsPaths(){
        LinkedHashMap<String, String> picTable = loadPokemonPicTable();
        return (new PokemonPicsPathsParser(picTable)).parse(pokemonPicsPaths, new LinkedHashMap<>());
    }

    private static final LinkedHashMap<String, String> loadPokemonPicTable(){
        return (new PokemonPicTableParser()).parse(pokemonPicTable, new LinkedHashMap<>());
    }

    public static final LinkedHashMap<String, Integer> indexTrainers(){
        return (new TrainerIndexer()).parse(trainers, new LinkedHashMap<>());
    }
    
    public static final Trainer loadTrainer(String name){
        return (new TrainerParser()).parser(trainers, MainActivity.trainerIndexes, name);
    }

    public static final void saveTrainers(LinkedList<Trainer> orderedTrainers){
        (new TrainersComposer()).compose(trainers, orderedTrainers, MainActivity.trainerIndexes);
    }

    public static final LinkedHashMap<String, Integer> indexParties(){

        return (new PartyIndexer()).parse(parties, new LinkedHashMap<>());
    }

    public static final Party loadParty(String name){
        return (new PartyParser()).parser(parties, MainActivity.partyIndexes, name);
    }

    public static final void saveParties(LinkedList<Party> orderedParties){
        (new PartiesComposer()).compose(parties, orderedParties, MainActivity.partyIndexes);
    }
}
