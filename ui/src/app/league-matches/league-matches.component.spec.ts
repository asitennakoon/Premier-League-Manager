import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueMatchesComponent } from './league-matches.component';

describe('LeagueMatchesComponent', () => {
  let component: LeagueMatchesComponent;
  let fixture: ComponentFixture<LeagueMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeagueMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeagueMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
