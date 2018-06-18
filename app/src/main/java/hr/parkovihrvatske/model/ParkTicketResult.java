package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Damjan Miloševski on 07.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkTicketResult {
    @SerializedName("result")
    private ParkTicket ticket;
    @SerializedName("status")
    private int status;

    public ParkTicketResult(ParkTicket ticket, int status) {
        this.ticket = ticket;
        this.status = status;
    }

    public ParkTicket getTicket() {
        return ticket;
    }

    public void setTicket(ParkTicket ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
