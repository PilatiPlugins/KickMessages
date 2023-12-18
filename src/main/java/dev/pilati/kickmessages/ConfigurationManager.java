package dev.pilati.kickmessages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    Configuration configuration;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public TextComponent getMessage(KickMessageData data) {
        String message = configuration
            .getString("message")
            .replace("{server}", data.getServer())
            .replace("{reason}", 
                new TextComponent(data.getReason()).toPlainText()
            );

        return new TextComponent(
            ChatColor.translateAlternateColorCodes('&', message)
        );
    }

    public void loadConfig() throws IOException {
        String configFileName = "config.yml";

        if(!KickMessages.getInstance().getDataFolder().exists()){
            KickMessages.getInstance().getDataFolder().mkdirs();
        }
    
        File configFile = new File(
            KickMessages.getInstance().getDataFolder(),
            configFileName
        );
    
        if(!configFile.exists()){
            FileOutputStream outputStream = new FileOutputStream(configFile);
            InputStream inputStream = KickMessages.getInstance().getResourceAsStream(configFileName);
            copyFile(inputStream, outputStream);
        }

        configuration = ConfigurationProvider
            .getProvider(YamlConfiguration.class)
            .load(
                new File(
                    KickMessages.getInstance().getDataFolder(),
                    configFileName
                )
            );
    }

    public void copyFile(InputStream inputStream, FileOutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
    
}
