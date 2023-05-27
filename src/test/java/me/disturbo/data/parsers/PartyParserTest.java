package me.disturbo.data.parsers;

import java.util.ArrayList;
import java.util.HashMap;

public class PartyParserTest {

    private static PartyParser partyParser = new PartyParser();

    public static void runTest() {

        String rawParty = new StringBuilder()
                .append("static const struct TrainerMonCustomized sParty_Sawyer1[] = {")
                .append(System.lineSeparator())
                .append("    {")
                .append(System.lineSeparator())
                .append("    .iv = TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0),")
                .append(System.lineSeparator())
                .append("    .lvl = 21,")
                .append(System.lineSeparator())
                .append("    .species = SPECIES_GEODUDE,")
                .append(System.lineSeparator())
                .append("    .friendship = 200,")
                .append(System.lineSeparator())
                .append("    .ball = ITEM_MASTER_BALL,")
                .append(System.lineSeparator())
                .append("    .ev = TRAINER_PARTY_EVS(0, 0, 0, 0, 0, 0),")
                .append(System.lineSeparator())
                .append("    .moves = {MOVE_GROWL, MOVE_MAGNITUDE, MOVE_TACKLE, MOVE_EXPLOSION},")
                .append(System.lineSeparator())
                .append("    .gender = TRAINER_MON_MALE,")
                .append(System.lineSeparator())
                .append("    .nature = TRAINER_PARTY_NATURE(NATURE_HARDY),")
                .append(System.lineSeparator())
                .append("    .isShiny = TRUE,")
                .append(System.lineSeparator())
                .append("    },")
                .append(System.lineSeparator())
                .append("    {")
                .append(System.lineSeparator())
                .append("    .iv = TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0),")
                .append(System.lineSeparator())
                .append("    .lvl = 29,")
                .append(System.lineSeparator())
                .append("    .species = SPECIES_GEODUDE")
                .append(System.lineSeparator())
                .append("    }")
                .append(System.lineSeparator())
                .append("};")
                .toString();

        String name = "sParty_Sawyer1";

        ArrayList<HashMap<String, String>> partyMemberList = partyParser.parseIntoMap(name, rawParty);

        assert partyMemberList.size() == 2;
        //First Party Member
        HashMap<String, String> member1 = partyMemberList.get(0);

        assert member1.get("iv") == "TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0)";
        assert member1.get("ev") == "TRAINER_PARTY_EVS(0, 0, 0, 0, 0, 0)";
        assert member1.get("moves") == "{MOVE_GROWL, MOVE_MAGNITUDE, MOVE_TACKLE, MOVE_EXPLOSION}";
        assert member1.get("gender") == "TRAINER_MON_MALE";
        assert member1.get("nautre") == "TRAINER_PARTY_NATURE(NATURE_HARDY)";
        assert member1.get("isShiny") == "TRUE";
        assert member1.get("lvl") == "21";
        assert member1.get("species") == "SPECIES_GEODUDE";
        assert member1.get("friendship") == "200";
        assert member1.get("ball") == "ITEM_MASTER_BALL";


        HashMap<String, String> member2 = partyMemberList.get(1);

        assert member1.get("iv") == "TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0)";
        assert member1.get("lvl") == "29";
        assert member1.get("species") == "SPECIES_GEODUDE";

        System.out.println("All Tests passed");
        
    }
}
