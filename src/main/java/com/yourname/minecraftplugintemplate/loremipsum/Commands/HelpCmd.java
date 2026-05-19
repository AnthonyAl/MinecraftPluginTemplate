package com.yourname.minecraftplugintemplate.loremipsum.Commands;

import com.yourname.minecraftplugintemplate.loremipsum.Core.Utils;
import com.yourname.minecraftplugintemplate.loremipsum.Handlers.CommandsHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HelpCmd implements SubCommand {

    private final CommandsHandler cmdHandler;

    public HelpCmd(CommandsHandler cmdHandler) {
        this.cmdHandler = cmdHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(Utils.parse("&r&7&lCommand List: &r"));

        for (SubCommand cmd : cmdHandler.getCommands()) {
            if (cmd.inGameOnly() && !(sender instanceof Player)) continue;
            if (!sender.hasPermission(cmd.getPermissionNode())) continue;

            sender.sendMessage(Utils.parse("&7 -&b/lorem " + cmd.getUsage()));
            sender.sendMessage(Utils.parse("&7 - " + cmd.getDescription() + "&r"));
            sender.sendMessage(Utils.parse("&7 --- "));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows this page.";
    }

    @Override
    public String getPermissionNode() {
        return "lorem.user";
    }
}
