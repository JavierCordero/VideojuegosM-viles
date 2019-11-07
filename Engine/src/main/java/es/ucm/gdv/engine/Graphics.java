package es.ucm.gdv.engine;

public interface Graphics {
    public Image newImage(String name);
    public void clear(int Color);
    public void drawImage(Image image, int x, int y, int alpha);
    public void drawImage(Image image, Rect src, int x, int y, int alpha);
    public void drawImage(Image image, Rect src, Rect dest, int alpha);
    public int getWidth();
    public int getHeight();
    public int getPhysicalWidth();
    public int getPhysicalHeight();
}
