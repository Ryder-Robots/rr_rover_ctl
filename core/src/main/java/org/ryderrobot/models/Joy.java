package org.ryderrobot.models;

import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import java.util.List;

/**
 * Used to create joystick message and transmit it.
 * <p />
 * <a href="https://docs.ros.org/en/noetic/api/sensor_msgs/html/msg/Joy.html">sensor_msgs/joy</a>
 */

public class Joy {

    public static final String LINK = "joy_ps4";
    private final Header header;
    private final List<Float> axes;
    private final List<Boolean> buttons;

    private Joy(Builder builder) {
        this.header = builder.header;
        this.axes = builder.axes;
        this.buttons = builder.buttons;
    }

    public static Joy.Builder  builder() {
        return new Joy.Builder();
    }

    public Header getHeader() {
        return header;
    }

    public List<Float> getAxes() {
        return axes;
    }

    public List<Boolean> getButtons() {
        return buttons;
    }

    public static class Builder {
        private Header header;
        private List<Float> axes;
        private List<Boolean> buttons;

        public Joy.Builder withAxes(List<Float> axes) {
            this.axes = axes;
            return this;
        }

        public Joy.Builder withButtons(List<Boolean> buttons) {
            this.buttons = buttons;
            return this;
        }

        public Joy build(int seq) {
            header = Header.builder()
                .withFrameId(LINK)
                .withSeq(seq)
                .build();
            return new Joy(this);
        }
    }
}
