package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkSeasonResult {
    @SerializedName("result")
    private ParkSeason parkSeason;
    @SerializedName("status")
    private int status;

    public ParkSeasonResult(ParkSeason parkSeason, int status) {
        this.parkSeason = parkSeason;
        this.status = status;
    }

    public ParkSeason getParkSeason() {
        return parkSeason;
    }

    public void setParkSeason(ParkSeason parkSeason) {
        this.parkSeason = parkSeason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
