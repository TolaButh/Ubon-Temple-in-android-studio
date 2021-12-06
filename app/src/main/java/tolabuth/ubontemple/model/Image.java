package tolabuth.ubontemple.model;

public class Image {
    private String templeName;
    private String description;
    private String imageName;

    public Image(String description, String imageName) {

        this.description = description;
        this.imageName = imageName;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return templeName;
    }

    public String getTempleName() {
        return templeName;
    }

    public void setTempleName(String templeName) {
        this.templeName = templeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
