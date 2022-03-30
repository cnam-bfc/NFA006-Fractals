package net.cnam;

public class App {
    private MainFrame mainFrame;
    private NewSurfaceFrame newSurfaceFrame;
    private CarteFrame carteFrame;
    private StratesViewFrame stratesViewFrame;
    private OmbresViewFrame ombresViewFrame;

    public App() {
        this.mainFrame = new MainFrame(this);
        this.newSurfaceFrame = new NewSurfaceFrame(this);
        this.carteFrame = new CarteFrame(this);
        this.stratesViewFrame = new StratesViewFrame(this);
        this.ombresViewFrame = new OmbresViewFrame(this);
    }

    public void launchMainWindow() {
        this.mainFrame.setVisible(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public NewSurfaceFrame getNewSurfaceFrame() {
        return newSurfaceFrame;
    }

    public void launchNewSurfaceWindow() {
        this.newSurfaceFrame.setVisible(true);
    }

    public CarteFrame getCarteFrame() {
        return carteFrame;
    }

    public void launchCarteWindow() {
        this.carteFrame.setVisible(true);
    }

    public StratesViewFrame getStratesViewFrame() {
        return stratesViewFrame;
    }

    public void launchStratesViewWindow() {
        this.stratesViewFrame.setVisible(true);
    }

    public OmbresViewFrame getOmbresViewFrame() {
        return ombresViewFrame;
    }

    public void launchOmbresViewWindow() {
        this.ombresViewFrame.setVisible(true);
    }
}
