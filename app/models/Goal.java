package models;

import javax.persistence.*;

/**
 * Created by Yuri Cesar on 03/15/2015.
 */
@Entity(name = "Goal")
public class Goal implements Comparable{
    @Id
    @SequenceGenerator(name = "GOAL_SEQUENCE", sequenceName = "GOAL_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private int date;
    private String name;
    private String description;
    //1 to 3
    private int priority;
    private boolean achieved;

    public Goal(String name,String description, int date, int priority) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.priority = priority;
        achieved = false;
    }

    public Goal() {

    }

    public int getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAchieved() { return achieved; }

    public void setAchieved(boolean achieved) { this.achieved = achieved; }

    @Override
    public int compareTo(Object o) {
        Goal goal2 = (Goal)o;
        if(date == goal2.getDate()) {
            if(priority > goal2.getPriority()) {
                return -1;
            } else if (priority < goal2.getPriority()) {
                return 1;
            } else {
                return 0;
            }
         }
        return ((Integer) date).compareTo((Integer)goal2.getDate());
    }
}
