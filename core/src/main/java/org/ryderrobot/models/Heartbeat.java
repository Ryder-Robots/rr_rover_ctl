package org.ryderrobot.models;

import java.time.Duration;

public class Heartbeat {
    private final Header header;
    private final RrDuration duration;
    private final RrDuration actual_duration;
    private final String hb_link;

    public Header getHeader() {
        return header;
    }

    public RrDuration getDuration() {
        return duration;
    }

    public RrDuration getActual_duration() {
        return actual_duration;
    }

    public String getHb_link() {
        return hb_link;
    }

    private Heartbeat(Heartbeat.Builder builder) {
        header = builder.header;
        duration = builder.duration;
        hb_link = builder.hb_link;
        actual_duration = builder.actual_duration;
    }

    public static Heartbeat.Builder builder() {
        return new Heartbeat.Builder();
    }

    public static class Builder {
        private  Header header;
        private  RrDuration duration;
        private  RrDuration actual_duration;
        private String hb_link;

        public Heartbeat.Builder withHeader(Header header) {
            this.header = header;
            return this;
        }

        public Heartbeat.Builder withDuration(Duration duration) {
            this.duration = RrDuration.builder().withDuration(duration).build();
            return this;
        }

        public Heartbeat.Builder withHb_Link(String hb_link) {
            this.hb_link = hb_link;
            return this;
        }

        public Heartbeat.Builder withActual_Duration(Duration actual_duration) {
            this.actual_duration = RrDuration.builder().withDuration(actual_duration).build();
            return this;
        }

        public Heartbeat build() {
            header = Header.builder()
                .withFrameId("heartbeat_link")
                .build();
            return new Heartbeat(this);
        }
    }
}
