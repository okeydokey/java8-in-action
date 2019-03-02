package com.okeydokey.java8inaction.ch5;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ApplicationTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void 트랜잭션_2011_값_오름차순() {
        System.out.println(
                transactions.stream()
                        .filter(e -> e.getYear() == 2011)
                        .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList())
        );
    }

    @Test
    public void 거래자가_근무하는_모든도시_중복없이_나열() {
        System.out.println(
                transactions.stream()
                        .map(e -> e.getTrader().getCity())
                        .distinct()
                        .collect(Collectors.toList()));
    }

    @Test
    public void 케임브리지_근무하는_모든거래자_이름순_정렬() {
        System.out.println(
                transactions.stream().map(Transaction::getTrader)
                        .filter(e -> e.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(Comparator.comparing(Trader::getName))
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void 모든_거래자_이름을_알파벳순으로_정렬() {
        System.out.println(
                transactions.stream().map(e -> e.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList())

        );
    }

    @Test
    public void 밀라노_거래자_존재_여부() {
        System.out.println(
                transactions.stream()
                        .anyMatch(e -> e.getTrader().getCity().equals("Milan"))

        );
    }

    @Test
    public void 케임브리지_거주하는_거래자의_모든_트랜잭션값_출력() {
        System.out.println(
                transactions.stream()
                        .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                        .map(Transaction::getValue)
                .collect(Collectors.toList())
        );
    }

    @Test
    public void 전체_트랜젝션_중_최대값() {
        System.out.println(
                transactions.stream()
                        .max(Comparator.comparing(Transaction::getValue)).get()
        );
    }

    @Test
    public void 전체_트랜젝션_중_최소값() {
        System.out.println(
                transactions.stream()
                        .min(Comparator.comparing(Transaction::getValue)).get()
        );
    }
}