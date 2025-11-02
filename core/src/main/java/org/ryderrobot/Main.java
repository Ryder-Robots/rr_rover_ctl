package org.ryderrobot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ryderrobot.listeners.RrControllerListener;
import org.ryderrobot.listeners.RrControllerListenerImpl;
import org.ryderrobot.models.Heartbeat;
import org.ryderrobot.models.Joy;

import com.badlogic.gdx.utils.Json;
import java.time.Duration;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
//    private Controller controller;
    private final RrControllerListener listener = new RrControllerListenerImpl();

    @Override
    public void create() {
        if (Controllers.getControllers().isEmpty()) {
            Gdx.app.error("Main", "no controller present");
        }
        Controller controller = Controllers.getCurrent();
        controller.addListener(listener);

        // throttle speed to around 5 frames p/second, 200ms
        Gdx.graphics.setForegroundFPS(1000 / 200);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        Json json = new Json();
        String jsonStr = "";
        if (listener.isRequest()) {
            Joy msg = listener.getMessage();
            jsonStr = json.toJson(msg);
        } else {
            Heartbeat heartbeat = Heartbeat.builder()
                .withDuration(Duration.ofMillis(1000 / 200))
                .withHb_Link(Joy.LINK)
                .build();
            jsonStr = json.toJson(heartbeat);
        }
        Gdx.app.debug("Main", "received joystick input");

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
