package Model;

public class Workers {
    private String acronym;
    private String firstName;
    private String lastName;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Workers(){};
    public Workers(String acronym, String firstName, String lastName){
        this.acronym = acronym;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return acronym+" - "+firstName+" - "+lastName;
    }
}
