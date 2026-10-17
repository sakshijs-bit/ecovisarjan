package ecovisarjan.model;

public class Idol {

    private String idolId;
    private String devoteeName;
    private String mobile;
    private String material;
    private String size;
    private String location;
    private String visarjanDate;
    private String status;

    public Idol() {
    }

    public Idol(String idolId, String devoteeName, String mobile,
                String material, String size, String location,
                String visarjanDate, String status) {

        this.idolId = idolId;
        this.devoteeName = devoteeName;
        this.mobile = mobile;
        this.material = material;
        this.size = size;
        this.location = location;
        this.visarjanDate = visarjanDate;
        this.status = status;
    }

    public String getIdolId() {
        return idolId;
    }

    public String getDevoteeName() {
        return devoteeName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMaterial() {
        return material;
    }

    public String getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }

    public String getVisarjanDate() {
        return visarjanDate;
    }

    public String getStatus() {
        return status;
    }
}