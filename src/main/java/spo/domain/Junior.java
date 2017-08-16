package spo.domain;

public class Junior extends Worker {
    private Senior boss;

    public Senior getBoss() {
        return boss;
    }

    public void setBoss(Senior boss) {
        this.boss = boss;
    }
}
