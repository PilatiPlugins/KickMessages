package dev.pilati.kickmessages;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class KickMessages extends Plugin implements Listener {

    private static KickMessages instance;

    public static KickMessages getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        try{
            ConfigurationManager.getInstance().loadConfig();
        } catch (Exception e) {
            getLogger().severe("Error while loading config.yml");
            e.printStackTrace();
            return;
        }

        this.getProxy().getPluginManager().registerListener(this, new PlayerEvents());
        
    }   
}
