package com.greenjon902.coloredchat.colorers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class FlatColorer implements Colorer {
    private TextColor color;
    public TextColor getColor() {
        return color;
    }
    public void setColor(TextColor color) {
        this.color = color;
    }

    public FlatColorer(TextColor color) {
        this.color = color;
    }

    @Override
    public Component colorString(String string) {
        return Component.text(string)
                .color(color);
    }
}
