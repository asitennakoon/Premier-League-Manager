package models;

import java.io.Serializable;
import java.util.Objects;

// This was created as an abstract class because in real world, a sports club is always going to be related to just one
// sport i.e. Football Club, Cricket Club, Golf Club, etc. (Just like you refer to a vehicle as Car, Van, etc.)
public abstract class SportsClub implements Serializable {

    private String name;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub sportsClub = (SportsClub) o;
        return Objects.equals(name, sportsClub.name) &&
                Objects.equals(location, sportsClub.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
