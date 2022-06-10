package com.greenjon902.coloredchat.colorers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class GradientColorer implements Colorer {
    private TextColor[] colors;
    private int[] amounts; // Fit colors to string if null

    //TODO: GETTERS AND SETTERS FOR COLORS AND AMOUNTS

    public GradientColorer(TextColor... colors) {
        this.colors = colors;
    }

    public GradientColorer(ColorAndAmount... colorsAndAmounts) {
        colors = new TextColor[colorsAndAmounts.length];
        amounts = new int[colorsAndAmounts.length];

        for (int i=0; i<colorsAndAmounts.length; i++) {
            colors[i] = colorsAndAmounts[i].getColor();
            amounts[i] = colorsAndAmounts[i].getAmount();
        }
    }

    @Override
    public String color(String string) {
        return null;
    }

    public static class ColorAndAmount {
        private TextColor color;
        public TextColor getColor() {
            return color;
        }
        public void setColor(TextColor color) {
            this.color = color;
        }

        private int amount;
        public int getAmount() {
            return amount;
        }
        public void setAmount(int amount) {
            this.amount = amount;
        }

        public ColorAndAmount(TextColor color, int amount) {
            this.color = color;
            this.amount = amount;
        }
    }
}
