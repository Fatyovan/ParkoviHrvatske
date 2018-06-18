package hr.parkovihrvatske.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import hr.parkovihrvatske.enumeration.TicketTypeEnum;


/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkTicketReservation {
    @SerializedName("reservationId")
    private UUID reservationId;
    @SerializedName("ticketId")
    private UUID ticketId;
    @SerializedName("seasonId")
    private UUID seasonId;
    @SerializedName("hash")
    private String hash;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("ticketReservationType")
    private TicketTypeEnum ticketReservationType;
    @SerializedName("cancellationTimestamp")
    private String cancellationTime;

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UUID getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(UUID seasonId) {
        this.seasonId = seasonId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TicketTypeEnum getTicketReservationType() {
        return ticketReservationType;
    }

    public void setTicketReservationType(TicketTypeEnum ticketReservationType) {
        this.ticketReservationType = ticketReservationType;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }
}
