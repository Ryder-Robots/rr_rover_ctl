package org.ryderrobot.models;

import java.time.Instant;

public class Stamp {

    public long getSec() {
        return sec;
    }

    public int getNsec() {
        return nsec;
    }

    private final long sec;
    private final int nsec;

    public Stamp() {
        java.time.Instant instant = Instant.now();
        sec = instant.getEpochSecond();
        nsec = instant.getNano();
    }
}
