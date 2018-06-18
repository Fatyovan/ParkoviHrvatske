package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */

public class ParkTicketsResult {
    @SerializedName("result")
    private List<ParkTicket> parkTickets;
    @SerializedName("status")
    private int status;

    public ParkTicketsResult(List<ParkTicket> parkTickets, int status) {
        this.parkTickets = parkTickets;
        this.status = status;
    }
}
