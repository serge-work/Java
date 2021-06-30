package com.mycompany.tennis.core.entity;

public class Score {
    private Long id;
    private Byte set_1;
    private Byte set_2;
    private Byte set_3;
    private Byte set_4;
    private Byte set_5;
    private Match match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSet_1() {
        return set_1;
    }

    public void setSet_1(Byte set_1) {
        this.set_1 = set_1;
    }

    public Byte getSet_2() {
        return set_2;
    }

    public void setSet_2(Byte set_2) {
        this.set_2 = set_2;
    }

    public Byte getSet_3() {
        return set_3;
    }

    public void setSet_3(Byte set_3) {
        this.set_3 = set_3;
    }

    public Byte getSet_4() {
        return set_4;
    }

    public void setSet_4(Byte set_4) {
        this.set_4 = set_4;
    }

    public Byte getSet_5() {
        return set_5;
    }

    public void setSet_5(Byte set_5) {
        this.set_5 = set_5;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
