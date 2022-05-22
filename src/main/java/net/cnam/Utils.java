package net.cnam;

import java.awt.Color;
import java.awt.Graphics2D;

public class Utils {

    public static void plot(Graphics2D graphics, int x, int y, int c) {
        if (graphics == null)
            return;
        graphics.setColor(Utils.getSurfaceColor(c));
        // graphics.drawLine(x, y, x, y);
        graphics.drawRect(x * 2, y * 2, 1, 1);
    }

    public static void drawLine(Graphics2D graphics, int x1, int y1, int x2, int y2, int c) {
        if (graphics == null)
            return;
        graphics.setColor(Utils.getSurfaceColor(c));
        // graphics.drawLine(x1, y1, x2, y2);
        if (x1 == x2 && y2 >= y1) {
            for (int i = y1; i <= y2; i++) {
                graphics.drawRect(x1 * 2, i * 2, 1, 1);
            }
        } else if (y1 == y2 && x2 >= x1) {
            for (int i = x1; i <= x2; i++) {
                graphics.drawRect(i * 2, y1 * 2, 1, 1);
            }
        }
    }

    public static Color getSurfaceColor(int c) {
        if (c < 0 || c > 15) {
            throw new IllegalArgumentException("c doit être compris entre 0 et 15");
        }

        switch (c) {
            case 0:
                // Noir
                return new Color(0, 0, 0);
            case 1:
                // Bleu
                return new Color(0, 0, 255);
            case 2:
                // Bleu vif
                return new Color(0, 0, 139);
            case 3:
                // Rouge
                return new Color(255, 0, 0);
            case 4:
                // Magenta
                return new Color(255, 0, 255);
            case 5:
                // TODO Mauve
                return new Color(212, 115, 212);
            case 6:
                // Rouge vif
                return new Color(139, 0, 0);
            case 7:
                // Violet
                return new Color(128, 0, 128);
            case 8:
                // Magenta vif
                return new Color(139, 0, 139);
            case 9:
                // Vert
                return new Color(0, 128, 0);
            case 10:
                // Turquoise
                return new Color(64, 224, 208);
            case 11:
                // Bleu ciel
                return new Color(135, 206, 235);
            case 12:
                // Jaune
                return new Color(255, 255, 0);
            case 13:
                // Blanc
                return new Color(255, 255, 255);
            case 14:
                // TODO Bleu pastel
                return Color.BLUE;
            case 15:
                // Orange
                return new Color(255, 165, 0);
            default:
                return Color.BLACK;
        }
    }

    public static Color getStratesColor(int c) {
        if (c < 0 || c > 15) {
            throw new IllegalArgumentException("c doit être compris entre 0 et 15");
        }

        switch (c) {
            case 0:
                // Noir
                return new Color(0, 0, 0);
            case 1:
                // Bleu
                return new Color(0, 0, 255);
            case 2:
                // Bleu vif
                return new Color(0, 0, 139);
            case 3:
                // Rouge
                return new Color(255, 0, 0);
            case 4:
                // Magenta
                return new Color(255, 0, 255);
            case 5:
                // TODO Mauve
                return new Color(212, 115, 212);
            case 6:
                // Rouge vif
                return new Color(139, 0, 0);
            case 7:
                // Violet
                return new Color(128, 0, 128);
            case 8:
                // Magenta vif
                return new Color(139, 0, 139);
            case 9:
                // Vert
                return new Color(0, 128, 0);
            case 10:
                // Turquoise
                return new Color(64, 224, 208);
            case 11:
                // Bleu ciel
                return new Color(135, 206, 235);
            case 12:
                // Jaune
                return new Color(255, 255, 0);
            case 13:
                // Blanc
                return new Color(255, 255, 255);
            case 14:
                // TODO Bleu pastel
                return Color.BLUE;
            case 15:
                // Orange
                return new Color(255, 165, 0);
            default:
                return Color.BLACK;
        }
    }

    public static Color getOmbresColor(int c) {
        if (c < 0 || c > 15) {
            throw new IllegalArgumentException("c doit être compris entre 0 et 15");
        }

        switch (c) {
            case 0:
                // Bleu foncé
                return new Color(50, 57, 175);
            case 1:
                // Bleu plus clair
                return new Color(137, 143, 253);
            case 2:
                // Bleu clair smr
                return new Color(38, 249, 254);
            case 3:
                // Jaune
                return new Color(254, 254, 38);
            case 4:
                // Magenta
                return new Color(255, 0, 255);
            case 5:
                // TODO Mauve
                return new Color(212, 115, 212);
            case 6:
                // Rouge vif
                return new Color(139, 0, 0);
            case 7:
                // Violet
                return new Color(128, 0, 128);
            case 8:
                // Magenta vif
                return new Color(139, 0, 139);
            case 9:
                // Vert
                return new Color(0, 128, 0);
            case 10:
                // Turquoise
                return new Color(64, 224, 208);
            case 11:
                // Bleu ciel
                return new Color(135, 206, 235);
            case 12:
                // Jaune
                return new Color(255, 255, 0);
            case 13:
                // Blanc
                return new Color(255, 255, 255);
            case 14:
                // Gris
                return new Color(218, 218, 210);
            case 15:
                // Blanc
                return new Color(255, 255, 255);
            default:
                return Color.BLACK;
        }
    }
}
