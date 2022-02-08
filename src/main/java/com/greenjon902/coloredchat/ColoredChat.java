package com.greenjon902.coloredchat;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ColoredChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("ColoredChat")).setExecutor(new ColoredChatCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
