package controllers;

import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.*;
import play.db.jpa.*;
import play.mvc.*;
import views.html.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import play.data.*;
import static views.html.index.render;
import views.html.index;

public class Application extends Controller {
    static Form<Goal> goalForm = Form.form(Goal.class);
    private static GenericDAO dao = new GenericDAOImpl();

    @Transactional
    public static Result index() {
        List<Goal> goals = getDao().findAllByClassName("Goal");
        Collections.sort(goals);
        return ok(views.html.index.render(goals, goalForm));
    }

    @Transactional
    public static Result goals() {
        return redirect(routes.Application.index());
    }

    @Transactional
    public static Result newGoal() throws ParseException {
        DynamicForm form = Form.form().bindFromRequest();

        //String codigo = form.get("codigo");
        String name = form.get("goal");
        String description = form.get("description");
        String date = form.get("date");
        String relevance = form.get("relevance");;

        Goal goal = new Goal(name, description,Integer.parseInt(date), Integer.parseInt(relevance));
        dao.persist(goal);

        List<Goal> goals = getDao().findAllByClassName("Goal");
        Collections.sort(goals);
        return ok(views.html.index.render(goals, goalForm));
    }

    @Transactional
    public static Result deleteGoal(long id) {
        DynamicForm form = Form.form().bindFromRequest();
        dao.removeById(Goal.class, id);
        return redirect(routes.Application.goals());
    }

    @Transactional
    public static Result updateGoal(long id) {
        Goal goal = dao.findByEntityId(Goal.class, id);
        goal.setAchieved(true);
        dao.merge(goal);
        return redirect(routes.Application.goals());
    }

    public static GenericDAO getDao() {
        return dao;
    }

    public static void setDao(GenericDAO dao) {
        Application.dao = dao;
    }
}