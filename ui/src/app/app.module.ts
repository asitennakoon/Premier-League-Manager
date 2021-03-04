import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  HttpClientXsrfModule,
} from "@angular/common/http";

import { AppComponent } from "./app.component";

import { AppService } from "./app.service";
import { AppHttpInterceptorService } from "./http-interceptor.service";
import { LeagueTableComponent } from "./league-table/league-table.component";
import { LeagueMatchesComponent } from "./league-matches/league-matches.component";
import { DatePipe } from "@angular/common";
import { HomePageComponent } from "./home-page/home-page.component";

const routes: Routes = [
  {
    path: "home",
    component: HomePageComponent,
  },
  {
    path: "league-table",
    component: LeagueTableComponent,
  },
  {
    path: "match-results",
    component: LeagueMatchesComponent,
  },
  {
    path: "**",
    redirectTo: "home",
    pathMatch: "full",
  },
];

@NgModule({
  declarations: [
    AppComponent,
    LeagueTableComponent,
    LeagueMatchesComponent,
    HomePageComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: "Csrf-Token",
      headerName: "Csrf-Token",
    }),
    RouterModule.forRoot(routes),
  ],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService,
    },
    DatePipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
