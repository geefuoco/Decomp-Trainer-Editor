package me.disturbo.types;

public class PartyTest {

    
    public static void runTest(){
        
        Party testParty = new Party("testParty");

        //Everything SHOULD work normally even if the trainers are all customized
        assert testParty.getPartyType() == "TrainerMonCustomied";
        //Only valid flag for customized party
        assert testParty.getPartyFlags() == "F_TRAINER_PARTY_EVERYTHING_CUSTOMIZED";

        System.out.println("All tests passed");
    }
}
