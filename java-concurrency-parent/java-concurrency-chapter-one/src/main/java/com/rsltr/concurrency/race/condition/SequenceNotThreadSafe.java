package com.rsltr.concurrency.race.condition;

import com.rsltr.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
public class SequenceNotThreadSafe {

    private int value;

    public int getValue() {
        return value++;
    }
}
