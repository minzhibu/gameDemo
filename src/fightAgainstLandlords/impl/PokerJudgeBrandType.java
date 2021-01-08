package fightAgainstLandlords.impl;
/*
 *@program:gameDemo
 *@author: songjiamin
 *@Time: 2021/1/8  14:32
 */

import fightAgainstLandlords.JudgeBrandType;
import fightAgainstLandlords.pokerEnum.OutBrandType;
import fightAgainstLandlords.util.PokerSortRule;

import java.util.List;

public class PokerJudgeBrandType implements JudgeBrandType<PokerBrand> {
    private PokerSort pokerSort = new PokerSort();

    @Override
    public OutBrandType judgeBrandType(List<PokerBrand> brands) {
        OutBrandType outBrandType = OutBrandType.NOT_MATCH;
        //判断是否为单牌
        if(brands.size() == 1){
            outBrandType = OutBrandType.SINGLE;
        //判断是否为对子
        } else if(isDouble(brands)){
            outBrandType = OutBrandType.DOUBLE;
        //判断是否为三带一
        } else if (isThreeBelts(brands)) {
            outBrandType = OutBrandType.THREE_BELTS;
        //判断是否为顺子
        } else if (isEven(brands)){
            outBrandType = OutBrandType.EVEN;
        //是否为炸弹
        } else if (isBomb(brands)) {
            outBrandType = OutBrandType.BOMB;
        } else if(isCouple(brands)){
            outBrandType = OutBrandType.COUPLE;
        }
        return outBrandType;
    }

    /**
     * 判断是否为对子
     * @param brands
     * @return
     */
    private boolean isDouble(List<PokerBrand> brands){
        boolean result = false;
        if(brands.size() == 2){
            String pokeName = brands.get(0).getPokeName();
            String pokeName1 = brands.get(1).getPokeName();
            result = pokeName.equals(pokeName1) && PokerSortRule.getNameInt(pokeName) < 13;
        }
        return result;
    }

    /**
     * 判断是否为三带一
     * @param brands
     * @return
     */
    private boolean isThreeBelts(List<PokerBrand> brands){
        boolean result = false;
        if(brands.size() == 4){
            pokerSort.sort(brands);
            PokerBrand pokerBrand = brands.get(0);
            int size = 0;
            for(int i = 1; i < brands.size(); i++){
                if(brands.get(i).getPokeName().equals(pokerBrand.getPokeType())){
                    size++;
                }
                pokerBrand = brands.get(i);
            }
            if(size == 2){
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断是否为顺子
     */
    private boolean isEven(List<PokerBrand> brands){
        boolean result = false;
        //排序
        if(brands.size() > 5){
            pokerSort.sort(brands);
            PokerBrand pokerBrand = brands.get(0);
            int index = 0;
            for(int i = 1; i < brands.size(); i++){
                PokerBrand pokerBrand1 = brands.get(i);
                if(PokerSortRule.getNameInt(pokerBrand1.getPokeName()) - PokerSortRule.getNameInt(pokerBrand.getPokeName()) != 1){
                    break;
                }
                index++;
            }
            if(index == brands.size()){
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断是否为炸弹
     * @param brands
     * @return
     */
    private boolean isBomb(List<PokerBrand> brands){
        boolean result = false;
        if(brands.size() == 4){
            pokerSort.sort(brands);
            PokerBrand pokerBrand = brands.get(0);
            int index = 0;
            for(int i = 1; i < brands.size(); i++){
                PokerBrand pokerBrand1 = brands.get(i);
                if(!pokerBrand.getPokeName().equals(pokerBrand1.getPokeName())){
                    break;
                }
                index++;
            }
            if(index == brands.size()){
                result = true;
            }
        }
        if(brands.size() == 2){
            PokerBrand pokerBrand = brands.get(0);
            PokerBrand pokerBrand1 = brands.get(1);
            if("王".equals(pokerBrand.getPokeName()) && "王".equals(pokerBrand1.getPokeName())){
                result =  true;
            }
        }
        return result;
    }

    private boolean isCouple(List<PokerBrand> brands){
        boolean result = false;
        if(brands.size() > 6 && brands.size() % 2 == 0){
            int index = 0;
            for(int i = 0; i < brands.size(); i += 2){
                PokerBrand pokerBrand = brands.get(i);
                PokerBrand pokerBrand1 = brands.get(i + 1);
                if(!pokerBrand.getPokeName().equals(pokerBrand.getPokeName())){
                    break;
                }
                index++;
            }
            if(index == brands.size() / 2){
                result = true;
            }
        }
        return result;
    }

    private boolean isAircraft(List<PokerBrand> brands){
        boolean result = false;
        return result;
    }
}
