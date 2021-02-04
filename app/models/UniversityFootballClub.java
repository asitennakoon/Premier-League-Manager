package models;

public class UniversityFootballClub extends FootballClub {
    //UniversityFootballClub and SchoolFootballClub are child classes of FootballClub

    private String universityName;

    public UniversityFootballClub(String name, String location, String universityName) {
        super(name, location);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
