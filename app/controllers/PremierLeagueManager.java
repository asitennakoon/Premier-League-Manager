package controllers;

import models.FootballClub;
import models.Match;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    // Object instance is going to be initiated when getInstance method is called
    // for the first time. Then, that same
    // object will be returned whenever getInstance method is called each time.
    private static PremierLeagueManager instance = null;
    // Two separate ArrayLists were used to store clubs and matches as it was more
    // efficient and less error-prone
    private static final List<FootballClub> clubList = new ArrayList<>();
    private static final List<Match> matchList = new ArrayList<>();

    private PremierLeagueManager() {
    }

    // Implementing Thread-Safe Singleton design pattern using Lazy Initialization.
    // Because of this only one object of
    // this class can be created.
    public static PremierLeagueManager getInstance() {
        if (instance == null) {
            // Below block will be locked by the thread which accesses it first. If another
            // thread tries to access it
            // the same time, it will be sent to the wait state.
            synchronized (PremierLeagueManager.class) {
                if (instance == null) {
                    instance = new PremierLeagueManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void addClub(FootballClub footballClub) {
        // Premier League (which this project is based on) has only 20 clubs playing in a single season.
        if (clubList.size() == 20) {
            System.out.println("Premier League cannot have more than 20 clubs.");
        } else {
            for (FootballClub club : clubList) {
                if (club.getName().equals(footballClub.getName())) {
                    System.out.println(footballClub.getName() + " is already in the system");
                    return;
                }
            }
            clubList.add(footballClub);
            System.out.println(footballClub.getName() + " has been added successfully");
            saveData();
        }
    }

    @Override
    public void deleteClub(String footballClub) {
        if (clubList.isEmpty()) {
            System.out.println("There are no clubs currently in the system");
        } else {
            for (FootballClub club : clubList) {
                if (club.getName().equals(footballClub)) {
                    clubList.remove(club);
                    System.out.println(footballClub + " has been deleted successfully");
                    saveData();
                    return;
                }
            }
            System.out.println("The system could not find a club named " + footballClub);
        }
    }

    @Override
    public void displayStats(String footballClub) {
        for (FootballClub club : clubList) {
            if (club.getName().equals(footballClub)) {
                System.out.println(club);
                return;
            }
        }
        System.out.println("The system could not find a club named " + footballClub);
    }

    @Override
    public void displayTable() {
        if (clubList.isEmpty()) {
            System.out.println("Currently there are no clubs in the system");
        } else {
            System.out.println("Sorted by Points, Goal Difference");
            // Below statement sorts the Club list by points, in descending order
            Collections.sort(clubList);
            System.out.println("------------Premier League Table-------------");
            System.out.println("|      Club      | Points | Goal Difference |");
            for (FootballClub club : clubList) {
                // System.out.print(" ");
                // printf method of PrintStream class is used to display stats of the clubs as a
                // table
                System.out.printf("%-17s %5s %13s %n", club.getName(), club.getPoints(),
                        (club.getGoalsScored() - club.getGoalsReceived()));
            }
            System.out.println();
            System.out.println("Sorted by Goals");
            clubList.sort(new GoalComparator());
            System.out.println("---Premier League Table---");
            System.out.println("|      Club      | Goals |");
            for (FootballClub club : clubList) {
                System.out.printf("%-17s %5s %n", club.getName(), club.getGoalsScored());
            }
            System.out.println();
            System.out.println("Sorted by Wins");
            clubList.sort(new WinComparator());
            System.out.println("---Premier League Table---");
            System.out.println("|       Club      | Wins |");
            for (FootballClub club : clubList) {
                System.out.printf("%-17s %5s %n", club.getName(), club.getWins());
            }
        }
    }

    @Override
    public void addMatch(String home, int homeGoals, String away, int awayGoals, String date) throws ParseException {
        FootballClub homeTeam = null;
        FootballClub awayTeam = null;
        // First, the input team names passed to the method parameters
        // are looped through the arraylist to check whether respective clubs are in the
        // system
        for (FootballClub club : clubList) {
            if (club.getName().equals(home)) {
                homeTeam = club;
            } else if (club.getName().equals(away)) {
                awayTeam = club;
            }
        }
        // If and only if clubs with the input names exists in the arraylist, below
        // block of code will execute.
        if (homeTeam != null && awayTeam != null) {
            // All the stats for the two teams are updated accordingly by below block of
            // code
            homeTeam.setGoalsScored(homeTeam.getGoalsScored() + homeGoals);
            homeTeam.setGoalsReceived(homeTeam.getGoalsReceived() + awayGoals);
            homeTeam.setMatchesPlayed(homeTeam.getMatchesPlayed() + 1);
            awayTeam.setGoalsScored(awayTeam.getGoalsScored() + awayGoals);
            awayTeam.setGoalsReceived(awayTeam.getGoalsReceived() + homeGoals);
            awayTeam.setMatchesPlayed(awayTeam.getMatchesPlayed() + 1);
            if (homeGoals > awayGoals) {
                homeTeam.setWins(homeTeam.getWins() + 1);
                homeTeam.setPoints(homeTeam.getPoints() + 2);
                awayTeam.setLosses(awayTeam.getLosses() + 1);
            } else if (homeGoals < awayGoals) {
                awayTeam.setWins(awayTeam.getWins() + 1);
                awayTeam.setPoints(awayTeam.getPoints() + 2);
                homeTeam.setLosses(homeTeam.getLosses() + 1);
            } else {
                homeTeam.setDraws(homeTeam.getDraws() + 1);
                homeTeam.setPoints(homeTeam.getPoints() + 1);
                awayTeam.setDraws(awayTeam.getDraws() + 1);
                awayTeam.setPoints(awayTeam.getPoints() + 1);
            }
            matchList.add(new Match(homeTeam, homeGoals, awayTeam, awayGoals, date));
            System.out.println(home + " vs. " + away + " match has been successfully added");
            saveData();
        } else {
            System.out.println("The system could not find the requested club");
        }
    }

    public void generateMatch() throws ParseException {
        // Club list's object order is shuffled each time this method gets called. Then,
        // 0th and 1st elements are
        // returned, which means it's going be two random clubs each time.
        if (clubList.size() >= 2) {
            Collections.shuffle(clubList);
            FootballClub homeTeam = clubList.get(0);
            FootballClub awayTeam = clubList.get(1);
            // Random class is used to get a random number between 0 and 10 inclusive. It
            // was assumed by the programmer that
            // 10 goals is the most a club will score in a match.
            Random random = new Random();
            int homeGoals = random.nextInt(10);
            int awayGoals = random.nextInt(10);
            // Finally, all the specific stats for the two random clubs are updated
            // accordingly
            homeTeam.setGoalsScored(homeTeam.getGoalsScored() + homeGoals);
            homeTeam.setGoalsReceived(homeTeam.getGoalsReceived() + awayGoals);
            homeTeam.setMatchesPlayed(homeTeam.getMatchesPlayed() + 1);
            awayTeam.setGoalsScored(awayTeam.getGoalsScored() + awayGoals);
            awayTeam.setGoalsReceived(awayTeam.getGoalsReceived() + homeGoals);
            awayTeam.setMatchesPlayed(awayTeam.getMatchesPlayed() + 1);
            if (homeGoals > awayGoals) {
                homeTeam.setWins(homeTeam.getWins() + 1);
                homeTeam.setPoints(homeTeam.getPoints() + 2);
                awayTeam.setLosses(awayTeam.getLosses() + 1);
            } else if (homeGoals < awayGoals) {
                awayTeam.setWins(awayTeam.getWins() + 1);
                awayTeam.setPoints(awayTeam.getPoints() + 2);
                homeTeam.setLosses(homeTeam.getLosses() + 1);
            } else {
                homeTeam.setDraws(homeTeam.getDraws() + 1);
                homeTeam.setPoints(homeTeam.getPoints() + 1);
                awayTeam.setDraws(awayTeam.getDraws() + 1);
                awayTeam.setPoints(awayTeam.getPoints() + 1);
            }
            // Generated matches is added to the Match list with the date it was generated
            // as the date which it was played
            matchList.add(new Match(homeTeam, homeGoals, awayTeam, awayGoals,
                    new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
            //System.out.println(homeTeam.getName() + " " + homeGoals + " : " + awayGoals + " " + awayTeam.getName());
            //System.out.println("Generated match has been successfully added to the system");
            saveData();
        } else {
            System.out.println("Number of clubs in the system are not sufficient to generate a match");
        }
    }

    public void displayMatches() {
        if (matchList.isEmpty()) {
            System.out.println("Currently there are no matches in the system");
        } else {
            Collections.sort(matchList);
            System.out.println("------------Premier League Matches-------------");
            for (Match match : matchList) {
                System.out.println(match);
            }
        }
    }

    public void searchMatches(String date) {
        boolean found = false;
        for (Match match : matchList) {
            if (new SimpleDateFormat("dd/MM/yyyy").format(match.getDate()).equals(date)) {
                found = true;
                System.out.println(match.getHomeTeam().getName() + " " + match.getHomeGoals() + " : "
                        + match.getAwayGoals() + " " + match.getAwayTeam().getName());
            }
        }
        if (!found) {
            System.out.println("No matches were played in that given date");
        }
    }

    // This method is called at the end of each other method which manipulate the
    // arraylist. Therefore, the data will
    // be persistent even in a case of unexpected runtime exception
    public void saveData() {
        // Below implementation is called try-with-resource. It provides AutoCloseable
        // behavior for the streams.
        try (FileOutputStream fos1 = new FileOutputStream("Clubs.txt");
                ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
                FileOutputStream fos2 = new FileOutputStream("Matches.txt");
                ObjectOutputStream oos2 = new ObjectOutputStream(fos2)) {
            for (FootballClub club : clubList) {
                oos1.writeObject(club);
            }
            for (Match match : matchList) {
                oos2.writeObject(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Auto Save Completed");
    }

    public void loadData() {
        // Club list and the match list are saved to two separate files to avoid loss of
        // data and increase efficiency
        // A boolean flag was used to prevent from EOFException error message from
        // displaying after compiling
        boolean loadingClubs = true;
        try (FileInputStream fis1 = new FileInputStream("Clubs.txt");
                ObjectInputStream ois1 = new ObjectInputStream(fis1)) {
            clubList.clear();
            while (loadingClubs) {
                FootballClub club = (FootballClub) ois1.readObject();
                clubList.add(club);
            }
        } catch (EOFException e) {
            loadingClubs = false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean loadingMatches = true;
        try (FileInputStream fis2 = new FileInputStream("Matches.txt");
                ObjectInputStream ois2 = new ObjectInputStream(fis2)) {
            matchList.clear();
            while (loadingMatches) {
                Match match = (Match) ois2.readObject();
                matchList.add(match);
            }
        } catch (EOFException e) {
            loadingMatches = false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // System.out.println("Loading Completed");
    }

    // Below methods are used to send data to the frond end from the LeagueController.
    public List<FootballClub> clubsByPoints() {
        loadData();
        Collections.sort(clubList);
        return clubList;
    }

    public List<FootballClub> clubsByGoals() {
        loadData();
        clubList.sort(new GoalComparator());
        return clubList;
    }

    public List<FootballClub> clubsByWins() {
        loadData();
        clubList.sort(new WinComparator());
        return clubList;
    }

    public List<Match> allMatches() {
        loadData();
        Collections.sort(matchList);
        return matchList;
    }

    public List<Match> generatedMatch() {
        loadData();
        try {
            generateMatch();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(matchList);
        return matchList;
    }

}
