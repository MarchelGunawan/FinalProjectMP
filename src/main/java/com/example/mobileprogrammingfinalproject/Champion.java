package com.example.mobileprogrammingfinalproject;

public class Champion {
    private int atk;
    private int def;
    private int cost;

    Champion(int atk, int def, int cost){
        this.atk = atk;
        this.def = def;
        this.cost = cost;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }

    public int getCost() {
        return cost;
    }
}
