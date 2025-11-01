package org.ryderrobot.models;

import java.time.Duration;

public class Heartbeat {
    private final Header header;
    private final Duration duration;
    private final Duration actual_duration;

    public Header getHeader() {
        return header;
    }

    public Duration getDuration() {
        return duration;
    }

    public Duration getActual_duration() {
        return actual_duration;
    }

    private Heartbeat(Heartbeat.Builder builder) {
        header = builder.header;
        duration = builder.duration;
        actual_duration = builder.actual_duration;
    }

    public static class Builder {
        private  Header header;
        private  Duration duration;
        private  Duration actual_duration;

        public Heartbeat.Builder withHeader(Header header) {
            this.header = header;
            return this;
        }

        public Heartbeat.Builder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public Heartbeat.Builder withActual_Duration(Duration actual_duration) {
            this.actual_duration = actual_duration;
            return this;
        }

        public Heartbeat build() {
            return new Heartbeat(this);
        }
    }
}
