package Model;

public class Workers {
    private final String acronym;
    private final String firstName;
    private final String lastName;

    public String getAcronym() {
        return acronym;
    }

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
