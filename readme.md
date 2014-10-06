Transmutation
===============
![alt text](http://img1.wikia.nocookie.net/__cb20100415081430/fma/images/5/58/Human_Transmutation_Circle.png "Transmutation1")

A simple, easy and usable java model mapper


 Howto use
 ---------
___
## Creating Transmutator (Mapper)
___
For a mapping beetewwn two Models you need to crate a transmutator (mapper) by extends AbstractTransmutator<Class from, Class to> and using @Transmutator in the mapper class.

For instance you need to Map from BasicUser to BussinesUser, first create a Mapping class for this case


``` java 
@Transmutator(
        fromClass = User.class,
        toClass = Employee.class
)
public class UserToEmployeeNameTransmutator extends AbstractTransmutator<User,Employee> {
    @Override
    public void rules() {
    //Put your mapping rules here
    }
}

```
___
The rules
---------
___
There are 3 types of rules:
___

### Simple field rule
___
Is the most simple rule, this rule copy de data from the source object field to a destiny object field, both needs to be the same type

``` java 
        addTransmutatorRule(new SimpleFieldRule("name","employeeName"));

```
___

### Complex field rule
___
The complex field rule is use when needs to map some field into other but is not the same type or needs extra work
``` java 
        //Complex field rule
        addTransmutatorRule(new ComplexFieldRule<String,Integer>("bornDate","age") {
            @Override
            public Integer map(String bornDate) {
                String[] dates = bornDate.split("/");
                return 2014 - Integer.parseInt(dates[2]);
            }
        });
```
When create a new ComplexFieldRule the <Type,Type> must be the same type that the source and destiny object, in this example the mapping is from String bornDate to int age

___

### Generated field rule
___
The generate field rule is when need to set a field in destiny object but it not has similar in source object, this field will be generate using source object data or external data during the mapping process
``` java 
        //Generate field rules
        addTransmutatorRule(new GenerateFieldRule<User>("employeeCompleteName") {
            @Override
            public String map(User user) {
                return  user.getName()+" "+user.getSurname();
            }
        });


       addTransmutatorRule(new GenerateFieldRule<User>("job") {
           @Override
           public String map(User user) {
               return jobServiceMock.getJobByName(user.getName());
           }
       });
```

___
 Adding Rules
 ---------
___

When you extends AbstractMapper a rules() method need to be Override, inside this method put all the rules for this mapper


``` java 
   @Override
    public void rules() {
       
         //Simple field rule
        addTransmutatorRule(new SimpleFieldRule("name","employeeName"));

        //Complex field rule
        addTransmutatorRule(new ComplexFieldRule<String,Integer>("bornDate","age") {
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
                return  user.getName()+" "+user.getSurname();
            }
        });


       addTransmutatorRule(new GenerateFieldRule<User>("job") {
           @Override
           public String map(User user) {
               return jobServiceMock.getJobByName(user.getName());
           }
       });
    }
```


___
Using Transmutation
---------

___

``` java 
    Transmutation transmutation = new Transmutation();
    transmutation.addTransmutator(new UserToEmployeeTransmutator());
    //Add more transmutation (mappers) here
    Employee employee = transmutation.map(user, Employee.class);

```

___
#### Example
___


You can see a complete example of use in the test

Developer
---------
___
* Adrián García Lomas <glomadrian@gmail.com>
* Twitter acc - @glomAdrian (https://twitter.com/glomadrian)
___
License
-------
___

    Copyright 2014 Adrián García Lomas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
___