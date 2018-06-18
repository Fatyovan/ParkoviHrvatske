package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Damjan Miloševski on 07.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkTicketReservationResult {
    @SerializedName("result")
    private ParkTicketResult ticketReservation;
    @SerializedName("status")
    private int status;

    public ParkTicketReservationResult(ParkTicketResult ticketReservation, int status) {
        this.ticketReservation = ticketReservation;
        this.status = status;
    }

    public ParkTicketResult getTicketReservation() {
        return ticketReservation;
    }

    public void setTicketReservation(ParkTicketResult ticketReservation) {
        this.ticketReservation = ticketReservation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
