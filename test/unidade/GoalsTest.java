package unidade;

import models.Goal;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Yuri Cesar on 03/15/2015.
 */
public class GoalsTest {

    @Test
    public void deveOrdenarMetasPorRelevancia() {
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new Goal("goal1", "description", 1, 1));
        goals.add(new Goal("goal2", "description", 1, 2));
        goals.add(new Goal("goal3", "description", 0, 1));
        goals.add(new Goal("goal4", "description", 0, 3));
        goals.add(new Goal("goal5", "description", 2, 1));
        Collections.sort(goals);
        assertThat(goals.get(0).getName()).isEqualTo("goal4");
        assertThat(goals.get(1).getName()).isEqualTo("goal3");
        assertThat(goals.get(2).getName()).isEqualTo("goal2");
        assertThat(goals.get(3).getName()).isEqualTo("goal1");
        assertThat(goals.get(4).getName()).isEqualTo("goal5");
    }
}
