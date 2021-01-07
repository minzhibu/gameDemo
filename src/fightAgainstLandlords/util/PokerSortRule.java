package fightAgainstLandlords.util;

import fightAgainstLandlords.impl.PokerBrand;
import fightAgainstLandlords.pokerEnum.PokerNameEnum;
import fightAgainstLandlords.pokerEnum.PokerTypeEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerSortRule {

    private static Map<String,Integer> map;

    static{
        map = Arrays.asList(PokerNameEnum.values()).stream().flatMap(pokerNameEnum ->
                Arrays.asList(PokerTypeEnum.values()).stream().map(pokerTypeEnum -> new PokerSort(pokerNameEnum, pokerTypeEnum))).collect(Collectors.toMap(PokerSort::getName, PokerSort::getSort));
        map.put("小王",53);
        map.put("大王",54);
    }
    public static int getSort(PokerBrand brand){
        return map.get(brand.getPokeType() + brand.getPokeName());
    }
}
