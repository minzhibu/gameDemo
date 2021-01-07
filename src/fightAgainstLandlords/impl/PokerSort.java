package fightAgainstLandlords.impl;

import fightAgainstLandlords.Sort;
import fightAgainstLandlords.util.PokerSortRule;

import java.util.Comparator;
import java.util.List;

public class PokerSort implements Sort<PokerBrand> {
    @Override
    public void sort(List<PokerBrand> brands) {
        brands.sort(Comparator.comparing(PokerSortRule::getSort));
    }
}
