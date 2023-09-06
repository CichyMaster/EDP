package Model;

public class Repairs {
    private String nrCase;
    private long imei;
    private String model;
    private String acronym;
    private String operator;
    private String status;
    private String admissionDate;

    private String endDate;



    public String getNrCase() {
        return nrCase;
    }

    public void setNrCase(String nrCase) {
        this.nrCase = nrCase;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
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

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public Repairs(){}
    public Repairs(String nrCase, long imei, String model, String acronym, String operator, String status, String admissionDate, String endDate){
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
        return nrCase+" - "+imei+" - "+model+" - "+acronym+" - "+operator+" - "+status+" - "+admissionDate+" - "+endDate;
    }
}
