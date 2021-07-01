package rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EasyRulesTest {
    @Test
    public void test(){
        // create a rules engine
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        // create rules
        Rules rules = new Rules();
        rules.register(new Up100ValueRule());
        rules.register(new Down100ValueRule());
        //
        Facts facts = new Facts();
        EntityForRules entityForRules = new EntityForRules();
        entityForRules.setValue(200);
        facts.put("entity",entityForRules);
        Map<Rule, Boolean>matchRules =  fizzBuzzEngine.check(rules,facts);
        List<Rule> matichRules =
                matchRules.entrySet().stream()
                        .filter(Map.Entry::getValue)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
        assertEquals(entityForRules.getValue(),1);
        entityForRules.setValue(50);
        fizzBuzzEngine.fire(rules,facts);
        assertEquals(entityForRules.getValue(),0);

    }
}
