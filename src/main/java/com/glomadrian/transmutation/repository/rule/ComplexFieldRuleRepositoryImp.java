package com.glomadrian.transmutation.repository.rule;

import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;
import com.glomadrian.transmutation.rule.ComplexFieldRule;

import java.util.List;
import java.util.Stack;

/**
 * @author Adrian Garcia Lomas
 */
public class ComplexFieldRuleRepositoryImp implements RuleRepository<ComplexFieldRule> {

    private List<ComplexFieldRule> complexSimpleFieldRuleList;

    public ComplexFieldRuleRepositoryImp() {
        complexSimpleFieldRuleList = new Stack<ComplexFieldRule>();
    }

    @Override
    public void add(ComplexFieldRule item) {
        complexSimpleFieldRuleList.add(item);
    }

    @Override
    public ComplexFieldRule search(String param) throws ItemNotFoundException {

        for(ComplexFieldRule complexFieldRule : complexSimpleFieldRuleList){
            if(complexFieldRule.getFromField().equals(param)){
                return complexFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public ComplexFieldRule search(String fromField, String toField) throws ItemNotFoundException {

        for(ComplexFieldRule complexFieldRule : complexSimpleFieldRuleList){
            if(complexFieldRule.getFromField().equals(fromField) && complexFieldRule.getToField().equals(toField)){
                return complexFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public ComplexFieldRule searchByToField(String toField) throws ItemNotFoundException {
        for(ComplexFieldRule complexFieldRule : complexSimpleFieldRuleList){
            if(complexFieldRule.getToField().equals(toField)){
                return complexFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public List<ComplexFieldRule> getAsList() {
        return complexSimpleFieldRuleList;
    }

    @Override
    public boolean isEmpty() {
        return complexSimpleFieldRuleList.isEmpty();
    }
}
