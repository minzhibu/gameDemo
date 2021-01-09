package fightAgainstLandlords.test;

import fightAgainstLandlords.impl.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestPoker {
    public static void main(String[] args) {
        PokerRoom pokerRoom = new PokerRoom();
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());
        pokerRoom.addGamePlayer(new PokerBrandGamePlayer());

        pokerRoom.start();
    }

    @Test
    void testPokerType(){
        PokerJudgeBrandType pokerJudgeBrandType = new PokerJudgeBrandType();
        PokerGenerate generate = new PokerGenerate();
        List<PokerBrand> generate1 = generate.generate();
//        for(int i = 0; i < generate1.size(); i++){
//            List<PokerBrand> list = new ArrayList<PokerBrand>(1);
//            list.add(generate1.get(i));
//            OutBrandType outBrandType = pokerJudgeBrandType.judgeBrandType(list);
//            System.out.println(generate1.get(i) + " - " + outBrandType);
//        }
//        generate1.forEach(pokerBrand -> generate1.forEach(pokerBrand1 -> {
//            List<PokerBrand> list = new ArrayList<>(2);
//            list.add(pokerBrand);
//            list.add(pokerBrand1);
//            OutBrandType outBrandType = pokerJudgeBrandType.judgeBrandType(list);
//            System.out.println(pokerBrand+ "-" +pokerBrand1 + " - " + outBrandType);
//        }));
    }
}
