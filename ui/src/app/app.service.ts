import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { map } from "rxjs/operators";

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private clubsByPointsUrl = "/api/clubsByPoints";
  private clubsByGoalsUrl = "/api/clubsByGoals";
  private clubsByWinsUrl = "/api/clubsByWins";
  private allMatchesUrl = "/api/allMatches";
  private generatedMatchUrl = "/api/generatedMatch";

  constructor(private http: HttpClient) {}

  /**
   * Makes a http get request to retrieve the welcome message from the backend service.
   */

  public clubsByPoints() {
    return this.http
      .get<any>(this.clubsByPointsUrl)
      .pipe(map((response) => response));
  }

  public clubsByGoals() {
    return this.http
      .get<any>(this.clubsByGoalsUrl)
      .pipe(map((response) => response));
  }

  public clubsByWins() {
    return this.http
      .get<any>(this.clubsByWinsUrl)
      .pipe(map((response) => response));
  }

  public allMatches() {
    return this.http
      .get<any>(this.allMatchesUrl)
      .pipe(map((response) => response));
  }

  public generatedMatch() {
    return this.http
      .get<any>(this.generatedMatchUrl)
      .pipe(map((response) => response));
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  /* public sendData(): Observable<any> {
    return this.http.post(this.dataPostTestUrl, {});
  } */
}
