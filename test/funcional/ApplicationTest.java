package funcional;

import java.util.*;

import base.AbstractTest;
import com.fasterxml.jackson.databind.JsonNode;
import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import org.junit.*;

import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.db.jpa.Transactional;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import scala.Option;

import javax.persistence.EntityManager;

import static java.util.Collection.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest extends AbstractTest{

    @Test
    public void deveIniciarCom4Metas() {
        List<Goal> goals = dao.findAllByClassName("Goal");
        assertThat(goals.size()).isEqualTo(4);
    }

    @Test
    public void deveSalvarMeta() {
        Goal goal = new Goal("Goal","Description",0, 1);
        dao.persist(goal);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(5);
        Goal goal2 = dao.findByEntityId(Goal.class, goal.getId());
        assertThat(goal2.getName()).isEqualTo("Goal");
    }

    @Test
    public void deveRemoverMeta() {
        Goal goal = new Goal("Goal","Description", 0, 1);
        dao.persist(goal);
        long id = goal.getId();
        dao.removeById(Goal.class, id);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(4);
    }

    @Test
    public void deveTrocarMeta() {
        Goal goal = new Goal("Goal","Description", 0, 1);
        dao.persist(goal);
        goal.setAchieved(true);
        dao.merge(goal);
        List<Goal> goals = dao.findAllByClassName(Goal.class.getName());
        assertThat(goals.size()).isEqualTo(5);
        Goal goal2 = dao.findByEntityId(Goal.class, goal.getId());
        assertThat(goal2.getAchieved()).isEqualTo(true);
    }
}
