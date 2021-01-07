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
        List<PokerBrand> result = Arrays.asList(PokerNameEnum.values()).stream().flatMap(pokerNameEnum ->
                Arrays.asList(PokerTypeEnum.values()).stream().map(pokerTypeEnum -> new PokerBrand(pokerTypeEnum, pokerNameEnum))).collect(Collectors.toList());
        result.addAll(Arrays.asList(PokerKingTypeEnum.values()).stream().map(PokerBrand::new).collect(Collectors.toList()));
        return result;
    }
}
