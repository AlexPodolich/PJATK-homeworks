package Project.Dormitory;

import java.io.Serializable;

public class Address implements Serializable{
    private String street, city;
    private int streetNum;

    public Address(String street, int streetNum, String city) {
        setStreet(street);
        setStreetNum(streetNum);
        setCity(city);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("street is required");
        }
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("city is required");
        }
        this.city = city;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        if(streetNum <= 0){
            throw new IllegalArgumentException("streetNum must be positive");
        }
        this.streetNum = streetNum;
    }

    @Override
    public String toString() {
        return "Project.Project.Dormitory.Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", streetNum=" + streetNum +
                '}';
    }
}
