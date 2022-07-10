package eu.glowacki.utp.assignment01.sample;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;

public final class Computer implements IAggregable<Computer, Integer>, IDeeplyCloneable<Computer> {

    public int _compID;

    public Computer(){

    }

    public Computer(int compID){
        _compID = compID;
    }

    public int getCompID(){
        return _compID;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return _compID;
        }
        return _compID + intermediateResult;
    }

    @Override
    public Computer deepClone() {
        return new Computer(_compID);
    }
}
