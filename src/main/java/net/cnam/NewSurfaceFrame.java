package net.cnam;

import javax.swing.JFrame;

public class NewSurfaceFrame extends JFrame {
    private NewSurfacePanel panel;

    public NewSurfaceFrame(App app) {
        panel = new NewSurfacePanel(3, 100, 100, 62548759613L, 127);

        // Caractéristiques de la fenêtre
        setTitle("Fractals - Nouvelle surface");
        setSize(1280, 720);
        setLocationRelativeTo(app.getMainFrame());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ajout du panel
        this.add(panel);
    }
}