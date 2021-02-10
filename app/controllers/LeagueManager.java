package controllers;

import models.FootballClub;

import java.text.ParseException;

// This interface can be used to maintain different Football Leagues as well i.e. UniversityFootballLeague
public interface LeagueManager {

    void addClub(FootballClub footballClub);

    void deleteClub(String footballClub);

    void displayStats(String footballClub);

    void displayTable();

    // The date parameter in the addMatch method is type String. It will be parsed to a date object inside the method body.
    void addMatch(String home, int homeGoals, String away, int awayGoals, String date) throws ParseException;
}
