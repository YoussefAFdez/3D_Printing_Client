import java.io.Serializable;
import java.util.Arrays;

public class Model implements Serializable {
    private int id;
    private String name;
    private String author;
    private String url;
    private String fileName;
    private String description;
    private String category;
    private String material;
    private String infill;
    private String resolution;
    private boolean supports;
    private int creator;

    public Model(int id, String name, String author, String url, String fileName, String description, String category, String material, String infill, String resolution, boolean supports, int creator) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.url = url;
        this.fileName = fileName;
        this.description = description;
        this.category = category;
        this.material = material;
        this.infill = infill;
        this.resolution = resolution;
        this.supports = supports;
        this.creator = creator;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getInfill() {
        return infill;
    }

    public void setInfill(String infill) {
        this.infill = infill;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isSupports() {
        return supports;
    }

    public void setSupports(boolean supports) {
        this.supports = supports;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", material='" + material + '\'' +
                ", infill='" + infill + '\'' +
                ", resolution='" + resolution + '\'' +
                ", supports=" + supports +
                ", creator=" + creator +
                '}';
    }
}
