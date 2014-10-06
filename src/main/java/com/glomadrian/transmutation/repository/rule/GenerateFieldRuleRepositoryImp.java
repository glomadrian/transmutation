package com.glomadrian.transmutation.repository.rule;

import com.glomadrian.transmutation.core.repository.exception.ItemNotFoundException;
import com.glomadrian.transmutation.rule.GenerateFieldRule;

import java.util.List;
import java.util.Stack;

/**
 * @author Adrian Garcia Lomas
 */
public class GenerateFieldRuleRepositoryImp implements RuleRepository<GenerateFieldRule> {

    private List<GenerateFieldRule> complrexfieldrulelist;

    public GenerateFieldRuleRepositoryImp() {
        complrexfieldrulelist = new Stack<GenerateFieldRule>();
    }

    @Override
    public void add(GenerateFieldRule item) {
        complrexfieldrulelist.add(item);
    }

    @Override
    public GenerateFieldRule search(String param) throws ItemNotFoundException {

        for(GenerateFieldRule generateFieldRule : complrexfieldrulelist){
            if(generateFieldRule.getFromField().equals(param)){
                return generateFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public GenerateFieldRule search(String fromField, String toField) throws ItemNotFoundException {

        for(GenerateFieldRule generateFieldRule : complrexfieldrulelist){
            if(generateFieldRule.getFromField().equals(fromField) && generateFieldRule.getToField().equals(toField)){
                return generateFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public GenerateFieldRule searchByToField(String toField) throws ItemNotFoundException {
        for(GenerateFieldRule generateFieldRule : complrexfieldrulelist){
            if(generateFieldRule.getToField().equals(toField)){
                return generateFieldRule;
            }
        }

        throw  new ItemNotFoundException();
    }

    @Override
    public List<GenerateFieldRule> getAsList() {
        return complrexfieldrulelist;
    }

    @Override
    public boolean isEmpty() {
        return complrexfieldrulelist.isEmpty();
    }
}
