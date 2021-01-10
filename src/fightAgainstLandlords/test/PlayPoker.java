package fightAgainstLandlords.test;

import fightAgainstLandlords.impl.PokerBrandGamePlayer;
import fightAgainstLandlords.impl.PokerRoom;

import java.util.Scanner;

public class PlayPoker {

    public static void main(String[] args) {
        PokerRoom pokerRoom = new PokerRoom();
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.start();
        boolean boo1 = true;
        boolean boo2 = true;
        while(boo1){
            while(boo2){
                PokerBrandGamePlayer pokerBrandGamePlayer = pokerRoom.getGamePlayers().get(pokerRoom.getLandlordIndexTemp());
                System.out.println("当前抢地主的人为" + pokerBrandGamePlayer.getPlayId());
                System.out.println("输入1抢地主，2弃权");
                String s = new Scanner(System.in).nextLine();
                pokerRoom.robLandlord("1".equals(s));
                if(pokerRoom.getState() == 3){
                    boo2 = false;
                }
            }
            boo2 = true;
            System.out.println(pokerRoom.getGamePlayers().get(pokerRoom.getLandlordIndex()).getPlayId());
            pokerRoom.start();
        }

    }
}
