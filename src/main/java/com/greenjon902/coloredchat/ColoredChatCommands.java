package com.greenjon902.coloredchat;

import com.greenjon902.utils.FilterList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColoredChatCommands implements TabExecutor {
    List<String> firstArgSet = Arrays.asList("say", "nick", "toggleauto");
    List<String> secondArgSet = Arrays.asList("flatColor", "rainbow", "gradient");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        int length = args.length;

        if (length == 1) { //say, nick, toggleauto
            return FilterList.filterListByStart(firstArgSet, args[0]);
        }

        if (length == 2) { //flatcolor, rainbow, gradient
            return FilterList.filterListByStart(secondArgSet, args[1]);
        }

        return null;
    }
}
