package com.sekwah.sekclib.core.util;

import net.minecraft.network.chat.TextColor;
import java.awt.*;

public class ColorUtil {

    /**
     * Useful for being paired with a user controlled color selector.
     * @param hue range 0-255
     * @param brightness range 0-100
     * @return resulting color
     */
    public static Color recalculateHue(float hue, int brightness) {
        double radiansHue = Math.toRadians(hue);
        int red = (int) (Math.sqrt(Math.cos(radiansHue)+1/2) * 255);
        int green = (int) (Math.sqrt(Math.cos(radiansHue-(Math.PI+1)/2)+1/2) * 255);
        int blue = (int) (Math.sqrt(Math.cos(radiansHue+(Math.PI+1)/2)+1/2) * 255);

        if(brightness > 50){
            float multiValue = (float) (brightness - 50) / 50f;
            red = (int) ((255 - red) * multiValue + red);
            green = (int) ((255 - green) * multiValue + green);
            blue = (int) ((255 - blue) * multiValue + blue);
        }
        else if(brightness < 50){
            float multiValue = (float) brightness / 50f;
            red *= multiValue;
            green *= multiValue;
            blue *= multiValue;
        }

        return new Color(red, green, blue);
    }

    /**
     * Basically just because i wanted to enable the intellij color picker >:)
     *
     * @param color java.awt.Color
     * @return minecraft TextColor
     */
    public static TextColor toMCColor(Color color) {
        return TextColor.parseColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
    }

    public int colorToInt(Color color) {
        return toMCColor(color).getValue();
    }

}
