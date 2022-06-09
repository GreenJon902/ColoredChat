package com.greenjon902.coloredchat;

import com.greenjon902.utils.FilterList;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ColoredChatCommands implements TabExecutor {
    List<String> firstArgSet = Arrays.asList("say", "nick", "toggleauto");
    List<String> secondArgSet = Arrays.asList("flatcolor", "rainbow", "gradient");
    List<String> thirdArgSetA = Arrays.asList("#000000", "#111111", "#222222", "#333333", "#444444", "#555555", "#666666", "#777777", "#888888", "#999999", "#aaaaaa","#bbbbbb", "#cccccc", "#dddddd", "#eeeeee", "#ffffff");
    List<String> thirdArgSetB = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        int length = args.length;
        String colorMode;

        switch (length) {
            case 1: //say, nick, toggleauto
                return FilterList.filterListByStart(firstArgSet, args[0]);

            case 2: //flatcolor, rainbow, gradient
                return FilterList.filterListByStart(secondArgSet, args[1]);

            case 3:
                colorMode = args[1];
                switch (colorMode) {
                    case "flatcolor":
                    case "rainbow":
                        return FilterList.filterListByStart(thirdArgSetA, args[2]);
                }
            case 4:
                colorMode = args[1];
                switch (colorMode) {
                    case "flatcolor":
                        return null;
                    case "rainbow":
                        return FilterList.filterListByStart(thirdArgSetB, args[2]);
                }
        }

        if (length >= 2) {
            if (args[1].equals("gradient")) {
                if (args.length == 3) { //cant do next thing while length is 0
                    return FilterList.filterListByStart(new ArrayList<String>(thirdArgSetA) {{
                        addAll(thirdArgSetB); //merge lists
                    }}, args[args.length - 1]);
                }

                 if (args[3].startsWith("#")) { // all args are colors
                    return FilterList.filterListByStart(thirdArgSetA, args[args.length - 1]);

                 } else { // not all args are colors
                     if ((length % 2) == 0) { //if event return distances
                         return FilterList.filterListByStart(thirdArgSetB, args[args.length - 1]);
                     } else {
                         return FilterList.filterListByStart(thirdArgSetA, args[args.length - 1]);
                     }
                 }
            }
        }

        return null;
    }
}
