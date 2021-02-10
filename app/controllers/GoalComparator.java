package controllers;

import models.FootballClub;

import java.util.Comparator;

public class GoalComparator implements Comparator<FootballClub> {
    // This class is created to implement a comparator for FootballClub which helps to sort FootballClub objects
    // with number of goals scored in descending order
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        return o2.getGoalsScored() - o1.getGoalsScored();
    }
}
