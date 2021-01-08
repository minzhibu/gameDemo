package fightAgainstLandlords;
/*
 *@program:gameDemo
 *@author: songjiamin
 判断出牌的类型
 *@Time: 2021/1/8  14:30
 */

import fightAgainstLandlords.pokerEnum.OutBrandType;

import java.util.List;

@FunctionalInterface
public interface JudgeBrandType<T extends Brand> {

    OutBrandType judgeBrandType(List<T> brands);
}
