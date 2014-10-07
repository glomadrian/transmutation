package com.glomadrian.transmutation.mapper;

import com.glomadrian.transmutation.AbstractTransmutator;
import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.model.Address;
import com.glomadrian.transmutation.model.Direction;
import com.glomadrian.transmutation.rule.GenerateFieldRule;
import com.glomadrian.transmutation.rule.SimpleFieldRule;

/**
 * @author Adrian Garcia Lomas
 */
@Transmutator(fromClass = Address.class, toClass = Direction.class)
public class AddressToDirectionTransmutator extends AbstractTransmutator<Address,Direction> {
    @Override
    public void rules() {

        addTransmutatorRule(new SimpleFieldRule("city","city"));

        addTransmutatorRule(new GenerateFieldRule<Address>("direction") {
            @Override
            public Object map(Address address) {
                return address.getStreet()+" "+address.getNumber()+" "+address.getPostalCode();
            }
        });



    }
}
