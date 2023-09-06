package Model;

public class Phones {
    private final String producer;
    private String model;
    private final String designation;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

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
