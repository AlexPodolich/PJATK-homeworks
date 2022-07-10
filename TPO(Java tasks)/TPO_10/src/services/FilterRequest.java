package services;

import java.util.Date;

public class FilterRequest {

    public String personSurname;
    public Date personBirthDate;

    public FilterRequest() {
    }

    public FilterRequest(String personSurname, Date personBirthDate) {
        this.personSurname = personSurname;
        this.personBirthDate = personBirthDate;
    }
}
