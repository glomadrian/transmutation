package com.glomadrian.transmutation.repository.rule;

import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;
import com.glomadrian.transmutation.rule.SimpleFieldRule;

import java.util.List;
import java.util.Stack;

/**
 * @author Adrian Garcia Lomas
 */
public class SimpleFieldRuleRepositoryImp implements RuleRepository<SimpleFieldRule> {

    private List<SimpleFieldRule> simpleFieldRuleList;

    public SimpleFieldRuleRepositoryImp() {
        simpleFieldRuleList = new Stack<SimpleFieldRule>();
    }

    @Override
    public void add(SimpleFieldRule item) {
        simpleFieldRuleList.add(item);
    }

    @Override
    public SimpleFieldRule search(String param) throws ItemNotFoundException {

        for(SimpleFieldRule simpleFieldRule : simpleFieldRuleList){
            if(simpleFieldRule.getFromField().equals(param)){
                return simpleFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public SimpleFieldRule search(String fromField, String toField) throws ItemNotFoundException {

        for(SimpleFieldRule simpleFieldRule : simpleFieldRuleList){
            if(simpleFieldRule.getFromField().equals(fromField) && simpleFieldRule.getToField().equals(toField)){
                return simpleFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public SimpleFieldRule searchByToField(String toField) throws ItemNotFoundException {

        for(SimpleFieldRule simpleFieldRule : simpleFieldRuleList){
            if(simpleFieldRule.getToField().equals(toField)){
                return simpleFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public List<SimpleFieldRule> getAsList() {
        return simpleFieldRuleList;
    }

    @Override
    public boolean isEmpty() {
        return simpleFieldRuleList.isEmpty();
    }
}
