package org.ryderrobot.net;

public interface NetClient {
    void sendMessage(String message) throws Exception;
    String receiveMessage() throws Exception;
    void close();

    void init(String host, int port) throws Exception;
}
