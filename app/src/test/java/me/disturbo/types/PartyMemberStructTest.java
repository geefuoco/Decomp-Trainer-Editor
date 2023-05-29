package me.disturbo.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PartyMemberStructTest {

    private static PartyMemberStruct partyMemberStruct;

    @Test
    public void runTest() {

        partyMemberStruct = new PartyMemberStruct();

        String nickname = "Tokyo";
        String ball = "ITEM_MASTER_BALL";
        String species = "SPECIES_ZIGZAGOON";
        String friendship = "100";
        String gender = "TRAINER_MON_MALE";
        String heldItem = "ITEM_TOXIC_ORB";
        String ability = "ABILITY_PICKUP";
        String[] ivs = { "20", "30", "14", "7", "18", "24" };
        String[] evs = { "200", "130", "14", "0", "18", "24" };
        String lvl = "15";
        String[] moves = { "MOVE_TACKLE", "MOVE_GROWL", "MOVE_HEADBUTT", "MOVE_BELLY_DRUM" };
        String nature = "NATURE_HARDY";
        String shiny = "FALSE";

        partyMemberStruct.addNickname(nickname);
        partyMemberStruct.addBall(ball);
        partyMemberStruct.addSpecies(species);
        partyMemberStruct.addFriendship(friendship);
        partyMemberStruct.addGender(gender);
        partyMemberStruct.addHeldItem(heldItem);
        partyMemberStruct.addAbility(ability);
        partyMemberStruct.addIvs(ivs);
        partyMemberStruct.addEvs(evs);
        partyMemberStruct.addLvl(lvl);
        partyMemberStruct.addMoves(moves);
        partyMemberStruct.addNature(nature);
        partyMemberStruct.addShiny(shiny);

        String finalStr = partyMemberStruct.build();
        String expectedString = new StringBuilder()
                .append("    {")
                .append(System.lineSeparator())
                .append("    .nickname = COMPOUND_STRING(\"Tokyo\"),")
                .append(System.lineSeparator())
                .append("    .ball = ITEM_MASTER_BALL,")
                .append(System.lineSeparator())
                .append("    .species = SPECIES_ZIGZAGOON,")
                .append(System.lineSeparator())
                .append("    .friendship = 100,")
                .append(System.lineSeparator())
                .append("    .gender = TRAINER_MON_MALE,")
                .append(System.lineSeparator())
                .append("    .heldItem = ITEM_TOXIC_ORB,")
                .append(System.lineSeparator())
                .append("    .ability = ABILITY_PICKUP,")
                .append(System.lineSeparator())
                .append("    .iv = TRAINER_PARTY_IVS(20, 30, 14, 7, 18, 24),")
                .append(System.lineSeparator())
                .append("    .ev = TRAINER_PARTY_EVS(200, 130, 14, 0, 18, 24),")
                .append(System.lineSeparator())
                .append("    .lvl = 15,")
                .append(System.lineSeparator())
                .append("    .moves = {MOVE_TACKLE, MOVE_GROWL, MOVE_HEADBUTT, MOVE_BELLY_DRUM},")
                .append(System.lineSeparator())
                .append("    .nature = TRAINER_PARTY_NATURE(NATURE_HARDY),")
                .append(System.lineSeparator())
                .append("    .isShiny = FALSE,")
                .append(System.lineSeparator())
                .append("    }")
                .toString();
        assertEquals(finalStr, expectedString);
    }

}
