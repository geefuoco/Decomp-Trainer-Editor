package me.disturbo.types;

import java.lang.StringBuilder;

public class PartyMemberStruct {

    private final String OPEN_BRACKET = "{";
    private final String CLOSE_BRACKET= "}";
    private final String NEW_LINE = System.lineSeparator();
    private final String INDENT = "    ";
    private final String COMMA = ",";
    private StringBuilder finalStruct;

    public PartyMemberStruct(){
        this.finalStruct = new StringBuilder()
                           .append(INDENT)
                           .append(OPEN_BRACKET)
                           .append(NEW_LINE);
    }

    public String build() {
        return this.finalStruct.append(INDENT + CLOSE_BRACKET).toString();
    }

    private final String buildMoves(String[] moves){
        String finalMoves = "";
        for (int index = 0; index < moves.length; index++) {
            finalMoves += moves[index];
            if (index != moves.length - 1){
                finalMoves += ", ";
            } 
        }
        return INDENT + ".moves = " + OPEN_BRACKET + finalMoves + CLOSE_BRACKET;
    }

    private final String buildIVs(String[] ivs){
        String finalIvs = "";
        for (int i=0; i < ivs.length; i++){
            finalIvs += ivs[i];
            if (i != ivs.length - 1){
                finalIvs += ", ";
            }
        }
        return INDENT + ".iv = " + "TRAINER_PARTY_IVS(" + finalIvs + ")"; 
    }

    private final String buildEVs(String[] evs){
        String finalEvs = "";
        for (int i=0; i < evs.length; i++){
            finalEvs += evs[i];
            if (i != evs.length - 1){
                finalEvs += ", ";
            }
        }
        return INDENT + ".ev = " + "TRAINER_PARTY_EVS(" + finalEvs + ")";
    }

    private final String buildNickname(String nickname){
        return INDENT + ".nickname = " + "COMPOUND_STRING(\"" + nickname + "\")";
    }

    private final String buildNature(String nature){
        return INDENT + ".nature = " + "TRAINER_PARTY_NATURE(" + nature + ")";
    }


    public PartyMemberStruct addIvs(String[] ivs){
        finalStruct
            .append(buildIVs(ivs))
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addEvs(String[] evs){
        finalStruct
            .append(buildEVs(evs))
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addNickname(String nickname) {
        finalStruct
            .append(buildNickname(nickname))
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addMoves(String[] moves) {
        finalStruct
            .append(buildMoves(moves))
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addSpecies(String species) {
        finalStruct
            .append(INDENT)
            .append(".species = ")
            .append(species)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addHeldItem(String heldItem) {
        finalStruct
            .append(INDENT)
            .append(".heldItem = ")
            .append(heldItem)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addAbility(String ability) {
        finalStruct
            .append(INDENT)
            .append(".ability = ")
            .append(ability)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addLvl(String lvl) {
        finalStruct
            .append(INDENT)
            .append(".lvl = ")
            .append(lvl)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addBall(String ball) {
        finalStruct
            .append(INDENT)
            .append(".ball = ")
            .append(ball)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addFriendship(String friendship) {
        finalStruct
            .append(INDENT)
            .append(".friendship = ")
            .append(friendship)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addNature(String nature) {
        finalStruct
            .append(buildNature(nature))
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addGender(String gender) {
        finalStruct
            .append(INDENT)
            .append(".gender = ")
            .append(gender)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

    public PartyMemberStruct addShiny(String isShiny) {
        finalStruct
            .append(INDENT)
            .append(".isShiny = ")
            .append(isShiny)
            .append(COMMA)
            .append(NEW_LINE);
        return this;
    }

}
