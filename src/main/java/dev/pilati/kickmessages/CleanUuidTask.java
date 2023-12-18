package dev.pilati.kickmessages;

import java.util.UUID;

public class CleanUuidTask extends Thread{
    private UUID uuid;

        public CleanUuidTask(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public void run() {
            PlayerManager.getInstance().getAndRemovePlayerData(uuid);
        }
}
