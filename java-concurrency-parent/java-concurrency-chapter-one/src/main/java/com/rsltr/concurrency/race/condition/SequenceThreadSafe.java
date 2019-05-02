package com.rsltr.concurrency.race.condition;

import com.rsltr.concurrency.annotation.ThreadSafe;

@ThreadSafe
public class SequenceThreadSafe {

    private int value;

    public synchronized int getValue() {
        return value++;
    }

}
