package hr.parkovihrvatske.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import hr.parkovihrvatske.enumeration.EntityTypeEnum;


/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkTicket {
    @SerializedName("ticketId")
    private UUID ticketId;
    @SerializedName("dateFrom")
    private String dateFrom;
    @SerializedName("dateTo")
    private String dateTo;
    @SerializedName("operatorType")
    private EntityTypeEnum operatorType;
    @SerializedName("hash")
    private String hash;
    private UUID seasonId;

    public ParkTicket(UUID ticketId, String dateFrom, String dateTo, EntityTypeEnum operatorType, String hash) {
        this.ticketId = ticketId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.operatorType = operatorType;
        this.hash = hash;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public EntityTypeEnum getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(EntityTypeEnum operatorType) {
        this.operatorType = operatorType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
