package com.rsltr.concurrency.race.condition;

import com.rsltr.concurrency.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class SequenceThreadSafeAtomic {

    private AtomicInteger value = new AtomicInteger();

    public int getValue() {
        return value.incrementAndGet();
    }
}
