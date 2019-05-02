package com.rsltr.concurrency.runner;

import com.rsltr.concurrency.race.condition.SequenceNotThreadSafe;
import com.rsltr.concurrency.race.condition.SequenceThreadSafe;
import com.rsltr.concurrency.race.condition.SequenceThreadSafeAtomic;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainRunner {

    @SneakyThrows
    public static void main(String[] args) {
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        SequenceNotThreadSafe sequenceNotThreadSafe = new SequenceNotThreadSafe();
        SequenceThreadSafe sequenceThreadSafe = new SequenceThreadSafe();
        SequenceThreadSafeAtomic sequenceThreadSafeAtomic = new SequenceThreadSafeAtomic();
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
        List<Integer> list = new CopyOnWriteArrayList<>();

        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());


        for (int i = 1; i <= 1000000; i++) {
            executor.execute(()-> {
                //list.add(sequenceNotThreadSafe.getValue());
                set.add(sequenceNotThreadSafe.getValue());
                //integers.add(sequenceNotThreadSafe.getValue());
            });
        }

        TimeUnit.SECONDS.sleep(8L);

        System.out.println(list.size());
        System.out.println(set.size());
        System.out.println(integers.size());

        list.clear();
        set.clear();
        integers.clear();

        for (int i = 0; i < 10000 ; i++) {
            executor.execute(()-> {
                list.add(sequenceThreadSafe.getValue());
                set.add(sequenceThreadSafe.getValue());
                integers.add(sequenceThreadSafe.getValue());
            });
        }

        TimeUnit.SECONDS.sleep(5L);

        System.out.println("=====================");
        System.out.println(list.size());
        System.out.println(set.size());
        System.out.println(integers.size());
    }
}
