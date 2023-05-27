package me.disturbo.main;

import me.disturbo.data.parsers.PartyParserTest;
import me.disturbo.types.PartyMemberTest;
import me.disturbo.types.PartyTest;
import me.disturbo.types.PartyMemberStructTest;

public class TestMain {

    public static void main(String[] args){

        PartyParserTest.runTest();
        PartyMemberTest.runTest();
        PartyMemberStructTest.runTest();
        PartyTest.runTest();
    }

}
