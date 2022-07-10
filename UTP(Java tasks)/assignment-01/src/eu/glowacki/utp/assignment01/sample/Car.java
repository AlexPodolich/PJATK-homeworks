package eu.glowacki.utp.assignment01.sample;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;

public final class Car implements IAggregable<Car, Integer>, IDeeplyCloneable<Car>{

    public int _speed;

    public Car(){

    }

    public Car(int speed){
        _speed = speed;
    }

    public int getCarSpeed(){
        return _speed;
    }

    @Override
    public Integer aggregate(Integer intermediateResult){
        if(intermediateResult == null){
            return _speed;
        }
        return _speed + intermediateResult;
    }


    @Override
    public Car deepClone() {
        return new Car(_speed);
    }
}
