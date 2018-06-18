package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */

public class ParkTicketReservationsResult {
    @SerializedName("result")
    private List<ParkSeason> seasons;
    @SerializedName("status")
    private int status;

    public List<ParkSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<ParkSeason> seasons) {
        this.seasons = seasons;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
