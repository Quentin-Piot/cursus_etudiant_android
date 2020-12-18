package fr.utt.if26.projet_final_if26.models;

import java.util.Arrays;
import java.util.List;

public class NombreCreditsCategorie {

    private int cs = 0;
    private int tm = 0;
    private int st = 0;
    private int ec = 0;
    private int me = 0;
    private int ht = 0;
    private int hp = 0;

    private List<Integer> maxProgress = Arrays.asList(24, 24, 60, 12, 4, 4, 180, 0);
    private int total;

    public NombreCreditsCategorie(int cs, int tm, int st, int ec, int me, int ht, int hp) {
        this.cs = cs;
        this.tm = tm;
        this.st = st;
        this.ec = ec;
        this.me = me;
        this.ht = ht;
        this.hp = hp;
        this.total = cs + tm + st + ec + me + ht + hp;

        if (cs > maxProgress.get(0)) maxProgress.set(0, cs);
        if (tm > maxProgress.get(1)) maxProgress.set(1, tm);
        if (st > maxProgress.get(2)) maxProgress.set(2, st);
        if (ec > maxProgress.get(3)) maxProgress.set(3, ec);
        if (me > maxProgress.get(4)) maxProgress.set(4, me);
        if (ht > maxProgress.get(5)) maxProgress.set(5, ht);
        if (hp > maxProgress.get(7)) maxProgress.set(7, hp);

        if (total > maxProgress.get(6)) maxProgress.set(6, total);

    }


    public int getCs() {
        return cs;
    }

    public int getTm() {
        return tm;
    }

    public int getSt() {
        return st;
    }

    public int getEc() {
        return ec;
    }

    public int getMe() {
        return me;
    }

    public int getHt() {
        return ht;
    }


    public int getHp() {
        return hp;
    }

    public int getTotal() {
        return total;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public void setTm(int tm) {
        this.tm = tm;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public void setHt(int ht) {
        this.ht = ht;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxProgress(List<Integer> maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Integer> getMaxProgress() {
        return maxProgress;
    }
}