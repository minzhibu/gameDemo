package fightAgainstLandlords.impl;

import fightAgainstLandlords.Brand;
import fightAgainstLandlords.pokerEnum.PokerKingTypeEnum;
import fightAgainstLandlords.pokerEnum.PokerNameEnum;
import fightAgainstLandlords.pokerEnum.PokerTypeEnum;

/**
 * 斗地主扑克牌
 */
public class PokerBrand implements Brand {
    private final String pokeType;
    private final String pokeName;

    public PokerBrand(PokerTypeEnum pokeType, PokerNameEnum pokeName) {
        this.pokeType = pokeType.getType();
        this.pokeName = pokeName.getName();
    }
    public PokerBrand(PokerKingTypeEnum pokeType) {
        this.pokeType = pokeType.getType();
        this.pokeName = "王";
    }

    public String getPokeType() {
        return pokeType;
    }

    public String getPokeName() {
        return pokeName;
    }

    @Override
    public String toString() {
        return "PokerBrand{" +
                "pokeType='" + pokeType + '\'' +
                ", pokeName='" + pokeName + '\'' +
                '}';
    }

    @Override
    public int BrandSort() {
        return 0;
    }
}
