package com.glomadrian.transmutation.reflection;

import com.glomadrian.transmutation.model.User;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.List;

import static com.glomadrian.transmutation.utils.ReflexionUtils.getInheritedPrivateFields;

/**
 * @author Adrian Garcia Lomas
 */
public class ReflectionTest extends TestCase {


   public void testIfCanGetAllVars(){

        User user = new User();

        List<Field> fields = getInheritedPrivateFields(user.getClass());

       for(Field field : fields){
           System.out.println(field.getName());
       }

        assertEquals(3,fields.size());
    }


    public void testCreateUserUsingReflection() throws IllegalAccessException, InstantiationException {

        Object object = User.class.newInstance();

        List<Field> fields = getInheritedPrivateFields(object.getClass());


        for(Field field : fields){
            field.setAccessible(true);
            field.set(object,"adrian");
        }

        assertNotNull(object);


    }






}
