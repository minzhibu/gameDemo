package fightAgainstLandlords.impl;

import fightAgainstLandlords.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 斗地主扑克牌房间
 */
public class PokerRoom implements Room {

    private List<PokerBrandGamePlayer> gamePlayers;
    private List<PokerBrand> brands;


    @Override
    public void start() {
        if(!isEquipment()){
            return;
        }
        //为空的时候生成牌
        if(brands == null){
            generate(new PokerGenerate());
        }
        //洗牌
        shuffleTheCards(new PokerShuffleTheCards());
        //发牌
        dealCards(new PokerDealCards());
        //牌排序
        PokerSort pokerSort = new PokerSort();
        gamePlayers.forEach(gamePlayer -> sortPoker(pokerSort,gamePlayer.getBrands()));
        sortPoker(pokerSort,brands);
    }




    @Override
    public boolean addGamePlayer(PokerBrandGamePlayer gamePlayer) {
        boolean isSuccess = true;
        if(gamePlayers == null){
            gamePlayers = new ArrayList<>();
        }
        if(gamePlayers.size() < 3){
            gamePlayers.add(gamePlayer);
        }else{
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 生成牌
     * @param pokerGenerate
     */
    private void generate(Generate<PokerBrand> pokerGenerate) {
        brands = pokerGenerate.generate();
    }


    /**
     * 洗牌
     * @param pokerShuffleTheCards
     */
    private void shuffleTheCards(ShuffleTheCards<PokerBrand> pokerShuffleTheCards) {
        brands = pokerShuffleTheCards.shuffleTheCards(brands);
    }

    /**
     * 发牌
     * @param dealCards
     */
    private void dealCards(DealCards<PokerBrandGamePlayer, PokerBrand> dealCards) {
        brands = dealCards.dealCards(gamePlayers,brands);
    }

    private void sortPoker(Sort<PokerBrand> sort,List<PokerBrand> brands){
        sort.sort(brands);
    }

    /**
     * 判断人数
     * @return
     */
    private boolean isEquipment() {
        return true;
    }
}
