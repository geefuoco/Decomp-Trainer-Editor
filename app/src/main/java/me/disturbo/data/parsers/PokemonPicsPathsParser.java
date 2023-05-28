
package me.disturbo.data.parsers;

import me.disturbo.data.LineParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PokemonPicsPathsParser implements LineParser<LinkedHashMap<String, String>> {
    private final HashMap<String, String> picTable;

    public PokemonPicsPathsParser(LinkedHashMap<String, String> picTable){
        this.picTable = picTable;
    }

    public HashMap<String, String> getPicTable() {
        return this.picTable;
    }

    @Override
    public boolean parseLine(LinkedHashMap<String, String> picsPaths, String line) {
        line = line.replaceAll("\\s+", "").replaceAll(System.lineSeparator(), "");
        if(line.contains("constu32gMonFrontPic_")){
            try{
                String [] frontPic = line.substring("constu32".length()).split("=");
                String picDeclaration = frontPic[0].replace("[]", "");
                String picPath = frontPic[1].substring("INCBIN_U32(.".length(), frontPic[1].length() - ".4bpp.lz.);".length()) + ".png";
                picsPaths.put(picTable.get(picDeclaration), picPath);
            } catch(Exception e){
                System.out.println("Error when processing line: " + line);
            }
        }
        return false;
    }
}
