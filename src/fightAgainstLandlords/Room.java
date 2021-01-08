package fightAgainstLandlords;

import fightAgainstLandlords.impl.PokerBrandGamePlayer;

import java.util.List;

/**
 * 房间
 */
public interface Room<T> {
    /**
     * 开始游戏
     */
    void start();



    /**
     * 添加玩家
     */
    boolean addGamePlayer(PokerBrandGamePlayer gamePlayer);


    /**
     * 出牌
     * @param brands
     */
    void play(List<T> brands);

    /**
     * 弃权
     */
    void waiver();
}
