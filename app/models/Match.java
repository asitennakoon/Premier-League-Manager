package models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Use non-deprecated methods for date in your own implementation
public class Match implements Comparable<Match>, Serializable {

    private FootballClub homeTeam;
    private int homeGoals;
    private FootballClub awayTeam;
    private int awayGoals;
    private Date date;

    public Match(FootballClub homeTeam, int homeGoals, FootballClub awayTeam, int awayGoals, String date) throws ParseException {
        this.homeTeam = homeTeam;
        this.homeGoals = homeGoals;
        this.awayTeam = awayTeam;
        this.awayGoals = awayGoals;
        // The date parameter in the constructor is type String. The below statement parse it to a date object using
        // SimpleDateFormat class. This approach reduces code redundancy.
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    public FootballClub getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FootballClub homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public FootballClub getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FootballClub awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        // Again SimpleDateFormat class was used to return the date object as a String,
        // displaying it with a better date format.
        return homeTeam.getName() + " " + homeGoals + " : " + awayGoals + " " + awayTeam.getName() + " (" + new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(date) + ")\n";
    }

    @Override
    // Two matches are compared with its' played year, month and date, in that order.
    public int compareTo(Match o) {
        return this.date.compareTo(o.date);
    }
}
