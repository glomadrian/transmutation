package com.glomadrian.transmutation.utils;

import com.glomadrian.transmutation.model.Address;
import com.glomadrian.transmutation.model.Car;
import com.glomadrian.transmutation.model.User;

/**
 * @author Adrian Garcia Lomas
 */
public class TestUtils {

    public static Address createAddress(){

        Address address = new Address();
        address.setCity("Murcia");
        address.setNumber(2);
        address.setPostalCode(80564);
        address.setStreet("C/ the street");
        return address;

    }
    public static Car createCar(){
        Car car = new Car();
        car.setMake("Ford");
        car.setModel("Mustang GT");
        return car;
    }

    public  static  User createUser(){
        User user = new User();
        user.setName("Adrián");
        user.setSurname("García Lomas");
        user.setBornDate("06/03/1987");
        user.setCar(createCar());
        user.setAddress(createAddress());
        return user;
    }
}
