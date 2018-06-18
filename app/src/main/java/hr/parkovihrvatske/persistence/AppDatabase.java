package hr.parkovihrvatske.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import hr.parkovihrvatske.R;
import hr.parkovihrvatske.model.ParkSeason;
import hr.parkovihrvatske.model.ParkSeasonPeriod;
import hr.parkovihrvatske.model.ParkTicket;
import hr.parkovihrvatske.model.ParkTicketReservation;

/**
 * Created by Damjan Miloševski on 12.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */

//@Database(entities = {ParkTicket.class, ParkTicketReservation.class, ParkSeason.class, ParkSeasonPeriod.class},
//        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract ParkoviHrvatskeDao parkoviHrvatskeDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "parkovi-hrvatske-db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
