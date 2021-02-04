package models;

import java.io.Serializable;

// FootballClub is a sub-class (child) of SportsClub. Therefore, it inherits attributes like name and location from SportsClub
public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {

    private int wins;
    private int losses;
    private int draws;
    private int goalsScored;
    private int goalsReceived;
    private int points;
    private int matchesPlayed;

    public FootballClub() {
    }

    // Constructor for newly started clubs that do not have any statistics
    public FootballClub(String name, String location) {
        setName(name);
        setLocation(location);
    }

    // Constructor for already existing clubs
    public FootballClub(String name, String location, int wins, int losses, int draws, int goalsScored, int goalsReceived, int points, int matchesPlayed) {
        setName(name);
        setLocation(location);
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Override
    //toString method makes the object human readable when printed to the command line
    public String toString() {
        return getName() + "\nLocation: " + getLocation() + "\nWins: " + wins + "\nLosses: " + losses + "\nDraws: " + draws + "\nGoals Scored: " + goalsScored + "\nGoals Received: " + goalsReceived + "\nPoints: " + points + "\nMatches Played: " + matchesPlayed + "\n";
    }

    @Override
    // This method is used to compare clubs with their points, then number of goals scored.
    public int compareTo(FootballClub o) {
        if (o.points == this.points) {
            return (o.goalsScored - o.goalsReceived) - (this.goalsScored - this.goalsReceived);
        } else {
            return o.points - this.points;
        }
    }
}