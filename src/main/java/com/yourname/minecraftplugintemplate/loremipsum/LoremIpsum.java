package com.yourname.minecraftplugintemplate.loremipsum;

import com.yourname.minecraftplugintemplate.loremipsum.Handlers.CommandsHandler;
import com.yourname.minecraftplugintemplate.loremipsum.Handlers.ConfigHandler;
import com.yourname.minecraftplugintemplate.loremipsum.Handlers.EventsHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LoremIpsum extends JavaPlugin {

    public ConfigHandler config;
    private static final String prefix = "&r&7[&bLoremIpsum&7] &r";
    private static final String prefixRaw = "[LoremIpsum] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        config = new ConfigHandler(this);
        Objects.requireNonNull(getCommand("lorem")).setExecutor(new CommandsHandler(this));
        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @SuppressWarnings("unused")
    public static String getPrefix() {
        return prefix;
    }

    @SuppressWarnings("unused")
    public static String getPrefixRaw() {
        return prefixRaw;
    }
}
