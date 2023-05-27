package me.disturbo.types;

import me.disturbo.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class PartyMember {
    /*
            The Trainer class manages the information of each pokemon
             - <fields> serves as a collection of pokemon attributes, each one having a corresponding field
    */

    public static final String[] fields = { "nickname",
                                            "ev",
                                            "iv",
                                            "lvl",
                                            "species",
                                            "heldItem",
                                            "moves",
                                            "ability",
                                            "ball",
                                            "friendship",
                                            "nature",
                                            "gender",
                                            "isShiny"};

    public String[] ivs;
    public String[] evs;
    public String[] moves;
    public String level;
    public String species;
    public String heldItem;
    public String ability;
    public String nature;
    public String gender;
    public String isShiny;
    public String nickname;
    public String friendship;
    public String ball;
    public PartyMemberStruct partyMemberStruct;

    //This is for displaying the pokemon in a list 
    public PartyMember(String species){}

    public PartyMember(){
        this((new LinkedList<>(MainActivity.species.keySet())).get(0));
    }

    public PartyMember(HashMap<String, String> values){
        //ivs will be in the form TRAINER_PARTY_IVS(1,2,3,4,5,6) or a number string: 122
        String ivs = values.get("iv");
        if(ivs != null){
            ivs = ivs.substring(ivs.indexOf("TRAINER_PARTY_IVS("), ivs.length()-1);
            this.ivs = ivs.split(",");
        } else {
            this.ivs = new String[]{"0", "0", "0", "0", "0", "0"};
        }
        //evs will be in the form TRAINER_PARTY_EVS(1,2,3,4,5,6) or not appear at all
        String evs = values.get("ev");
        if(evs != null){
            evs = evs.substring(evs.indexOf("TRAINER_PARTY_EVS("), evs.length()-1);
            this.evs = evs.split(",");
        }
        level = values.get("lvl");
        species = values.get("species");
        heldItem = values.get("heldItem");
        moves = extractMoves(values.get("moves"));
        ability = values.get("ability");
        nature = values.get("nature");
        gender = values.get("gender");
        isShiny = values.get("isShiny");
        nickname = values.get("nickname");
        friendship = values.get("friendship");
        ball = values.get("ball");
    }

    public static final String[] extractMoves(String moves){
        if (moves == null){
            return null;
        }
        if(moves.length() == 0) return new String[4];
        return moves.substring(1, moves.length() - 1).split(",");
    }

    public final String buildMemberStruct(){
        partyMemberStruct = new PartyMemberStruct();
        partyMemberStruct.addIvs(ivs);
        if(evs != null){
            partyMemberStruct.addIvs(ivs);
        } 
        partyMemberStruct.addLvl(level);
        partyMemberStruct.addSpecies(species);
        if(heldItem != MainActivity.items.get(0)){
            partyMemberStruct.addHeldItem(heldItem);
        }
        if(moves.length > 0){
            partyMemberStruct.addMoves(moves);
        }
        if(ability != null){
            partyMemberStruct.addAbility(ability);
        }
        if(nature != null){
            partyMemberStruct.addNature(nature);
        }
        if(gender != null){
            partyMemberStruct.addGender(gender);
        }
        if(isShiny != null){
            partyMemberStruct.addShiny(isShiny);
        }
        if(nickname != null){
            partyMemberStruct.addNickname(nickname);
        }
        if(friendship != null){
            partyMemberStruct.addFriendship(friendship);
        }
        if(ball != null){
            partyMemberStruct.addBall(ball);
        }
        return partyMemberStruct.build();
        
    }
}
