package es.ucm.gdv.engine;

public interface Graphics {
    public Image newImage(String name);
    public void clear(int Color);
    public void drawImage(Image image); //En e futura se ampliará y se definirá de distintas maneras
    public int getWidth();
    public int getHeight();
}
