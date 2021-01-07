package fightAgainstLandlords.util;

import fightAgainstLandlords.pokerEnum.PokerNameEnum;
import fightAgainstLandlords.pokerEnum.PokerTypeEnum;

public class PokerSort {
    PokerNameEnum pokerNameEnum;
    PokerTypeEnum pokerTypeEnum;

    public PokerSort(PokerNameEnum pokerNameEnum, PokerTypeEnum pokerTypeEnum) {
        this.pokerNameEnum = pokerNameEnum;
        this.pokerTypeEnum = pokerTypeEnum;
    }

    public PokerNameEnum getPokerNameEnum() {
        return pokerNameEnum;
    }

    public PokerTypeEnum getPokerTypeEnum() {
        return pokerTypeEnum;
    }

    public String getName(){
        return pokerTypeEnum.getType() + pokerNameEnum.getName();
    }
    public int getSort(){
        return pokerTypeEnum.getSort() + (pokerNameEnum.getSort() * 4);
    }
}
