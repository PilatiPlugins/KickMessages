package dev.pilati.kickmessages;

import java.util.UUID;

import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onServerKick(ServerKickEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        PlayerManager.getInstance().addTemporaryPlayerData(uuid, 
            new KickMessageData(
                event.getKickReasonComponent(),
                event.getKickedFrom().getName()
            )
        );
    }

    @EventHandler
    public void onPlayerConnected(ServerConnectedEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        if (!PlayerManager.getInstance().hasPlayerData(uuid)) {
            return;
        }

        event.getPlayer().sendMessage(
            ConfigurationManager.getInstance().getMessage(
                PlayerManager.getInstance().getAndRemovePlayerData(uuid)
            )
        );
    }
}
