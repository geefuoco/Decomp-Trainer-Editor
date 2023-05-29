package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;

import java.util.LinkedList;

public class AbilityParser implements LineParser<LinkedList<String>> {
    @Override
    public boolean parseLine(LinkedList<String> abilities, String line) {
        if(line.contains("ABILITY_")) abilities.add(line.split(" ")[1]);
        return false;
    }
}
