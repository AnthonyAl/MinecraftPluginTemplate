package com.yourname.minecraftplugintemplate.loremipsum.Handlers;

import com.yourname.minecraftplugintemplate.loremipsum.Core.Utils;
import com.yourname.minecraftplugintemplate.loremipsum.LoremIpsum;
import com.yourname.minecraftplugintemplate.loremipsum.Commands.HelpCmd;
import com.yourname.minecraftplugintemplate.loremipsum.Commands.ReloadCmd;
import com.yourname.minecraftplugintemplate.loremipsum.Commands.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CommandsHandler implements TabExecutor {

    private final HashMap<String, SubCommand> commands = new HashMap<>();

    public CommandsHandler(LoremIpsum plugin) {
        commands.put("help", new HelpCmd(this));
        commands.put("reload", new ReloadCmd(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&7A really cool Minecraft plugin." +
                    "To check the help page, type &e/lorem help&7.&r"));
            return true;
        }

        SubCommand command = commands.get(args[0].toLowerCase());

        if (command == null) {
            sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&cUnknown command. To check out the help page, type &7/lorem help&c.&r"));
            return true;
        }

        if (!sender.hasPermission(command.getPermissionNode())) {
            sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&cYou have no permission to use this command."));
            return true;
        }

        if (command.inGameOnly() && !(sender instanceof Player)) {
            sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&cThis command cannot run from the console."));
            return true;
        }

        String[] subCmdArgs = new String[args.length - 1];
        System.arraycopy(args, 1, subCmdArgs, 0, subCmdArgs.length);

        if (!command.onCommand(sender, subCmdArgs)) {
            sender.sendMessage(Utils.parse(LoremIpsum.getPrefix() + "&cCommand usage: /lorem &7" + command.getUsage() + "&c.&r"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        if(args.length == 1) {
            List<String> keySet = new ArrayList<>();
            for (Map.Entry<String, SubCommand> entry : commands.entrySet())
                if (sender.hasPermission(entry.getValue().getPermissionNode()))
                    keySet.add(entry.getKey());

            StringUtil.copyPartialMatches(args[0], keySet, completions);
        }
        else if(args.length == 2) {
            if(commands.containsKey(args[0])) {
                List<String> keySet = new ArrayList<>(commands.get(args[0]).onTabComplete(sender, args));
                StringUtil.copyPartialMatches(args[1], keySet, completions);
            }
        }
        //else if(args.length == 3) {} // <-- Here you can handle specific arguments of subcommands.

        //sort the list
        Collections.sort(completions);
        return completions;
    }

    public Collection<SubCommand> getCommands() {
        return commands.values();
    }
}
