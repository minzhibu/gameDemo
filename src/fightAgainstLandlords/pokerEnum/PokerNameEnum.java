package fightAgainstLandlords.pokerEnum;

/**
 * 扑克牌名称
 */
public enum PokerNameEnum {
    TWO("2",0),THREE("3",1),FOUR("4",2),FIVE("5",3),SIX("6",4),SEVEN("7",5),EIGHT("8",6),NINE("9",7),TEN("10",8),J("J",9),Q("Q",10),K("K",11),A("A",12);

    private final String name;
    private final int sort;
    PokerNameEnum(String name,int sort){
        this.name = name;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public int getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return "PokerNameEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}
