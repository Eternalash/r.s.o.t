package rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Up rule", description = "more than 100,set value to 1", priority = 1)
public class Up100ValueRule{

    @Condition
    public boolean when(@Fact("entity") EntityForRules entity) {
        //my rule conditions
        return entity.getValue() >=100;
    }

    @Action(order = 1)
    public void then(@Fact("entity") EntityForRules entity) throws Exception {
        //my actions
        entity.setValue(1);
        entity.setMesg(entity.getMesg().concat("&match Up100ValueRule"));
    }

    @Action(order = 2)
    public void end() throws Exception {
        //my final actions
    }
}
