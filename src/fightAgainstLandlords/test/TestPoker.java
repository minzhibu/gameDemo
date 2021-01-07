package fightAgainstLandlords.test;

import fightAgainstLandlords.impl.PokerBrandGamePlayer;
import fightAgainstLandlords.impl.PokerRoom;

public class TestPoker {
    public static void main(String[] args) {
        PokerRoom pokerRoom = new PokerRoom();
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());

        pokerRoom.start();
    }
}
