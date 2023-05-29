package me.disturbo.types;

import me.disturbo.main.MainActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PartyMember {
    /*
     * The Trainer class manages the information of each pokemon
     * - <fields> serves as a collection of pokemon attributes, each one having a
     * corresponding field
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
            "isShiny" };

    private String[] ivs;
    private String[] evs;
    private String[] moves;
    private String level;
    private String species;
    private String heldItem;
    private String ability;
    private String nature;
    private String gender;
    private String isShiny;
    private String nickname;
    private String friendship;
    private String ball;

    private PartyMemberStruct partyMemberStruct;

    // This is for displaying the pokemon in a list
    public PartyMember(String species) {
    }

    public PartyMember() {
        this((new LinkedList<>(MainActivity.species.keySet())).get(0));
    }

    public PartyMember(Map<String, String> values) {
        // ivs will be in the form TRAINER_PARTY_IVS(1,2,3,4,5,6) or a number string:
        // 122
        String ivs = values.get("iv");
        if (ivs == null || !ivs.contains("TRAINER")) {
            this.ivs = new String[] { "0", "0", "0", "0", "0", "0" };
        } else {
            ivs = ivs.substring(ivs.indexOf("(") + 1, ivs.length() - 1);
            this.ivs = ivs.replaceAll("\\s+", "").split(",");
        }
        // evs will be in the form TRAINER_PARTY_EVS(1,2,3,4,5,6) or not appear at all
        String evs = values.get("ev");
        if (evs != null) {
            evs = evs.substring(evs.indexOf("(") + 1, evs.length() - 1);
            this.evs = evs.replaceAll("\\s+", "").split(",");
        }
        level = values.get("lvl");
        species = values.get("species");
        heldItem = values.get("heldItem");
        moves = extractMoves(values.get("moves"));
        ability = values.get("ability");
        nature = extractNature(values.get("nature"));
        gender = values.get("gender");
        isShiny = values.get("isShiny");
        nickname = values.get("nickname");
        friendship = values.get("friendship");
        ball = values.get("ball");
    }

    private final String extractNature(String rawNature){
        if(rawNature == null){
            return null;
        }
        return rawNature.substring(rawNature.indexOf("(")+1, rawNature.length()-1);
    }

    public String[] getIvs() {
        return ivs;
    }

    public String[] getEvs() {
        return evs;
    }

    public String[] getMoves() {
        return moves;
    }

    public String getLevel() {
        return level;
    }

    public String getSpecies() {
        return species;
    }

    public String getHeldItem() {
        return heldItem;
    }

    public String getAbility() {
        return ability;
    }

    public String getNature() {
        return nature;
    }

    public String getGender() {
        return gender;
    }

    public String getIsShiny() {
        return isShiny;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFriendship() {
        return friendship;
    }

    public String getBall() {
        return ball;
    }

    public void setIvs(String[] ivs) {
        int total = Arrays.stream(ivs).mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        if (total <= MainActivity.MAX_IV_TOTAL) {
            this.ivs = ivs;
        }
    }

    public void setEvs(String[] evs) {
        int total = Arrays.stream(evs).mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        if (total <= MainActivity.MAX_EV_TOTAL) {
            this.evs = evs;
        }
    }

    public void setMoves(String[] moves) {
        this.moves = moves;
    }

    public void setLevel(String level) {
        int lvl = Integer.parseInt(level);
        if (lvl > 0 && lvl <= 100) {
            this.level = level;
        }
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setHeldItem(String heldItem) {
        if (!(heldItem.isEmpty() ||  heldItem == "NONE")) {
            this.heldItem = heldItem;
        }
    }

    public void setAbility(String ability) {
        if (!(ability.isEmpty() || ability == "NONE")) {
            this.ability = ability;
        }
    }

    public void setNature(String nature) {
        if (!(nature.isEmpty() || nature == "NONE")) {
            this.nature = nature;
        }
    }

    public void setGender(String gender) {
        if (!(gender.isEmpty() || gender == "NONE")) {
            this.gender = gender;
        }
    }

    public void setIsShiny(String isShiny) {
        if (isShiny == "TRUE" || isShiny == "FALSE") {
            this.isShiny = isShiny;
        }
    }

    // TODO
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFriendship(String friendship) {
        if (!(friendship.isEmpty() || friendship == "NONE")) {
            this.friendship = friendship;
        }
    }

    public void setBall(String ball) {
        if (!(ball.isEmpty() || ball == "NONE")) {
            this.ball = ball;
        }
    }

    public static final String[] extractMoves(String moves) {
        if (moves == null) {
            return null;
        }
        if (moves.length() == 0)
            return new String[4];
        moves = moves.replaceAll("\\s+", "");
        return moves.substring(1, moves.length() - 1).split(",");
    }

    public static final PartyMember createPartyMemberPlaceholder() {
        Map<String, String> templateValues = new HashMap<>();
        templateValues.put("lvl", "5");
        templateValues.put("species", "SPECIES_NONE");
        return new PartyMember(templateValues);
    }

    public final String buildMemberStruct() {
        partyMemberStruct = new PartyMemberStruct();
        // LVL and SPECIES and IVS should never be null
        partyMemberStruct.addIvs(ivs);
        partyMemberStruct.addLvl(level);
        partyMemberStruct.addSpecies(species);
        if (evs != null) {
            partyMemberStruct.addEvs(evs);
        }
        if (heldItem != null) {
            partyMemberStruct.addHeldItem(heldItem);
        }
        if (moves != null) {
            partyMemberStruct.addMoves(moves);
        }
        if (ability != null) {
            partyMemberStruct.addAbility(ability);
        }
        if (nature != null) {
            partyMemberStruct.addNature(nature);
        }
        if (gender != null) {
            partyMemberStruct.addGender(gender);
        }
        if (isShiny != null) {
            partyMemberStruct.addShiny(isShiny);
        }
        if (nickname != null) {
            partyMemberStruct.addNickname(nickname);
        }
        if (friendship != null) {
            partyMemberStruct.addFriendship(friendship);
        }
        if (ball != null) {
            partyMemberStruct.addBall(ball);
        }
        return partyMemberStruct.build();

    }
}
