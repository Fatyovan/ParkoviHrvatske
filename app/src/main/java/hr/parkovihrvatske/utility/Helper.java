package hr.parkovihrvatske.utility;

import java.util.ArrayList;
import java.util.List;

import hr.parkovihrvatske.R;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class Helper {
    public static final int SPLASH_DURATION = 1000; //1 second
    public static final int REQUEST_CODE_QR_SCAN = 100;
    public static final int PERMISSION_READ_EXTERNAL_STORAGE = 101;
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 102;
    public static final int PERMISSION_CAMERA = 103;
    public static int[] PARKS_OF_CROATIA = {
            R.drawable.biokovo,
            R.drawable.briuni,
            R.drawable.kopacki_rit,
            R.drawable.kornati,
            R.drawable.krka,
            R.drawable.lastovo,
            R.drawable.lonjsko,
            R.drawable.medvednica,
            R.drawable.mljet,
            R.drawable.paklenica,
            R.drawable.papuk,
            R.drawable.plitvice,
            R.drawable.risnjak,
            R.drawable.telascica,
            R.drawable.ucka,
            R.drawable.velebit,
            R.drawable.vransko_jezero,
            R.drawable.zumberak_samobor
    };
    public static String[] TIMEOUT = {
            "10 seconds",
            "30 seconds",
            "1 minute",
            "5 minutes",
            "30 minutes",
            "Custom"
    };
    public static List<String> PARKS = new ArrayList<String>() {{
        add("Lonjsko polje");
        add("Paklenica");
        add("Mljet");
        add("Krka");
        add("Velebit");
        add("Brijuni");
        add("Učka");
        add("Risnjak");
        add("Biokovo");
        add("Plitvička jezera");
        add("Telašćica");
        add("Lastovsko Otočje");
        add("Kopački rit");
        add("Medvednica");
        add("Kornati");
        add("Sjeverni Velebit");
        add("Papuk");
        add("Žumberak-Samoborsko gorje");
        add("Vransko jezero");
    }};
}
