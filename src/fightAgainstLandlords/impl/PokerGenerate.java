package fightAgainstLandlords.impl;

import fightAgainstLandlords.Generate;
import fightAgainstLandlords.pokerEnum.PokerKingTypeEnum;
import fightAgainstLandlords.pokerEnum.PokerNameEnum;
import fightAgainstLandlords.pokerEnum.PokerTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 扑克牌生成器
 */
public class PokerGenerate implements Generate<PokerBrand> {
    @Override
    public List<PokerBrand> generate() {
        List<PokerBrand> result = Arrays.stream(PokerNameEnum.values()).flatMap(pokerNameEnum ->
                Arrays.stream(PokerTypeEnum.values()).map(pokerTypeEnum -> new PokerBrand(pokerTypeEnum, pokerNameEnum))).collect(Collectors.toList());
        result.addAll(Arrays.stream(PokerKingTypeEnum.values()).map(PokerBrand::new).collect(Collectors.toList()));
        return result;
    }
}
