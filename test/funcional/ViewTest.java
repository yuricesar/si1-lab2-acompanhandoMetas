package funcional;

import base.AbstractTest;
import models.Goal;
import org.junit.Test;
import play.data.Form;
import play.twirl.api.Html;
import views.html.index;

import java.util.List;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Created by Yuri Cesar on 03/15/2015.
 */
public class ViewTest extends AbstractTest {

    @Test
    public void deveAdicionarMetaNaView() {
        Goal  goal = new Goal("My Goal", "Example Goal", 0, 1);
        Form<Goal> goalForm = Form.form(Goal.class);
        dao.persist(goal);
        List<Goal> goals  = dao.findAllByClassName(Goal.class.getName());
        Html html = index.render(goals, goalForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("My Goal");
    }

    @Test
    public void deveRemoverMetaDaView() {
        Goal  goal = new Goal("My Goal", "Example Goal", 0, 1);
        Form<Goal> goalForm = Form.form(Goal.class);
        dao.persist(goal);
        List<Goal> goals  = dao.findAllByClassName(Goal.class.getName());
        Html html = index.render(goals, goalForm);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("My Goal");

        long id = goal.getId();
        dao.removeById(Goal.class, id);
        goals  = dao.findAllByClassName(Goal.class.getName());
        html = index.render(goals, goalForm);
        assertThat(contentAsString(html)).doesNotContain("My Goal");

    }

    }
