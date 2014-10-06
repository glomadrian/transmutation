package com.glomadrian.transmutation.mapper;

import com.glomadrian.transmutation.AbstractTransmutator;
import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.model.Employee;
import com.glomadrian.transmutation.model.JobServiceMock;
import com.glomadrian.transmutation.model.User;
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
public class UserToEmployeeNameTransmutatorBroken extends AbstractTransmutator<User,Employee> {

    private JobServiceMock jobServiceMock;

    public UserToEmployeeNameTransmutatorBroken(JobServiceMock jobServiceMock) {
        this.jobServiceMock = jobServiceMock;
    }

    @Override
    public void rules() {

        //Simple field rule
        addTransmutatorRule(new SimpleFieldRule("name", "employeeNameBroken"));

        //Complex field rule
        addTransmutatorRule(new ComplexFieldRule<String, Integer>("bornDateBroken", "age") {
            @Override
            public Integer map(String bornDate) {
                String[] dates = bornDate.split("/");
                return 2014 - Integer.parseInt(dates[2]);
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
