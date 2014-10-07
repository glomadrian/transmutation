package com.glomadrian.transmutation.mapper;

import com.glomadrian.transmutation.AbstractTransmutator;
import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;
import com.glomadrian.transmutation.model.*;
import com.glomadrian.transmutation.rule.ComplexFieldRule;
import com.glomadrian.transmutation.rule.GenerateFieldRule;
import com.glomadrian.transmutation.rule.SimpleFieldRule;

/**
 * @author Adrian Garcia Lomas
 */
@Transmutator(
        fromClass = User.class,
        toClass = Employee.class
)
public class UserToEmployeeTransmutator extends AbstractTransmutator<User,Employee> {

    private JobServiceMock jobServiceMock;

    public UserToEmployeeTransmutator(JobServiceMock jobServiceMock) {
        this.jobServiceMock = jobServiceMock;
    }

    @Override
    public void rules() {

        //Simple field rule
        addTransmutatorRule(new SimpleFieldRule("name", "employeeName"));

        //Simple field rule (Custom object Car)
        addTransmutatorRule(new SimpleFieldRule("car","car"));

        //Complex field rule
        addTransmutatorRule(new ComplexFieldRule<String, Integer>("bornDate", "age") {
            @Override
            public Integer map(String bornDate) {
                String[] dates = bornDate.split("/");
                return 2014 - Integer.parseInt(dates[2]);
            }
        });

        //Complex field rule
        addTransmutatorRule(new ComplexFieldRule<Address,Direction>("address","direction") {
            @Override
            public Direction map(Address address) {

               //Use transmutator for mapping
                try {
                    return getTransmutation().transmute(address,Direction.class);
                } catch (MapperNotFoundException e) {
                    return new Direction();
                }
            }
        });

        //Generate field rules
        addTransmutatorRule(new GenerateFieldRule<User>("employeeCompleteName") {
            @Override
            public String map(User user) {
                return user.getName() + " " + user.getSurname();
            }
        });


       addTransmutatorRule(new GenerateFieldRule<User>("job") {
           @Override
           public String map(User user) {
               return jobServiceMock.getJobByName(user.getName());
           }
       });
    }
}
