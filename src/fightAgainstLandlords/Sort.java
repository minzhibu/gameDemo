package fightAgainstLandlords;

import java.util.List;

@FunctionalInterface
public interface Sort<T> {
    void sort(List<T> brands);
}
