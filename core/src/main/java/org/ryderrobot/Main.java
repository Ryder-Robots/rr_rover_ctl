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
import org.ryderrobot.net.NetClient;
import org.ryderrobot.net.UDPClient;

import java.time.Duration;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private final RrControllerListener listener = new RrControllerListenerImpl();
    private NetClient client = new UDPClient();
    private final int fps = 1000 / 50;

    @Override
    public void create() {
        if (Controllers.getControllers().isEmpty()) {
            Gdx.app.error("Main", "no controller present");
        }
        Controller controller = Controllers.getCurrent();
        controller.addListener(listener);

        // TODO: currently robot is hard coded, this will need to fixed.
        Gdx.app.debug("main", "attempting to open network connection");
        try {
            client.init("192.168.2.3", 57410);
        } catch (Exception ex) {
            Gdx.app.error("main", "can not create client", ex);
        }

        // throttle speed to around 5 frames p/second, 200ms
        Gdx.graphics.setForegroundFPS(fps);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {

            Json json = new Json();
            String jsonStr = "";
            float delta = Gdx.graphics.getDeltaTime();
            if (listener.isRequest()) {
                Joy msg = listener.getMessage();
                jsonStr = json.toJson(msg);
            } else {
                long secondsPart = (long) delta;  // integer seconds (3)
                long nanosPart = (long) ((delta - secondsPart) * 1_000_000_000);  // fractional nanoseconds (456 million)

                Duration duration = Duration.ofSeconds(secondsPart, nanosPart);
                Heartbeat heartbeat = Heartbeat.builder()
                    .withDuration(Duration.ofMillis(fps))
                    .withActual_Duration(duration)
                    .withHb_Link(Joy.LINK)
                    .build();
                jsonStr = json.toJson(heartbeat);
            }
            try {
                client.sendMessage(jsonStr);
            } catch (Exception e) {
                Gdx.app.error("main", "could not send message to robotL:", e);
            }
            // reset it, this should stop over-processing of robot, when there is nothing to do.
            listener.reset();
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
        client.close();
    }
}
