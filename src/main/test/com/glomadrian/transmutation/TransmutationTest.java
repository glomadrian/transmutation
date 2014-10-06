package com.glomadrian.transmutation;

import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.exceptions.MalformedMapperException;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;
import com.glomadrian.transmutation.mapper.UserToEmployeeTransmutator;
import com.glomadrian.transmutation.mapper.UserToEmployeeNameTransmutatorBroken;
import com.glomadrian.transmutation.mapper.UserToUserComplexTransmutator;
import com.glomadrian.transmutation.model.*;
import com.glomadrian.transmutation.rule.GenerateFieldRule;
import com.glomadrian.transmutation.utils.TestUtils;
import junit.framework.TestCase;

/**
 * @author Adrian Garcia Lomas
 */
public class TransmutationTest extends TestCase {

    private Transmutation transmutation;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        transmutation = new Transmutation();
        transmutation.addTransmutator(new UserToUserComplexTransmutator());
        transmutation.addTransmutator(new UserToEmployeeTransmutator(new JobServiceMock()));
    }


    public void testUserToUserComplexMapperFromTransmutation() throws MapperNotFoundException {

        User user = TestUtils.createUser();

        UserComplex userComplex = transmutation.transmute(user, UserComplex.class);

        assertNotNull(userComplex);
        assertEquals(user.getName(),userComplex.getNombre());
        assertEquals(user.getSurname(),userComplex.getApellido());
        assertEquals(27,userComplex.getEdad());
        assertEquals("Adrián García Lomas",userComplex.getNombreCompleto());
    }


    public void testUserToEmployeeMapping() throws MapperNotFoundException {

        User user = TestUtils.createUser();

        Employee employee = transmutation.transmute(user,Employee.class);

        assertNotNull(employee);
        assertEquals(user.getName(), employee.getEmployeeName());
        assertEquals("Adrián García Lomas",employee.getEmployeeCompleteName());
        assertEquals(27,employee.getAge());
        assertEquals("Developer",employee.getJob());

    }

    public void testMappingWithoutTransmutator(){

        User user = TestUtils.createUser();

        try {
            transmutation.transmute(user, Dummy.class);
        } catch (MapperNotFoundException e) {
            assertTrue(true);
            return;
        }

        fail();
    }

    public void testMappingWithIncorrectToParams() throws MapperNotFoundException {

        User user = TestUtils.createUser();

        Transmutation transmutationB = new Transmutation();
        transmutationB.addTransmutator(new UserToEmployeeNameTransmutatorBroken(new JobServiceMock()));

        try{
            Employee employee = transmutationB.transmute(user,Employee.class);
        } catch (MalformedMapperException e){
            assertEquals("The field employeeNameBroken in the object com.glomadrian.transmutation.model.Employee doesn't exists",e.getMessage());
        }

    }

    public void testMappingWithIncorrectReturnType(){

        User user = TestUtils.createUser();

        Transmutation transmutationB = new Transmutation();
        transmutationB.addTransmutator(new IncorrectUserToEmpoyeeTransmutator());

        try{
            Employee employee = transmutationB.transmute(user,Employee.class);
        } catch (MalformedMapperException e){
            assertEquals("Mapping error: Incorrect types fromjava.lang.Integer to java.lang.String in employeeCompleteName", e.getMessage());
        } catch (MapperNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void testMappingAsserts(){

    }


    @Transmutator(fromClass = User.class, toClass = Employee.class)
    private class IncorrectUserToEmpoyeeTransmutator extends AbstractTransmutator<User,Employee>{

        @Override
        public void rules() {
            //Generate field rules
            addTransmutatorRule(new GenerateFieldRule<User>("employeeCompleteName") {
                @Override
                public Integer map(User user) {
                    return 0;
                }
            });

        }
    }
}
