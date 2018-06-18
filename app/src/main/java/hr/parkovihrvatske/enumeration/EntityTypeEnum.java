package hr.parkovihrvatske.enumeration;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public enum EntityTypeEnum {
    ParkSeason(1),
    ParkTicket(2),
    ParkTicketReservation(3),
    CinemaSeason(4),
    CinemaTicket(5),
    CinemaTicketReservation(6);
    private final int value;

    EntityTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
