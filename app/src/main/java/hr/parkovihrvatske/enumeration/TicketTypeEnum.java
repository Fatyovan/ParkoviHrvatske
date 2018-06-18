package hr.parkovihrvatske.enumeration;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public enum TicketTypeEnum {
    SimpleTicket(1),
    MultipleTicket(2),
    SeasonTicket(3);
    private final int value;

    TicketTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
