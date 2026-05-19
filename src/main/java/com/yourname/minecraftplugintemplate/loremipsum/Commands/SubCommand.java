package com.yourname.minecraftplugintemplate.loremipsum.Commands;


import org.bukkit.command.CommandSender;

import java.util.List;

@SuppressWarnings("unused")
public interface SubCommand {

    boolean onCommand(CommandSender sender, String[] args);

    List<String> onTabComplete(CommandSender sender, String[] args);

    String getUsage();

    String getDescription();

    default boolean inGameOnly() {
        return false;
    }

    default String getPermissionNode() {
        return "lorem.admin";
    }
}
