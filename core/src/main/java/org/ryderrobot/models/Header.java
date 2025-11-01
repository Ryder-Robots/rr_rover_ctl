package org.ryderrobot.models;

public class Header {
    private int seq;
    private Stamp stamp = new Stamp();
    private String frame_id;

    public int getSeq() {
        return seq;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public String getFrameId() {
        return frame_id;
    }

    public static Header.Builder builder() {
        return new Header.Builder();
    }

    public static class Builder {
        private String frameId;
        private int seq;

        public Builder withFrameId(String frameId) {
            this.frameId = frameId;
            return this;
        }

        public Builder withSeq(int seq) {
            this.seq = seq;
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }

    private Header(Builder builder) {
        this.seq = builder.seq;
        this.frame_id = builder.frameId;
    }
}
