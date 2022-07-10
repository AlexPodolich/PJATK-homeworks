package eu.glowacki.utp.assignment01.sample;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IContainer;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.List;

public class MyContainer<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult> implements IContainer<TElement, TAggregateResult> {

    private List<TElement> list;

    public MyContainer() {
        list = new ArrayList<>();
    }

    public void addElement(TElement el) {
        list.add(el);
    }

    public TElement getElement(int id) {
        if (id < 0 || id >= list.size()) {
            return null;
        }
        return list.get(id);
    }


    @Override
    public List<TElement> elements() {
        return list;
    }

    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult res = null;
        for (TElement e : list) {
            res = e.aggregate(res);
        }
        return res;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        TElement clone;
        if (index < 0 || index >= list.size()) {
            return null;
        }
        clone = list.get(index).deepClone();
        return clone;
    }

}
