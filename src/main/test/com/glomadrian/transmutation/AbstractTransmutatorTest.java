package com.glomadrian.transmutation;

import com.glomadrian.transmutation.mapper.UserToUserComplexTransmutator;
import com.glomadrian.transmutation.model.User;
import com.glomadrian.transmutation.model.UserComplex;
import com.glomadrian.transmutation.utils.TestUtils;
import junit.framework.TestCase;

/**
 * @author Adrian Garcia Lomas
 */
public class AbstractTransmutatorTest extends TestCase{


    AbstractTransmutator<User,UserComplex> userMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        userMapper = new UserToUserComplexTransmutator();
    }


    public void testMappingUser(){

        User user = TestUtils.createUser();

        UserComplex userComplex = userMapper.map(user,UserComplex.class);

        assertNotNull(userComplex);
        assertEquals(user.getName(),userComplex.getNombre());
        assertEquals(user.getSurname(),userComplex.getApellido());
        assertEquals(27,userComplex.getEdad());
        assertEquals("Adrián García Lomas",userComplex.getNombreCompleto());
    }




}
