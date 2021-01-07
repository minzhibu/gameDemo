package fightAgainstLandlords.impl;

import fightAgainstLandlords.GamePlayer;

import java.util.List;
import java.util.UUID;

/**
 * 斗地主扑克牌玩家
 */
public class PokerBrandGamePlayer implements GamePlayer<PokerBrand> {
    private List<PokerBrand> brands;
    private final long playId;

    public PokerBrandGamePlayer() {
        playId = UUID.randomUUID().getLeastSignificantBits();
    }

    @Override
    public void obtain(List<PokerBrand> brands) {
        this.brands = brands;
    }

    public List<PokerBrand> getBrands() {
        return brands;
    }

    public long getPlayId() {
        return playId;
    }

    @Override
    public List<PokerBrand> out() {
        return null;
    }
}
