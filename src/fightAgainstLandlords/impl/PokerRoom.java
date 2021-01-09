package fightAgainstLandlords.impl;

import fightAgainstLandlords.*;
import fightAgainstLandlords.pokerEnum.OutBrandType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 斗地主扑克牌房间
 */
public class PokerRoom implements Room<PokerBrand> {

    //判断出牌的类型
    private PokerJudgeBrandType pokerJudgeBrandType = new PokerJudgeBrandType();
    //玩家
    private List<PokerBrandGamePlayer> gamePlayers  = new ArrayList<>();;
    //房间现在存在的牌
    private List<PokerBrand> brands;
    //当前出牌人的下标
    private int nowGamePlayersIndex;
    //弃权的次数
    private int waiverIndex;
    //上次出的牌
    private Stack<List<PokerBrand>> brandsStack = new Stack<>();


    /**
     * 创建房间获取扑克牌
     */
    {
        //生成54张扑克牌
        generate(new PokerGenerate());
    }

    @Override
    public void start() {
        if(!isEquipment()){
            return;
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
        if(gamePlayers.size() < 3&& !isRepeat(gamePlayer)){
            gamePlayers.add(gamePlayer);
        }else{
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean play(List<PokerBrand> brands) {
        boolean result = false;
        //判断出的牌是否符合规则
        if(pokerJudgeBrandType.judgeBrandType(brands) != OutBrandType.NOT_MATCH){
            //当前出牌人
            PokerBrandGamePlayer pokerBrandGamePlayer = gamePlayers.get(nowGamePlayersIndex);
            //当前出牌人的手中是否存在这些牌
            if(pokerBrandGamePlayer.isExistence(brands)) {
                if(brandsStack.isEmpty()){
                    pokerBrandGamePlayer.removeBrands(brands);
                    brandsStack.push(brands);
                    result = true;
                }else{

                }

            }
        }
        return result;
    }

    @Override
    public void waiver() {
        waiverIndex++;
        if(waiverIndex == 2){
            brandsStack.forEach(brands :: addAll);
            waiverIndex = 0;
        }
        //切换到下一个人
        nextGamePlayer();
    }

    /**
     * 生成牌
     * @param pokerGenerate
     */
    private void generate(Generate<PokerBrand> pokerGenerate) {
        brands = pokerGenerate.generate();
    }

    /**
     * 切换到下一个人
     */
    private void nextGamePlayer(){
        nowGamePlayersIndex = (nowGamePlayersIndex + 1) == gamePlayers.size() ? 0 :nowGamePlayersIndex + 1;
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

    /**
     * 排序
     * @param sort
     * @param brands
     */
    private void sortPoker(Sort<PokerBrand> sort,List<PokerBrand> brands){
        sort.sort(brands);
    }

    /**
     * 判断人员是否到期切全部准备
     * @return
     */
    private boolean isEquipment() {
        return true;
    }

    /**
     * 人员是否重复进入房间
     * @param gamePlayer
     * @return
     */
    private boolean isRepeat(PokerBrandGamePlayer gamePlayer){
        long count = gamePlayers.stream().filter(pokerBrandGamePlayer -> pokerBrandGamePlayer.getPlayId() == gamePlayer.getPlayId()).count();
        return count != 0;
    }
}
