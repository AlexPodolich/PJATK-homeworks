package Project.Resident;

import Project.ObjectPlus;

public class Document extends ObjectPlus {
    private int id;
    private String name;
    private String content;

    public Document(int id, String name, String content) {
        setId(id);
        setName(name);
        setContent(content);

        addToExtent();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
