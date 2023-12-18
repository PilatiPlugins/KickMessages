package dev.pilati.kickmessages;

import net.md_5.bungee.api.chat.BaseComponent;

public class KickMessageData {
    
    private BaseComponent[] reason;

    private String server;

    public KickMessageData(BaseComponent[] reason, String server) {
        this.reason = reason;
        this.server = server;
    }

    public BaseComponent[] getReason() {
        return reason;
    }

    public String getServer() {
        return server;
    }
}
