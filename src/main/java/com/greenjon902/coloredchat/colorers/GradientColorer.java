package com.greenjon902.coloredchat.colorers;

import org.bukkit.Color;

public class GradientColorer implements Colorer {
    public GradientColorer(Color... colors) {
    }

    public GradientColorer(ColorAndAmount... colorsAndAmounts) {
    }

    @Override
    public String color(String string) {
        return null;
    }

    public static class ColorAndAmount {
        public ColorAndAmount(Color color, int amount) {
        }
    }
}
