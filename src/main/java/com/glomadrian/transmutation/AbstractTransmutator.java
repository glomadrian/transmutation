package com.glomadrian.transmutation;

import com.glomadrian.transmutation.core.Mapper;
import com.glomadrian.transmutation.core.annotation.Transmutator;
import com.glomadrian.transmutation.exceptions.MalformedMapperException;
import com.glomadrian.transmutation.exceptions.MapperNotFoundException;
import com.glomadrian.transmutation.repository.rule.ComplexFieldRuleRepositoryImp;
import com.glomadrian.transmutation.repository.rule.GenerateFieldRuleRepositoryImp;
import com.glomadrian.transmutation.repository.rule.RuleRepository;
import com.glomadrian.transmutation.repository.rule.SimpleFieldRuleRepositoryImp;
import com.glomadrian.transmutation.rule.ComplexFieldRule;
import com.glomadrian.transmutation.rule.GenerateFieldRule;
import com.glomadrian.transmutation.rule.SimpleFieldRule;
import com.glomadrian.transmutation.utils.ReflexionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Adrian Garcia Lomas
 */
public abstract class AbstractTransmutator<fromClass, toClass> implements Mapper<fromClass, toClass>{

    private RuleRepository<SimpleFieldRule> simpleFieldRulesRuleRepository;
    private RuleRepository<ComplexFieldRule> complexFieldRulesRuleRepository;
    private RuleRepository<GenerateFieldRule> generateFRuleRepository;
    private List<Field> fromFields;
    private List<Field> toFields;
    private Class fromClass;
    private Class toClass;

    public abstract void rules();

    public AbstractTransmutator(){
        simpleFieldRulesRuleRepository = new SimpleFieldRuleRepositoryImp();
        complexFieldRulesRuleRepository = new ComplexFieldRuleRepositoryImp();
        generateFRuleRepository = new GenerateFieldRuleRepositoryImp();
        assertAnnotation();
        init();
        rules();
    }

    /**
     * Check if subclass implemented has a @Transmutator annotation necessary for MapProcessor
     */
    void assertAnnotation(){
        assert this.getClass().isAnnotationPresent(Transmutator.class) : "The Transmutator should have a @Transmutator annotation";
    }

    /**
     * init mapper, set from and to class types necessary for mapProcessor search
     */
     void init(){
        Transmutator transmutatorAnnnotation = this.getClass().getAnnotation(Transmutator.class);
         fromClass = transmutatorAnnnotation.fromClass();
         toClass = transmutatorAnnnotation.toClass();
     }

    protected void addTransmutatorRule(SimpleFieldRule simpleFieldRule){
        simpleFieldRulesRuleRepository.add(simpleFieldRule);
    }

    protected void addTransmutatorRule(ComplexFieldRule complexFieldRule){
        complexFieldRulesRuleRepository.add(complexFieldRule);
    }

    protected void addTransmutatorRule(GenerateFieldRule<fromClass> generateFieldRule){
        generateFRuleRepository.add(generateFieldRule);

    }

    /**
     * Map the concrete object using the mapping rules
     * @param fromObject
     * @param to
     * @param <T>
     * @return
     * @throws MapperNotFoundException
     */
    @Override
    public <T> T map(fromClass fromObject, Class<T> to){


        try {
            //Create return object
            Object toObject = to.newInstance();

             //Prepare for mapping
            if(fromFields==null || toFields==null){
                fromFields = ReflexionUtils.getInheritedPrivateFields(fromObject.getClass());
                toFields = ReflexionUtils.getInheritedPrivateFields(to);
            }

            //Simple Fields
            if(!simpleFieldRulesRuleRepository.isEmpty()){
                List<SimpleFieldRule> simpleFieldRules = simpleFieldRulesRuleRepository.getAsList();

                for(SimpleFieldRule simpleFieldRule : simpleFieldRules){

                    Field fromField = ReflexionUtils.getFieldByName(simpleFieldRule.getFromField(),fromFields);
                    fromField.setAccessible(true);
                    Object value = fromField.get(fromObject);
                    setValueToField(simpleFieldRule.getToField(),value,toObject);
                }
            }

            //Complex fields
            if(!complexFieldRulesRuleRepository.isEmpty()){

                List<ComplexFieldRule> complexFieldRules = complexFieldRulesRuleRepository.getAsList();

                for(ComplexFieldRule complexFieldRule : complexFieldRules){

                    Field fromField = ReflexionUtils.getFieldByName(complexFieldRule.getFromField(),fromFields);
                    fromField.setAccessible(true);
                    Object value = complexFieldRule.map(fromField.get(fromObject));



                    setValueToField(complexFieldRule.getToField(),value,toObject);

                }
            }

            //Generated fields
            if(!generateFRuleRepository.isEmpty()){
                List<GenerateFieldRule> generateFieldRules = generateFRuleRepository.getAsList();

                for(GenerateFieldRule generateFieldRule : generateFieldRules){
                    Object value = generateFieldRule.map(fromObject);
                    setValueToField(generateFieldRule.getToField(),value,toObject);
                }
            }


        return (T) toObject;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        return null;
    }


    private void setValueToField(String fieldName, Object value, Object toObject) throws IllegalAccessException {

        Field toField = ReflexionUtils.getFieldByName(fieldName,toFields);

        Class fromFieldClass  = value.getClass();

        if(toField==null){
            throw new MalformedMapperException("The field "+fieldName+" in the object "+toObject.getClass().getName()+" doesn't exists");
        }

        Class toFieldClass = toField.getType();

        if(!skip(toFieldClass,fromFieldClass)){
            if (!toFieldClass.equals(fromFieldClass)){
                throw new MalformedMapperException("Mapping error: Incorrect types from" +fromFieldClass.getName()+ " to "+ toFieldClass.getName()+" in "+fieldName);
            }
        }


        toField.setAccessible(true);
        toField.set(toObject,value);
    }


    public Class getFromClass() {
        return fromClass;
    }

    public Class getToClass() {
        return toClass;
    }

    /**
     * Some assertions needs to be skip or they fail
     */
    private boolean skip(Class fromFieldClass, Class toFieldClass){

        if(fromFieldClass.equals(int.class) && toFieldClass.equals(Integer.class)){
            return true;
        }

        if(fromFieldClass.equals(Integer.class) && toFieldClass.equals(int.class)){
            return true;
        }

        return false;
    }
}
