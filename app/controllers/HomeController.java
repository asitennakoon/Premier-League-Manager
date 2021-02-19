package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.FootballClub;
import models.Match;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

class LeagueController {
    private List<FootballClub> clubList;
    private List<Match> matchList;

    public LeagueController() {
    }

    public LeagueController(List<FootballClub> clubList) {
        this.clubList = clubList;
    }

    public List<FootballClub> getClubList() {
        return clubList;
    }

    public void setClubList(List<FootballClub> clubList) {
        this.clubList = clubList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}

public class HomeController extends Controller {
    private static final PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    public Result clubsByPoints() {
        JsonNode jsonNode = Json.toJson(new LeagueController(premierLeagueManager.clubsByPoints()));
        return ok(jsonNode).as("application/json");
    }

    public Result clubsByGoals() {
        JsonNode jsonNode = Json.toJson(new LeagueController(premierLeagueManager.clubsByGoals()));
        return ok(jsonNode).as("application/json");
    }

    public Result clubsByWins() {
        JsonNode jsonNode = Json.toJson(new LeagueController(premierLeagueManager.clubsByWins()));
        return ok(jsonNode).as("application/json");
    }

    public Result allMatches() {
        LeagueController leagueController = new LeagueController();
        leagueController.setMatchList(premierLeagueManager.allMatches());
        JsonNode jsonNode = Json.toJson(leagueController);
        return ok(jsonNode).as("application/json");
    }

    public Result generatedMatch() {
        LeagueController leagueController = new LeagueController();
        leagueController.setMatchList(premierLeagueManager.generatedMatch());
        JsonNode jsonNode = Json.toJson(leagueController);
        return ok(jsonNode).as("application/json");
    }
}
