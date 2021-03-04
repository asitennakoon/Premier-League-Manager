import { Component } from "@angular/core";
import { AppService } from "../app.service";
import { FootballClub } from "../models/FootballClub";

@Component({
  selector: "league-table",
  templateUrl: "./league-table.component.html",
  styleUrls: ["./league-table.component.scss"],
})
export class LeagueTableComponent {
  clubs: FootballClub[] = [];

  constructor(private leagueService: AppService) {
    this.clubsByPoints();
  }

  public clubsByPoints(): void {
    this.leagueService.clubsByPoints().subscribe((data) => {
      console.log("Sorted by Points");
      console.log(data.clubList);
      this.clubs = data.clubList;
    });
  }

  public clubsByGoals(): void {
    this.leagueService.clubsByGoals().subscribe((data) => {
      console.log("Sorted by Goals");
      console.log(data.clubList);
      this.clubs = data.clubList;
    });
  }

  public clubsByWins(): void {
    this.leagueService.clubsByWins().subscribe((data) => {
      console.log("Sorted by Wins");
      console.log(data.clubList);
      this.clubs = data.clubList;
    });
  }
}
