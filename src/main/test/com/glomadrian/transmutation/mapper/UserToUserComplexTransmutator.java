package com.glomadrian.transmutation.mapper;

import com.glomadrian.transmutation.AbstractTransmutator;
import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.model.User;
import com.glomadrian.transmutation.model.UserComplex;
import com.glomadrian.transmutation.rule.ComplexFieldRule;
import com.glomadrian.transmutation.rule.GenerateFieldRule;
import com.glomadrian.transmutation.rule.SimpleFieldRule;

/**
 * @author Adrian Garcia Lomas
 */
@Transmutator(
        fromClass = User.class,
        toClass = UserComplex.class
)
public class UserToUserComplexTransmutator extends AbstractTransmutator<User,UserComplex> {

    @Override
    public void rules() {
        addTransmutatorRule(new SimpleFieldRule("name", "nombre"));
        addTransmutatorRule(new SimpleFieldRule("surname", "apellido"));

        addTransmutatorRule(new ComplexFieldRule<String, Integer>("bornDate", "edad") {
            @Override
            public Integer map(String bornDate) {
                String[] dates = bornDate.split("/");
                return 2014 - Integer.parseInt(dates[2]);
            }
        });

        addTransmutatorRule(new GenerateFieldRule<User>("nombreCompleto") {
            @Override
            public Object map(User user) {
                return user.getName() + " " + user.getSurname();
            }
        });
    }
}
