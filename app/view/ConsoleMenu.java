package view;

import controllers.PremierLeagueManager;
import models.FootballClub;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class ConsoleMenu {
    // instance object of the PremierLeagueManager class is assigned to premierLeagueManager variable by below statement
    private static final PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        premierLeagueManager.loadData();
        System.out.println("\nWelcome to Premier League Manager");
        // Console Menu will printed in a loop until user wish to quit
        while (true) {
            System.out.println("\nSelect an option to proceed");
            System.out.println("\nPress 0 to quit\n");
            System.out.printf("%-35s %s %n", "1 => Add a club", "2 => Delete a club");
            System.out.printf("%-35s %s %n", "3 => Display statistics of a club", "4 => Display points table");
            System.out.printf("%-35s %s %n", "5 => Add a match", "6 => Generate a match");
            System.out.printf("%-35s %s %n", "7 => Display match results", "8 => Search matches by date");
            System.out.println("9 => Open GUI");
            // validate method checks whether input is an positive integer. It loops the prompts message until user enters
            // a valid input. Additionally, this prevents from any exceptions from happening during runtime.
            int option = validate(s, "\nEnter option: ");
            if (option == 0) {
                quit();
                break;
            } else if (option == 1) {
                addClub();
            } else if (option == 2) {
                deleteClub();
            } else if (option == 3) {
                displayStats();
            } else if (option == 4) {
                premierLeagueManager.displayTable();
            } else if (option == 5) {
                addMatch();
            } else if (option == 6) {
                generateMatch();
            } else if (option == 7) {
                premierLeagueManager.displayMatches();
            } else if (option == 8) {
                searchMatches();
            } else if (option == 9) {
                run();
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    private static void quit() {
        System.out.println("Enter y to confirm? ");
        String confirm = s.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            premierLeagueManager.saveData();
            System.out.println("Good Bye!");
        }
    }

    private static void addClub() {
        System.out.println("Press Enter with no value to go back to main menu\n");
        System.out.print("Name: ");
        String name = s.nextLine();
        // If the user enters nothing, the menu will return to the main menu. This ensures there will be no blank spaces
        // for object attributes.
        if (name.equals("")) return;
        System.out.print("Location: ");
        String location = s.nextLine();
        if (location.equals("")) return;
        premierLeagueManager.addClub(new FootballClub(name, location));
    }

    private static void deleteClub() {
        System.out.println("Press Enter with no value to go back to main menu\n");
        System.out.print("Enter name of the club you wish to remove: ");
        String name = s.nextLine();
        if (name.equals("")) return;
        System.out.println("Enter y to confirm? ");
        String confirm = s.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            premierLeagueManager.deleteClub(name);
        }
    }

    private static void displayStats() {
        System.out.println("Press Enter with no value to go back to main menu\n");
        System.out.print("Enter name of the club: ");
        String name = s.nextLine();
        if (name.equals("")) return;
        premierLeagueManager.displayStats(name);
    }

    private static void addMatch() {
        System.out.println("Press Enter with no value to go back to main menu\n");
        System.out.print("Home team: ");
        String homeTeam = s.nextLine();
        if (homeTeam.equals("")) return;
        // Since validate method only approves positive integers, there is no chance of invalid values to be assigned to
        // attributes of football clubs.
        int homeGoals = validate(s, "Goals scored: ");
        System.out.print("Away team: ");
        String awayTeam = s.nextLine();
        if (awayTeam.equals("")) return;
        int awayGoals = validate(s, "Goals scored: ");
        // dateValidate method ensures that the input is in valid type as well as in correct format.
        String date = dateValidate(s, "Date (dd/mm/yyyy): ");
        if (date.equals("")) return;
        try {
            premierLeagueManager.addMatch(homeTeam, homeGoals, awayTeam, awayGoals, date);
        } catch (ParseException e) {
            System.out.println("Incorrect date format. Try again.");
        }
    }

    private static void generateMatch() {
        try {
            premierLeagueManager.generateMatch();
        } catch (ParseException e) {
            System.out.println("Incorrect date format");
        }
    }

    private static void searchMatches() {
        System.out.println("Press Enter with no value to go back to main menu\n");
        String date = dateValidate(s, "Enter date (dd/mm/yyyy): ");
        premierLeagueManager.searchMatches(date);
    }

    public static int validate(Scanner s, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // In case of a NumberFormatException, system will prompt the user until integer is input
                int input = Integer.parseInt(s.nextLine());
                // Furthermore, it will loop until the value is positive
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Invalid input. Try Again.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Try Again.");
            }
        }
    }

    public static String dateValidate(Scanner s, String prompt) {
        // Below string is a regular expression pattern. Using Pattern class and its' matches method, dateValidate will
        // loop until user inputs a value that matches the below pattern. The pattern was created to ensure the dd/mm/yyyy
        // format of the date. Moreover, this method prevents from any exception from happening as well.
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        while (true) {
            System.out.print(prompt);
            String input = s.nextLine();
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println("Invalid date format. Try Again.");
        }
    }

    private static void run() {
        System.out.println("This may take a few minutes. Please wait patiently.");
        // This method runs the sbt run command on command line to load the GUI.
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd.exe", "/c", "sbt run");
        builder.directory(new File(System.getProperty("user.dir")));
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert process != null;
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = 0;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert exitCode == 0;
    }

    static class StreamGobbler implements Runnable {
        private final InputStream inputStream;
        private final Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
}




