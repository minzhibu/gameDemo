package fightAgainstLandlords.impl;

import fightAgainstLandlords.Brand;
import fightAgainstLandlords.pokerEnum.PokerKingTypeEnum;
import fightAgainstLandlords.pokerEnum.PokerNameEnum;
import fightAgainstLandlords.pokerEnum.PokerTypeEnum;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerBrand that = (PokerBrand) o;
        return pokeType.equals(that.pokeType) &&
                pokeName.equals(that.pokeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokeType, pokeName);
    }

    @Override
    public int BrandSort() {
        return 0;
    }
}
