package com.greenjon902.coloredchat.colorers;

import org.bukkit.Color;

public class FlatColorer implements Colorer {
    private Color color;
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public FlatColorer(Color color) {
        this.color = color;
    }

    @Override
    public String color(String string) {
        return color + string;
    }
}
