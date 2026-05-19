package com.yourname.minecraftplugintemplate.loremipsum.Commands;

import com.yourname.minecraftplugintemplate.loremipsum.Core.Utils;
import com.yourname.minecraftplugintemplate.loremipsum.LoremIpsum;
import com.yourname.minecraftplugintemplate.loremipsum.Handlers.ConfigHandler;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCmd implements SubCommand{

    private final LoremIpsum plugin;

    public ReloadCmd(LoremIpsum plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&7Reloading Configuration Files..."));
        plugin.reloadConfig();
        plugin.config = new ConfigHandler(plugin);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public String getUsage() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml for the plugin.";
    }
}
