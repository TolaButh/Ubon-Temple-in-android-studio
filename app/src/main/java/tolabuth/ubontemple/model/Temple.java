package tolabuth.ubontemple.model;

public class Temple {
    private int id;
    private String name;
    private String city;
    private String image;

    public Temple(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Temple() {
    }

    public Temple(int id, String name, String city, String image) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.image = image;
    }

    public Temple(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }


}
