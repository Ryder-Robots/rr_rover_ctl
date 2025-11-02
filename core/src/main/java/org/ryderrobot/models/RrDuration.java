package org.ryderrobot.models;

import java.time.Duration;

/**
 * Wrapper for Duration object, so that it prints as JSON.
 */
public class RrDuration {
    private final long sec;
    private final int nanosec;

    public long getSec() {
        return sec;
    }

    public int getNanosec() {
        return nanosec;
    }

    private RrDuration(RrDuration.Builder builder) {
        this.sec = builder.sec;
        this.nanosec = builder.nanosec;
    }

    public static class Builder {
        private long sec;
        private int nanosec;

        public RrDuration.Builder withSec(int sec) {
            this.sec = sec;
            return this;
        }

        public RrDuration.Builder withNanosec(int nanosec) {
            this.nanosec = nanosec;
            return this;
        }

        public RrDuration.Builder withDuration(Duration duration) {
            this.sec = duration.getSeconds();
            this.nanosec = duration.getNano();
            return this;
        }

        public RrDuration build() {
            return new RrDuration(this);
        }
    }

    public static RrDuration.Builder builder() {
        return new RrDuration.Builder();
    }
}
