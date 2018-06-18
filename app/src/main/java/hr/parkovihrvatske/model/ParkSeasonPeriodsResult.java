package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */

public class ParkSeasonPeriodsResult {
    @SerializedName("result")
    private List<ParkSeasonPeriod> parkSeasonPeriods;
    @SerializedName("status")
    private int status;

    public List<ParkSeasonPeriod> getParkSeasonPeriods() {
        return parkSeasonPeriods;
    }

    public void setParkSeasonPeriods(List<ParkSeasonPeriod> parkSeasonPeriods) {
        this.parkSeasonPeriods = parkSeasonPeriods;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
