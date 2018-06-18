package hr.parkovihrvatske.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;


/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkSeason {
    @SerializedName("seasonId")
    private UUID seasonId;
    @SerializedName("dateFrom")
    private String dateFrom;
    @SerializedName("dateTo")
    private String dateTo;
    @SerializedName("seasonQuantity")
    private int seasonQuantity;//number of tickets per season

    public UUID getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(UUID seasonId) {
        this.seasonId = seasonId;
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

    public int getSeasonQuantity() {
        return seasonQuantity;
    }

    public void setSeasonQuantity(int seasonQuantity) {
        this.seasonQuantity = seasonQuantity;
    }
}
