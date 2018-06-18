package hr.parkovihrvatske.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Damjan Miloševski on 07.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkSeasonPeriodResult {
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private int status;

    public ParkSeasonPeriodResult(String result, int status) {
        this.result = result;
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
