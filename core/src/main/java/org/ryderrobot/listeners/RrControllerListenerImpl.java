package org.ryderrobot.listeners;

import com.badlogic.gdx.controllers.Controller;
import org.ryderrobot.models.Joy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RrControllerListenerImpl implements RrControllerListener {


    /**
     * trim of deadzone,  set at 0.05 (May be a little high)
     *
     * @param input axes input
     * @return normalized axes output after deadzone subtracted.
     */
    private float trimDeadzone(float input) {
        return (Math.abs(input) < 0.05) ? 0 : input;
    }


    @Override
    public void connected(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int i) {
        isRequest = true;
        this.controller = controller;
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int i) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int i, float v) {

        if (trimDeadzone(v) != 0) {
            isRequest = true;
            this.controller = controller;
            return true;
        }
        return false;
    }

    @Override
    public boolean isRequest() {
        return isRequest;
    }


    @Override
    public void reset() {
        isRequest = false;
    }

    @Override
    public Joy getMessage() {
        List<Float> axes = new ArrayList<>();
        List<Boolean> buttons = new ArrayList<>();

        if (Optional.ofNullable(controller).isPresent()) {
            for (int i = 0; i < controller.getAxisCount(); i++) {
                axes.add(controller.getAxis(i));
            }

            for (int i = controller.getMinButtonIndex(); i < controller.getMaxButtonIndex(); i++) {
                buttons.add(controller.getButton(i));
            }
        }

        return Joy.builder()
            .withAxes(axes)
            .withButtons(buttons)
            .build(++seq);
    }

    private boolean isRequest = false;
    private Controller controller = null;
    private int seq = 0;
}
