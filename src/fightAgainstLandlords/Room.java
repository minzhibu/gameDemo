package fightAgainstLandlords;

import fightAgainstLandlords.impl.PokerBrandGamePlayer;

/**
 * 房间
 */
public interface Room {
    /**
     * 开始游戏
     */
    void start();



    /**
     * 添加玩家
     */
    boolean addGamePlayer(PokerBrandGamePlayer gamePlayer);

}
