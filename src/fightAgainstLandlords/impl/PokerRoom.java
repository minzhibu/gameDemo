package fightAgainstLandlords.impl;

import fightAgainstLandlords.*;
import fightAgainstLandlords.pokerEnum.OutBrandType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 斗地主扑克牌房间
 */
public class PokerRoom implements Room<PokerBrand> {

    //判断出牌的类型
    private PokerJudgeBrandType pokerJudgeBrandType = new PokerJudgeBrandType();
    //玩家
    private List<PokerBrandGamePlayer> gamePlayers  = new ArrayList<>();
    //房间现在存在的牌
    private List<PokerBrand> brands;
    //当前出牌人的下标
    private int nowGamePlayersIndex;
    //弃权的次数
    private int waiverIndex;
    //上次出的牌
    private Stack<List<PokerBrand>> brandsStack = new Stack<>();
    //地主
    private Map<Long,Integer> longIntegerMap;
    //地主
    private int landlordIndexTemp;
    //地主
    private int landlordIndex;
    //抢地主次数
    private int landlordNum;
    //状态 1:准备 2:抢地主 3:进行游戏
    private int state;




    @Override
    public void start() {
        if(!isEquipment()){
            return;
        }
        //重新生成扑克牌
        generate(new PokerGenerate());
        //洗牌
        shuffleTheCards(new PokerShuffleTheCards());
        //发牌
        dealCards(new PokerDealCards());
        //牌排序
        PokerSort pokerSort = new PokerSort();
        gamePlayers.forEach(gamePlayer -> sortPoker(pokerSort,gamePlayer.getBrands()));
        sortPoker(pokerSort,brands);
        //随机生成地主
        longIntegerMap = gamePlayers.stream().collect(Collectors.toMap(PokerBrandGamePlayer::getPlayId,pokerBrandGamePlayer -> 0));
        landlordIndex = -1;
        landlordIndexTemp = new Random().nextInt(3);
        landlordNum = 0;
        state = 2;
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
        OutBrandType nowJudge = pokerJudgeBrandType.judgeBrandType(brands);
        if(!OutBrandType.NOT_MATCH.equals(nowJudge)){
            //当前出牌人
            PokerBrandGamePlayer pokerBrandGamePlayer = gamePlayers.get(nowGamePlayersIndex);
            //当前出牌人的手中是否存在这些牌
            if(pokerBrandGamePlayer.isExistence(brands)) {
                //当出牌栈为空时，直接将出的牌入栈
                if(brandsStack.isEmpty()){
                    outPlay(brands);
                    result = true;
                }else{
                    //获取上家出的牌
                    List<PokerBrand> nextBrands = brandsStack.pop();
                    OutBrandType nextJudge = pokerJudgeBrandType.judgeBrandType(nextBrands);
                    //判断这次出牌和上次出牌是否为同一类型,
                    if(nextJudge.equals(nowJudge)){
                        if(new PokerBrandComparator().comparator(nextBrands,brands,nowJudge)){
                            outPlay(brands);
                            result = true;
                        }
                    //判断这次出的是否为炸弹
                    }else if(OutBrandType.BOMB.equals(nowJudge)){
                        outPlay(brands);
                        result = true;
                    }
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

    @Override
    public void robLandlord(boolean isRob) {
        if(state == 3){
            return;
        }
        long playId = gamePlayers.get(landlordIndexTemp).getPlayId();
        Integer integer = longIntegerMap.get(playId);
        if(isRob){
            if(integer == 1){
                landlordIndex = landlordIndexTemp;
                state = 3;
                return;
            }else if(integer == -1){
                landlordIndexTemp = (landlordIndexTemp + 1) == gamePlayers.size() ? 0 :landlordIndexTemp + 1;
            }else{
                longIntegerMap.put(playId,integer + 1);
            }
        }else{
            if(integer == 1){
                int temp = landlordIndexTemp;
                temp = (temp + 1) == gamePlayers.size() ? 0 :temp + 1;
                while(longIntegerMap.get(gamePlayers.get(temp).getPlayId()) == -1){
                    temp = (temp + 1) == gamePlayers.size() ? 0 :temp + 1;
                }
                landlordIndex = temp;
                state = 3;
                return;
            }else{
                longIntegerMap.put(playId,-1);
            }
        }
        landlordIndexTemp = (landlordIndexTemp + 1) == gamePlayers.size() ? 0 :landlordIndexTemp + 1;
        int index = 0;
        while(longIntegerMap.get(gamePlayers.get(landlordIndexTemp).getPlayId()) == -1){
            landlordIndexTemp = (landlordIndexTemp + 1) == gamePlayers.size() ? 0 :landlordIndexTemp + 1;
            index++;
            if(index == gamePlayers.size()){
                break;
            }
        }
        int temp = -1;
        index = 0;
        for(int i = 0; i < gamePlayers.size(); i++){
            if(longIntegerMap.get(gamePlayers.get(i).getPlayId()) == -1){
                index++;
            }else if(longIntegerMap.get(gamePlayers.get(i).getPlayId()) == 1){
                temp = i;
            }
        }
        if(index == 3){
            start();
        }else if(index == 2 && temp != -1){
            landlordIndex = temp;
            state = 3;
        }
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

    /**
     * 出牌
     * @param brand
     */
    private void outPlay(List<PokerBrand> brands){
        gamePlayers.get(nowGamePlayersIndex).removeBrands(brands);
        brandsStack.push(brands);
    }

    public PokerJudgeBrandType getPokerJudgeBrandType() {
        return pokerJudgeBrandType;
    }

    public void setPokerJudgeBrandType(PokerJudgeBrandType pokerJudgeBrandType) {
        this.pokerJudgeBrandType = pokerJudgeBrandType;
    }

    public List<PokerBrandGamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(List<PokerBrandGamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public List<PokerBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<PokerBrand> brands) {
        this.brands = brands;
    }

    public int getNowGamePlayersIndex() {
        return nowGamePlayersIndex;
    }

    public void setNowGamePlayersIndex(int nowGamePlayersIndex) {
        this.nowGamePlayersIndex = nowGamePlayersIndex;
    }

    public int getWaiverIndex() {
        return waiverIndex;
    }

    public void setWaiverIndex(int waiverIndex) {
        this.waiverIndex = waiverIndex;
    }

    public Stack<List<PokerBrand>> getBrandsStack() {
        return brandsStack;
    }

    public void setBrandsStack(Stack<List<PokerBrand>> brandsStack) {
        this.brandsStack = brandsStack;
    }

    public int getLandlordIndex() {
        return landlordIndex;
    }

    public void setLandlordIndex(int landlordIndex) {
        this.landlordIndex = landlordIndex;
    }

    public int getLandlordNum() {
        return landlordNum;
    }

    public void setLandlordNum(int landlordNum) {
        this.landlordNum = landlordNum;
    }

    public int getLandlordIndexTemp() {
        return landlordIndexTemp;
    }

    public void setLandlordIndexTemp(int landlordIndexTemp) {
        this.landlordIndexTemp = landlordIndexTemp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PokerRoom{" +
                "pokerJudgeBrandType=" + pokerJudgeBrandType +
                ", gamePlayers=" + gamePlayers +
                ", brands=" + brands +
                ", nowGamePlayersIndex=" + nowGamePlayersIndex +
                ", waiverIndex=" + waiverIndex +
                ", brandsStack=" + brandsStack +
                ", landlordIndexTemp=" + landlordIndexTemp +
                ", landlordIndex=" + landlordIndex +
                ", landlordNum=" + landlordNum +
                '}';
    }
}
