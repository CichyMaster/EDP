package Model;

public class Repairs {
    private final String nrCase;
    private final long imei;
    private String model;
    private final String acronym;
    private final String operator;
    private final String status;
    private final String admissionDate;

    private final String endDate;


    public String getNrCase() {
        return nrCase;
    }

    public long getImei() {
        return imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getOperator() {
        return operator;
    }

    public String getStatus() {
        return status;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Repairs(String nrCase, long imei, String model, String acronym, String operator, String status, String admissionDate, String endDate) {
        this.nrCase = nrCase;
        this.imei = imei;
        this.model = model;
        this.acronym = acronym;
        this.operator = operator;
        this.status = status;
        this.admissionDate = admissionDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return nrCase + " - " + imei + " - " + model + " - " + acronym + " - " + operator + " - " + status + " - " + admissionDate + " - " + endDate;
    }
}
