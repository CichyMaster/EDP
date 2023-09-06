package Model;

public class Phones {
    private String producer;
    private String model;
    private String designation;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Phones() {}
    public Phones(String producer, String model, String designation){
        this.producer = producer;
        this.model = model;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return producer+" - "+model+" - "+designation;
    }
}
