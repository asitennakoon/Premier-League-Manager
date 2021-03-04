import { DatePipe } from "@angular/common";
import { Component } from "@angular/core";
import { AppService } from "../app.service";
import { Match } from "../models/Match";

@Component({
  selector: "match-results",
  templateUrl: "./league-matches.component.html",
  styleUrls: ["./league-matches.component.css"],
})
export class LeagueMatchesComponent {
  matches: Match[] = [];

  constructor(private leagueService: AppService, private datePipe: DatePipe) {
    this.allMatches();
  }

  public allMatches(): void {
    this.leagueService.allMatches().subscribe((data) => {
      this.matches = data.matchList;
      console.log("All Matches");
      console.log(data.matchList);
    });
  }

  public generatedMatch(): void {
    this.leagueService.generatedMatch().subscribe((data) => {
      this.matches = data.matchList;
      console.log("Generated Match Added");
      console.log(data.matchList);
    });
  }

  public searchedMatch(search: string): void {
    this.leagueService.allMatches().subscribe((data) => {
      this.matches = data.matchList.filter(
        (item) =>
          this.datePipe.transform(item.date, "dd/MM/yyyy") ==
          this.datePipe.transform(search, "dd/MM/yyyy")
      );
      console.log("Searched Matches");
      console.log(
        data.matchList.filter(
          (item) =>
            this.datePipe.transform(item.date, "dd/MM/yyyy") ==
            this.datePipe.transform(search, "dd/MM/yyyy")
        )
      );
    });
  }
}
