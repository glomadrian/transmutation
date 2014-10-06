package com.glomadrian.transmutation.utils;

import com.glomadrian.transmutation.model.User;

/**
 * @author Adrian Garcia Lomas
 */
public class TestUtils {
    public  static  User createUser(){
        User user = new User();
        user.setName("Adrián");
        user.setSurname("García Lomas");
        user.setBornDate("06/03/1987");
        return user;
    }
}
