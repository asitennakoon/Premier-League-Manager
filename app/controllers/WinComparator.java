package controllers;

import models.FootballClub;

import java.util.Comparator;

public class WinComparator implements Comparator<FootballClub> {
    // This class is created to implement a comparator for FootballClub which helps to sort FootballClub objects
    // with number of wins in descending order
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        return o2.getWins() - o1.getWins();
    }
}
