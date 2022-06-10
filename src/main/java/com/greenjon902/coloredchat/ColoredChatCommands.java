package com.greenjon902.coloredchat;

import com.greenjon902.coloredchat.colorers.Colorer;
import com.greenjon902.coloredchat.colorers.FlatColorer;
import com.greenjon902.coloredchat.colorers.GradientColorer;
import com.greenjon902.coloredchat.colorers.RainbowColorer;
import com.greenjon902.utils.FilterList;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
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
        if (!(args.length > 1)) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments, takes at least 2");
            return true;
        }
        String useCase = args[0];
        String colorMode = args[1];
        Colorer colorer;

        if (!firstArgSet.contains(useCase)) {
            sender.sendMessage(ChatColor.RED + "First argument must be on of these - " + firstArgSet);
            return true;
        }
        if (!secondArgSet.contains(colorMode)) {
            sender.sendMessage(ChatColor.RED + "Second argument must be on of these - " + secondArgSet);
            return true;
        }

        String sColor;
        int iColor;
        switch (colorMode) {
            case "flatcolor":
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.RED + "/coloredchat ... flatcolor takes 3 arguments");
                    return true;
                }
                sColor = args[2];
                if (!sColor.startsWith("#")) {
                    sender.sendMessage(ChatColor.RED + "Color has to start with with #");
                    return true;
                }
                try {
                    iColor = Integer.parseInt(sColor.replaceFirst("#", ""), 16);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.RED + "Color can only contain characters 1,2,3,4,5,6,7,8,9,a,b,c,d,e,f");
                    return true;
                }
                colorer = new FlatColorer(Color.fromRGB(iColor));
                break;
            case "rainbow":
                if (!(args.length == 2 || args.length == 3)) {
                    sender.sendMessage(ChatColor.RED + "/coloredchat ... rainbow takes 2 or 3 arguments");
                    return true;
                }
                if (args.length == 2) {
                    colorer = new RainbowColorer();
                } else {
                    int resolution;
                    try {
                        resolution = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + "Invalid number - \"" + args[2] + "\"");
                        return true;
                    }
                    colorer = new RainbowColorer(resolution);
                }
                break;
            case "gradient":
                if (!(args.length >= 3)) {
                    sender.sendMessage(ChatColor.RED + "/coloredchat ... gradient takes 3 or more arguments");
                    return true;
                }
                if (args.length == 3 || args[3].startsWith("#")) { // all args are colors
                    Color[] colors = new Color[args.length-2];
                    for (int i=2; i<args.length; i++) {
                        sColor = args[i];
                        if (!sColor.startsWith("#")) {
                            sender.sendMessage(ChatColor.RED + "Color has to start with with #");
                            return true;
                        }
                        try {
                            iColor = Integer.parseInt(sColor.replaceFirst("#", ""), 16);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "Color can only contain characters 1,2,3,4,5,6,7,8,9,a,b,c,d,e,f");
                            return true;
                        }
                        colors[i-2] = Color.fromRGB(iColor);
                    }
                    colorer = new GradientColorer(colors);
                } else { // not all args are colors
                    if ((args.length % 2) == 1) {
                        sender.sendMessage(ChatColor.RED + "/coloredchat ... gradient takes an even number of args");
                        return true;
                    }
                    
                    GradientColorer.ColorAndAmount[] colorsAndAmounts = new GradientColorer.ColorAndAmount[args.length-2];
                    for (int i=2; i<args.length; i+=2) {
                        sColor = args[i];
                        if (!sColor.startsWith("#")) {
                            sender.sendMessage(ChatColor.RED + "Color has to start with with #");
                            return true;
                        }
                        try {
                            iColor = Integer.parseInt(sColor.replaceFirst("#", ""), 16);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "Color can only contain characters 1,2,3,4,5,6,7,8,9,a,b,c,d,e,f");
                            return true;
                        }
                        int amount;
                        try {
                            amount = Integer.parseInt(args[i+1]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "Invalid number - \"" + args[i+1] + "\"");
                            return true;
                        }
                        colorsAndAmounts[i-2] = new GradientColorer.ColorAndAmount(Color.fromRGB(iColor), amount);
                    }
                    colorer = new GradientColorer(colorsAndAmounts);
                }
        }

        return true;
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
                        return FilterList.filterListByStart(thirdArgSetA, args[2]);
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
