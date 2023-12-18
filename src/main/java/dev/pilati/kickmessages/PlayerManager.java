package dev.pilati.kickmessages;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerManager {

    private static PlayerManager instance;

    private HashMap<UUID, KickMessageData> kickMessageData = new HashMap<>();

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public boolean hasPlayerData(UUID uuid) {
        return kickMessageData.containsKey(uuid);
    }
    
    public KickMessageData getAndRemovePlayerData(UUID uuid) {
        return kickMessageData.remove(uuid);
    }

    public void addTemporaryPlayerData(UUID uuid, KickMessageData data) {
        kickMessageData.put(uuid, data);

        KickMessages.getInstance().getProxy().getScheduler().schedule(
            KickMessages.getInstance(),
            new CleanUuidTask(uuid),
            5,
            TimeUnit.SECONDS
        );
    }
}
