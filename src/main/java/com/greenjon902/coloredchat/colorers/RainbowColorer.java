package com.greenjon902.coloredchat.colorers;

import org.bukkit.Color;

public class RainbowColorer implements Colorer {
    private static final Color[] colors = new Color[] {
            Color.fromRGB(0xFF0000),
            Color.fromRGB(0xFF7F00),
            Color.fromRGB(0xFFFF00),
            Color.fromRGB(0x00FF00),
            Color.fromRGB(0x0000FF),
            Color.fromRGB(0x4B0082),
            Color.fromRGB(0x9400D3)
    };

    private GradientColorer gradientColorer;

    public RainbowColorer(int resolution) {
        GradientColorer.ColorAndAmount[] colorsAndAmounts = new GradientColorer.ColorAndAmount[colors.length];
        for (int i=0; i<colorsAndAmounts.length; i++) {
            colorsAndAmounts[i] = new GradientColorer.ColorAndAmount(colors[i], resolution);
        }
        gradientColorer = new GradientColorer(colorsAndAmounts);
    }

    public RainbowColorer() {
        gradientColorer = new GradientColorer(colors);
    }

    @Override
    public String color(String string) {
        return gradientColorer.color(string);
    }
}
