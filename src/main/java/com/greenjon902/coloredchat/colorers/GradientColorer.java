package com.greenjon902.coloredchat.colorers;

import org.bukkit.Color;

public class GradientColorer implements Colorer {
    private Color[] colors;
    private int[] amounts; // Fit colors to string if null

    //TODO: GETTERS AND SETTERS FOR COLORS AND AMOUNTS

    public GradientColorer(Color... colors) {
        this.colors = colors;
    }

    public GradientColorer(ColorAndAmount... colorsAndAmounts) {
        colors = new Color[colorsAndAmounts.length];
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
        private Color color;
        public Color getColor() {
            return color;
        }
        public void setColor(Color color) {
            this.color = color;
        }

        private int amount;
        public int getAmount() {
            return amount;
        }
        public void setAmount(int amount) {
            this.amount = amount;
        }

        public ColorAndAmount(Color color, int amount) {
            this.color = color;
            this.amount = amount;
        }
    }
}
