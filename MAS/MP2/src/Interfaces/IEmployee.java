package Interfaces;

import java.util.List;

public interface IEmployee {
    public List<String> getWorkingPhoneNumbers();

    public void addWorkingPhoneNumber(String phoneNum);

    public void removeWorkingPhoneNumber(String phoneNum);
}
