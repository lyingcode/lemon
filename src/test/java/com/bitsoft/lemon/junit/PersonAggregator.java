package com.bitsoft.lemon.junit;

import com.bitsoft.lemon.model.demo.Person;
import com.bitsoft.lemon.model.demo.Types;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        Person person = new Person();
        person.setFirstName(argumentsAccessor.getString(0));
        person.setLastName(argumentsAccessor.getString(1));
        person.setType(argumentsAccessor.get(2, Types.class));
        return person;
    }
}
