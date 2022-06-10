package com.greenjon902.coloredchat.colorers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;

public class RainbowColorer implements Colorer {
    private static final TextColor[] colors = new TextColor[] {
            TextColor.color(0xFF0000),
            TextColor.color(0xFF7F00),
            TextColor.color(0xFFFF00),
            TextColor.color(0x00FF00),
            TextColor.color(0x0000FF),
            TextColor.color(0x4B0082),
            TextColor.color(0x9400D3)
    };

    private GradientColorer gradientColorer;

    private int resolution;
    public void setResolution(int resolution) {
        this.resolution = resolution;
        updateWithResolution();
    }
    public int getResolution() {
        return resolution;
    }

    public RainbowColorer(int resolution) {
        this.resolution = resolution;
        updateWithResolution();
    }

    public RainbowColorer() {
        gradientColorer = new GradientColorer(colors);
    }

    private void updateWithResolution() {
        GradientColorer.ColorAndAmount[] colorsAndAmounts = new GradientColorer.ColorAndAmount[colors.length];
        for (int i=0; i<colorsAndAmounts.length; i++) {
            colorsAndAmounts[i] = new GradientColorer.ColorAndAmount(colors[i], resolution);
        }
        gradientColorer = new GradientColorer(colorsAndAmounts);
    }

    @Override
    public Component colorString(String string) {
        return gradientColorer.colorString(string);
    }
}
