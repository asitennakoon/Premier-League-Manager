package models;

public class SchoolFootballClub extends FootballClub {

    private String schoolName;

    public SchoolFootballClub(String name, String location, String schoolName) {
        super(name, location);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
