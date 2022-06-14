package net.cnam.fractals;

import net.cnam.fractals.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Fractals {
    private final List<Color> colors = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);

    private FractalsSettings settings;
    private int magicC;
    private boolean calculFractalDone;

    private int moveX;
    private int moveY;
    private int[][] h1;
    private int[] c1;
    private int m;
    private int p;
    private int h2;
    private int d;
    private Random random;
    private int l;
    private int n;
    private int x;
    private int y;
    private int c2;
    private int q;
    private int e;
    private int i;
    private int nm;
    private int nmx;
    private int a;
    private int t;
    private int fh;
    private int o1;
    private int o2;
    private int pv;
    private int xm;
    private int ym;
    private int xj;
    private int yj;
    private int tr;
    private int ds;

    public Fractals(FractalsSettings settings) {
        Color[] seaGradient = Utils.createGradient(new Color(0, 0, 128), new Color(0, 255, 255), 15);
        Color[] sandGradient = Utils.createGradient(new Color(202, 205, 0), new Color(255, 237, 0), 5);
        Color[] grassGradient = Utils.createGradient(new Color(106, 234, 0), new Color(58, 129, 0), 15);
        Color[] mountainGradient = Utils.createGradient(new Color(153, 153, 153), new Color(255, 255, 255), 10);
        colors.addAll(Arrays.asList(seaGradient));
        colors.addAll(Arrays.asList(sandGradient));
        colors.addAll(Arrays.asList(grassGradient));
        colors.addAll(Arrays.asList(mountainGradient));

        setSettings(settings);
    }

    public void setSettings(FractalsSettings settings) {
        semaphore.acquireUninterruptibly();

        this.settings = settings;
        reset();

        semaphore.release();
    }

    private void reset() {
        this.calculFractalDone = false;
        this.moveX = 0;
        this.moveY = 0;
        this.h1 = new int[settings.getTaille() + 1][settings.getTaille() + 1];
        this.magicC = settings.getTaille() * 2;
        this.c1 = new int[magicC + 2];
        this.m = settings.getMaille();
        this.p = (int) Math.pow(2, 7 - m);
        this.h2 = settings.getHauteur();
        this.d = settings.getDeviation();
        this.random = new Random(settings.getGraine());
        this.l = settings.getTaille();
        this.n = h2 / colors.size();
        this.x = 0;
        this.y = 0;
        this.c2 = 0;
        this.q = 0;
        this.e = 0;
        this.i = 0;
        this.nm = 0;
        this.nmx = 0;
        this.a = 0;
        this.t = 0;
        this.fh = 0;
        this.o1 = 0;
        this.o2 = 0;
        this.pv = 0;
        this.xm = 0;
        this.ym = 0;
        this.xj = 0;
        this.yj = 0;
        this.tr = 0;
        this.ds = 0;
    }

    public FractalsSettings getSettings() {
        return settings;
    }

    public Dimension getDimension2D() {
        return new Dimension(settings.getTaille() + 1, settings.getTaille() + 1);
    }

    public Dimension getDimension3D() {
        return new Dimension(settings.getTaille() * 8, settings.getTaille() * 2 + settings.getHauteur());
    }

    private void plot(Graphics2D graphics, int componentHeight, int x, int y, int c) {
        plot(graphics, componentHeight, x, y, colors.get(c));
    }

    private void plot(Graphics2D graphics, int componentHeight, int x, int y, Color color) {
        plot(graphics, componentHeight, x, y, 1, 1, color);
    }

    private void plot(Graphics2D graphics, int componentHeight, int x, int y, int width, int height, int c) {
        plot(graphics, componentHeight, x, y, width, height, colors.get(c));
    }

    private void plot(Graphics2D graphics, int componentHeight, int x, int y, int width, int height, Color color) {
        if (graphics == null) {
            return;
        }

        if (graphics.getColor() != color) {
            graphics.setColor(color);
        }
        graphics.fillRect(x, componentHeight - y, width, height);

        move(x, y);
    }

    private void move(int x, int y) {
        this.moveX = x;
        this.moveY = y;
    }

    private void drawLine(Graphics2D graphics, int componentHeight, int x, int y, int c) {
        drawLine(graphics, componentHeight, x, y, colors.get(c));
    }

    private void drawLine(Graphics2D graphics, int componentHeight, int x, int y, Color color) {
        if (graphics == null) {
            return;
        }

        if (graphics.getColor() != color) {
            graphics.setColor(color);
        }
        graphics.drawLine(moveX, componentHeight - moveY, x, componentHeight - y);

        move(x, y);
    }

    private void drawLine(Graphics2D graphics, int componentHeight, int x, int y, int size, int c) {
        Color color = colors.get(c);
        drawLine(graphics, componentHeight, x, y, size, color);
    }

    private void drawLine(Graphics2D graphics, int componentHeight, int x, int y, int size, Color color) {
        if (size <= 0) {
            return;
        }
        int saveX = moveX;
        int saveY = moveY;
        int saveDestX = 0;
        int saveDestY = 0;
        for (int i = 0; i < size; i++) {
            move(saveX + i, saveY);
            drawLine(graphics, componentHeight, x + i, y, color);
            if (i == 0) {
                saveDestX = moveX;
                saveDestY = moveY;
            }
        }
        move(saveDestX, saveDestY);
    }

    // lignes 290 à 350
    private void surfaceDeBase(Graphics2D graphics, int componentHeight) {
        for (x = 0; x <= l; x += p) {
            for (y = 0; y <= l; y += p) {
                h1[x][y] = (int) (random.nextFloat() * h2);
                if (h1[x][y] < n) {
                    h1[x][y] = n;
                }
                c2 = h1[x][y] / n;
                if (c2 > colors.size() - 1) {
                    c2 = colors.size() - 1;
                }
                plot(graphics, componentHeight, x, y, c2);
            }
        }
    }

    // lignes 360 à 690
    private void calculFractal(Graphics2D graphics, int componentHeight) {
        while (p > 1) {
            q = p / 2;
            e = d / 2;

            // lignes 390 à 450
            for (x = q; x <= l - q; x += p) {
                for (y = q; y <= l - q; y += p) {
                    h2 = (int) ((h1[x - q][y - q] + h1[x - q][y + q] + h1[x + q][y - q] + h1[x + q][y + q]) / 4
                            + d * random.nextFloat() - e);
                    if (h2 < n) {
                        h2 = n;
                    }
                    c2 = h2 / n;
                    if (c2 > colors.size() - 1) {
                        c2 = colors.size() - 1;
                    }
                    h1[x][y] = h2;
                    plot(graphics, componentHeight, x, y, c2);
                }
            }

            // lignes 460 à 560
            for (x = p; x <= l - p; x += p) {
                for (y = q; y <= l - q; y += p) {
                    h2 = (int) ((h1[x - q][y] + h1[x + q][y] + h1[x][y - q] + h1[x][y + q]) / 4 + d * random.nextFloat()
                            - e);
                    if (h2 < n) {
                        h2 = n;
                    }
                    c2 = h2 / n;
                    if (c2 > colors.size() - 1) {
                        c2 = colors.size() - 1;
                    }
                    h1[x][y] = h2;
                    plot(graphics, componentHeight, x, y, c2);

                    h2 = (int) ((h1[y - q][x] + h1[y + q][x] + h1[y][x - q] + h1[y][x + q]) / 4 + d * random.nextFloat()
                            - e);
                    if (h2 < n) {
                        h2 = n;
                    }
                    c2 = h2 / n;
                    if (c2 > colors.size() - 1) {
                        c2 = colors.size() - 1;
                    }
                    h1[y][x] = h2;
                    plot(graphics, componentHeight, y, x, c2);
                }
            }

            // lignes 570 à 660
            for (i = q; i <= l - q; i += p) {
                h2 = (int) ((h1[0][i - q] + h1[0][i + q] + h1[q][i]) / 3 + d * random.nextFloat() - e);
                if (h2 < n) {
                    h2 = n;
                }
                h1[0][i] = h2;

                h2 = (int) ((h1[l][i - q] + h1[l][i + q] + h1[l - q][i]) / 3 + d * random.nextFloat() - e);
                if (h2 < n) {
                    h2 = n;
                }
                h1[l][i] = h2;

                h2 = (int) ((h1[i - q][0] + h1[i + q][0] + h1[i][q]) / 3 + d * random.nextFloat() - e);
                if (h2 < n) {
                    h2 = n;
                }
                h1[i][0] = h2;

                h2 = (int) ((h1[i - q][l] + h1[i + q][l] + h1[i][l - q]) / 3 + d * random.nextFloat() - e);
                if (h2 < n) {
                    h2 = n;
                }
                h1[i][l] = h2;
            }

            p = p / 2;
            d = d / 2;
        }
    }

    // lignes 770 à 840
    private void surface(Graphics2D graphics, int componentHeight) {
        for (y = 0; y <= l; y++) {
            for (x = 0; x <= l; x++) {
                c2 = h1[x][y] / n;
                if (c2 > colors.size() - 1) {
                    c2 = colors.size() - 1;
                }
                plot(graphics, componentHeight, x, y, c2);
            }
        }
    }

    // lignes 980 à 1140
    private void strates(Graphics2D graphics, int componentHeight) {
        nm = n * 4;

        // lignes 1000 à 1040
        for (i = 0; i <= l; i++) {
            h2 = h1[0][i] + i;
            if (h2 < nm + i) {
                h2 = nm + i;
            }
            c1[l - i] = h2 - 2;
            h2 = h1[i][0] + i;
            if (h2 < nm + i) {
                h2 = nm + i;
            }
            c1[l + i] = h2 - 2;
        }

        // lignes 1050 à 1130
        for (y = 0; y <= l; y++) {
            for (x = 0; x < l; x++) {
                nmx = nm + x + y;
                a = l - y + x;
                h2 = h1[x][y] + x + y;
                c2 = h1[x][y] / n;
                if (c2 >= colors.size()) {
                    c2 = colors.size() - 1;
                }
                if (h2 < nmx) {
                    h2 = nmx;
                }
                if (h2 <= c1[a]) {
                    plot(graphics, componentHeight, a * 4, c1[a], 4, 1, c2);
                }
                if (h2 > c1[a]) {
                    move(a * 4, c1[a] + 1);
                    drawLine(graphics, componentHeight, a * 4, h2, 4, c2);
                    c1[a] = h2;
                }
            }
        }
    }

    // lignes 1150 à 1370
    private void ombres(Graphics2D graphics, int componentHeight) {
        // ligne 1180
        for (i = 0; i <= l; i++) {
            c1[l - i] = h1[0][i] + i - 2;
            c1[l + i] = h1[i][0] + i - 2;
        }

        // lignes 1190 à 1300
        for (y = 0; y <= l; y++) {
            o1 = 0;
            o2 = 0;
            for (x = l; x >= 0; x--) {
                a = l - y + x;
                h2 = h1[x][y] + x + y;
                c2 = 3;
                if (h1[x][y] >= o1) {
                    o1 = h1[x][y] + 1;
                } else {
                    c2 = 2;
                }
                if (h1[x][y] >= o2) {
                    o2 = h1[x][y] + 2;
                } else {
                    c2 = 1;
                }

                if (h2 < c1[a]) {
                    c2 += 1;
                }
                Color color;
                if (c2 == 1) {
                    color = new Color(0, 0, 128);
                } else if (c2 == 2) {
                    color = new Color(0, 0, 255);
                } else if (c2 == 3) {
                    color = new Color(0, 128, 255);
                } else {
                    color = new Color(0, 255, 255);
                }

                if (h2 < c1[a]) {
                    plot(graphics, componentHeight, a * 4, c1[a] - 2, 4, 1, color);
                    ombresPrivate();
                    continue;
                }
                move(a * 4, c1[a] - 1);
                drawLine(graphics, componentHeight, a * 4, h2, 4, color);
                c1[a] = h2 + 2;
                ombresPrivate();
            }
        }
    }

    private void ombresPrivate() {
        o1 = o1 - 1;
        o2 = o2 - 2;
    }

    // lignes 850 à 970
    private void filDeFer(Graphics2D graphics, int componentHeight) {
        int o = c1.length / 2;
        int k = 0;
        c1 = new int[magicC + 2];
        for (y = 0; y <= l; y += 2) {
            move(o * 4, c1[o + k]);
            k = 0;
            o = c1.length / 2 - y;
            if (o < 0)
                k = -o;
            for (x = k; x <= l; x += 2) {
                t = h1[x][y] + y + x;
                h2 = Math.max(c1[x + o], t);
                c1[x + o] = h2;
                drawLine(graphics, componentHeight, (o + x) * 4, h2, 1);
            }
            if (y != 0)
                drawLine(graphics, componentHeight, (o + x) * 4 - 2, fh, 1);
            fh = h2;
        }
    }

    private void jeu(Graphics2D graphics, int componentHeight) {
        pv = 100;
        xm = (int) (Math.random() * 20) + 5;
        ym = (int) (Math.random() * 10) + 5;

        // AFFICHAGE CABANE
        plot(graphics, componentHeight, xm * 4, ym * 2, 13);
        move(xm * 4, ym * 2);
        drawLine(graphics, componentHeight, (xm + 2) * 4, ym * 2, 13);
        drawLine(graphics, componentHeight, (xm + 2) * 4, (ym + 2) * 2, 13);
        drawLine(graphics, componentHeight, xm * 4, (ym + 2) * 2, 13);
        drawLine(graphics, componentHeight, xm * 4, ym * 2, 13);

        // PLACE JOUEUR
        xj = (int) (Math.random() * 30) + 10;
        yj = (int) (Math.random() * 10) + 10;
        plot(graphics, componentHeight, xj * 4, yj * 2, 1);
        // faire de 1500 à 1610

        // RESULTATS
        pv = pv - 1;
        tr = (int) (Math.random() * 100);
        if (h1[xj][yj] < 5 && tr < 15) {
            pv = 100;
        }
        // encore un locate en 1550
        if (pv <= 0) {
            // ecrire "VOUS ETES MORT !"
            System.exit(0);
        }
        plot(graphics, componentHeight, xj * 4, yj * 2, 3);
        ds = (int) ((Math.pow(xj - xm, 2)) + (Math.pow(yj - ym, 2)));
        if (ds < 3) {
            // ecrire "SAUVE !"
            System.exit(0);
        }
        // GOTO 1500
    }

    public void newSurface(Graphics2D graphics) {
        semaphore.acquireUninterruptibly();

        int componentHeight = (int) getDimension2D().getHeight();

        reset();
        surfaceDeBase(graphics, componentHeight);
        calculFractal(graphics, componentHeight);
        calculFractalDone = true;

        semaphore.release();
    }

    public void map(Graphics2D graphics) {
        if (!calculFractalDone) {
            newSurface(null);
        }

        semaphore.acquireUninterruptibly();

        surface(graphics, (int) getDimension2D().getHeight());

        semaphore.release();
    }

    public void viewStrates(Graphics2D graphics) {
        if (!calculFractalDone) {
            newSurface(null);
        }

        semaphore.acquireUninterruptibly();

        strates(graphics, (int) getDimension3D().getHeight());

        semaphore.release();
    }

    public void viewOmbres(Graphics2D graphics) {
        if (!calculFractalDone) {
            newSurface(null);
        }

        semaphore.acquireUninterruptibly();

        ombres(graphics, (int) getDimension3D().getHeight());

        semaphore.release();
    }

    public void viewFilDeFer(Graphics2D graphics) {
        if (!calculFractalDone) {
            newSurface(null);
        }

        semaphore.acquireUninterruptibly();

        filDeFer(graphics, (int) getDimension3D().getHeight());

        semaphore.release();
    }

    public void game(Graphics2D graphics) {
        if (!calculFractalDone) {
            newSurface(null);
        }

        semaphore.acquireUninterruptibly();

        jeu(graphics, (int) getDimension3D().getHeight());

        semaphore.release();
    }
}
