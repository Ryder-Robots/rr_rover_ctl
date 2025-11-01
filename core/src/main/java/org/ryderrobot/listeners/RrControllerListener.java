package org.ryderrobot.listeners;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import org.ryderrobot.models.Joy;

public interface RrControllerListener  extends ControllerListener {
    @Override
    void connected(Controller controller);

    @Override
    void disconnected(Controller controller);

    @Override
    boolean buttonDown(Controller controller, int i);

    @Override
    boolean buttonUp(Controller controller, int i);

    @Override
    boolean axisMoved(Controller controller, int i, float v);

    void reset();

    boolean isRequest();

    Joy getMessage();
}
