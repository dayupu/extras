package org.dayup.avatar.jpa.base;

import java.util.Comparator;

public class SequenceComparatorASC implements Comparator<DataSequence> {

    @Override
    public int compare(DataSequence o1, DataSequence o2) {
        int seqOne = o1.getSequence() == null ? 0 : o1.getSequence();
        int seqTwo = o2.getSequence() == null ? 0 : o2.getSequence();
        if (seqOne > seqTwo) {
            return 1;
        }
        if (seqOne < seqTwo) {
            return -1;
        }
        return 0;
    }
}
