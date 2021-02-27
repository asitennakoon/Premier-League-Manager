package view;/*
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ConsoleMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner s = new Scanner(System.in);
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        while (true) {
            try {
                premierLeagueManager.loadData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nWelcome to Premier League Manager");
            System.out.println("Please select an option to proceed...");
            System.out.println("\nPress 0 to quit\n");
            System.out.print("1 - Add a club");
            System.out.println("  2 - Delete a club");
            System.out.print("3 - Display statistics of a club");
            System.out.println("  4 - Display points table");
            System.out.print("5 - Add a match");
            System.out.println("  6 - Open GUI");
            System.out.print("Enter the number for the preferred option: ");
            int option = s.nextInt();
            s.nextLine();
            if (option == 0) {
                try {
                    premierLeagueManager.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if (option == 1) {
                System.out.print("Name: ");
                String name = s.nextLine();
                System.out.print("Location: ");
                String location = s.nextLine();
                System.out.print("Wins: ");
                int wins = s.nextInt();
                System.out.print("Draws: ");
                int draws = s.nextInt();
                System.out.print("Defeats: ");
                int defeats = s.nextInt();
                System.out.print("Number of goals received: ");
                int goalsReceived = s.nextInt();
                System.out.print("Number of goals scored: ");
                int goalsScored = s.nextInt();
                System.out.print("Points: ");
                int points = s.nextInt();
                System.out.print("Number of matches played: ");
                int matchesPlayed = s.nextInt();
                premierLeagueManager.addClub(new FootballClub(name, location, wins, draws, defeats, goalsReceived, goalsScored, points, matchesPlayed));
            } else if (option == 2) {
                System.out.print("Enter name of the club you wish to remove: ");
                String name = s.nextLine();
                premierLeagueManager.deleteClub(name);
            } else if (option == 3) {
                System.out.print("Enter name of the club: ");
                String name = s.nextLine();
                premierLeagueManager.displayStats(name);
            } else if (option == 4) {
                premierLeagueManager.displayTable();
            } else if (option == 5) {
                System.out.print("Name of the proposing team: ");
                String club1 = s.nextLine();
                System.out.print("Goals scored: ");
                int goals1 = s.nextInt();
                s.nextLine();
                System.out.print("Name of the opposing team: ");
                String club2 = s.nextLine();
                System.out.print("Goals scored: ");
                int goals2 = s.nextInt();
                System.out.print("Year: ");
                int y = s.nextInt();
                System.out.print("Month: ");
                int m = s.nextInt();
                System.out.print("Day: ");
                int d = s.nextInt();
                premierLeagueManager.addMatch(club1, goals1, club2, goals2, new Date(y, m, d));
            } else if (option == 6) {
                primaryStage.show();
                break;
            } else {
                System.out.println("Invalid option");
            }
        }

        TableView<FootballClub> tableView = new TableView<>();
        ObservableList<FootballClub> data = FXCollections.observableArrayList(premierLeagueManager.returnList());
        tableView.setLayoutY(37.0);
        tableView.setMaxHeight(500);
        tableView.setMaxWidth(610);

        TableColumn<FootballClub, String> tableColumn1 = new TableColumn<>();
        tableColumn1.setMinWidth(135);
        tableColumn1.setText("Club Name");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<FootballClub, String> tableColumn2 = new TableColumn<>();
        tableColumn2.setMinWidth(143);
        tableColumn2.setText("Location");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<FootballClub, String> tableColumn3 = new TableColumn<>();
        tableColumn3.setMinWidth(142);
        tableColumn3.setText("Points");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("points"));
        TableColumn<FootballClub, String> tableColumn4 = new TableColumn<>();
        tableColumn4.setMinWidth(129);
        tableColumn4.setText("Goals Scored");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        TableColumn<FootballClub, String> tableColumn5 = new TableColumn<>();
        tableColumn5.setMinWidth(59);
        tableColumn5.setText("Wins");
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("wins"));

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5);
        tableView.setItems(data);

        Label label = new Label("Football League Manager");
        label.setLayoutX(18);
        label.setLayoutY(4);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font("SansSerif Bold", FontWeight.BOLD, 20));

        TextField textField = new TextField();
        textField.setPromptText("Club Name");
        textField.setLayoutX(370);
        textField.setLayoutY(6);
        textField.setMinWidth(85);
        textField.setMinHeight(25);

        Button button = new Button("Search");
        button.setLayoutX(530);
        button.setLayoutY(6);
        button.setFont(Font.font("SansSerif Bold", FontWeight.BOLD, 12));

        button.setOnAction(event -> premierLeagueManager.displayStats(textField.getText()));

        AnchorPane root = new AnchorPane();
        root.getChildren().add(tableView);
        root.getChildren().add(label);
        root.getChildren().add(textField);
        root.getChildren().add(button);
        primaryStage.setTitle("Football League Manager");
        primaryStage.setScene(new Scene(root, 610, 438));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/
