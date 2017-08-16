package spo.domain;

public class Senior extends Worker {

    public void assignJunior(Junior pupil){
        pupil.setBoss(this);
    }
}
