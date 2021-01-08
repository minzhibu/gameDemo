package fightAgainstLandlords.impl;

import fightAgainstLandlords.GamePlayer;

import java.util.List;
import java.util.UUID;

/**
 * 斗地主扑克牌玩家
 */
public class PokerBrandGamePlayer implements GamePlayer<PokerBrand> {
    private List<PokerBrand> brands;
    //状态 0 ：未准备， 1：准备 ，3：正在游戏，4：玩家已退出进入托管
    private int state;
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

    /**
     * 删除已经出掉的牌
     * @param outBrands
     * @return
     */
    public void getBrands(List<PokerBrand> outBrands){
        brands.removeAll(outBrands);
    }

    /**
     * 判断出去的牌是否存在手里
     * @param outBrands
     * @return
     */
    public boolean isExistence(List<PokerBrand> outBrands){
        return brands.containsAll(outBrands);
    }
    @Override
    public List<PokerBrand> out() {
        return null;
    }
}
