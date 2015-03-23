import play.*;
import models.Goal;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.db.jpa.JPA;
public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAOImpl();

    public void onStart(Application app) {
        //Logger.info("Aplicação inicializada...");
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Goal goal0 = new Goal("Iniciar Projeto", "Projeto da disciplina de SI", 1, 3);
                dao.persist(goal0);

                Goal goal1 = new Goal("Estudar para SO", "Terceiro estagio", 4, 1);
                dao.persist(goal1);

                Goal goal2 = new Goal("Estudar para Redes", "Ultimo miniteste", 1, 1);
                dao.persist(goal2);

                Goal goal3 = new Goal("Revisar projeto do Embedded", "Deadline chegando", 1, 1);
                dao.persist(goal3);

                dao.flush();
            }
        });
    }

    public void onStop(Application app) {
        //Logger.info("Aplicação desligada...");
    }
}
