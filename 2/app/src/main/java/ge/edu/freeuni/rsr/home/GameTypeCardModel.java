package ge.edu.freeuni.rsr.home;

public class GameTypeCardModel {
    private int image;
    private String header;
    private String description;

    public GameTypeCardModel(int image, String header, String description) {
        this.image = image;
        this.header = header;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

}
