package Project.Resident;

import Project.ObjectPlus;

public class ResidencePermit extends ObjectPlus {
    private int id;
    private String type;
    private String status;

    public ResidencePermit(int id, String type, String status) {
        setId(id);
        setType(type);
        setStatus(status);

        addToExtent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResidencePermit{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
