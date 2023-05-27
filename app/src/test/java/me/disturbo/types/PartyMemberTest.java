package me.disturbo.types;

import java.util.Arrays;
import java.util.HashMap;

public class PartyMemberTest {


    public static void runTest() {
        HashMap<String, String> values = new HashMap();
        values.put("iv", "TRAINER_PARTY_IVS(0, 0, 0, 0, 0, 0)");
        values.put("ev", "TRAINER_PARTY_EVS(0, 0, 0, 0, 0, 0)");
        values.put("lvl", "21");
        values.put("species", "SPECIES_GEODUDE");
        values.put("friendship", "200");
        values.put("ball", "ITEM_MASTER_BALL");
        values.put("gender", "TRAINER_MON_MALE");
        values.put("nature", "TRAINER_PARTY_NATURE(NATURE_HARDY)");
        values.put("isShiny", "TRUE");
        values.put("moves", "{MOVE_GROWL, MOVE_MAGNITUDE, MOVE_TACKLE, MOVE_EXPLOSION}");

        PartyMember member1 = new PartyMember(values);

        assert Arrays.equals(member1.ivs, new String[]{"0", "0", "0", "0", "0", "0"});
        assert Arrays.equals(member1.evs, new String[]{"0", "0", "0", "0", "0", "0"});
        assert Arrays.equals(member1.moves, new String[]{"MOVE_GROWL", "MOVE_MAGNITUDE", "MOVE_TACKLE", "MOVE_EXPLOSION"});
        assert member1.isShiny == "TRUE";
        assert member1.level == "21";
        assert member1.species == "SPECIES_GEODUDE";
        assert member1.nature == "TRAINER_PARTY_NATURE(NATURE_HARDY)";
        assert member1.nickname == null;
        assert member1.ability == null;
        assert member1.friendship == "200";
        assert member1.gender == "TRAINER_MON_MALE";
        assert member1.ball == "ITEM_MASTER_BALL";


        System.out.println("All tests passed");
    }
}
