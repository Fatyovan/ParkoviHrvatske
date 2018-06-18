package hr.parkovihrvatske.api;

import java.util.List;

import hr.parkovihrvatske.model.ParkSeason;
import hr.parkovihrvatske.model.ParkSeasonPeriod;
import hr.parkovihrvatske.model.ParkSeasonPeriodsResult;
import hr.parkovihrvatske.model.ParkSeasonResult;
import hr.parkovihrvatske.model.ParkSeasonsResult;
import hr.parkovihrvatske.model.ParkTicket;
import hr.parkovihrvatske.model.ParkTicketReservation;
import hr.parkovihrvatske.model.ParkTicketReservationsResult;
import hr.parkovihrvatske.model.ParkTicketsResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public interface ApiService {
    @GET("api/parkseasons")
    Call<ParkSeasonsResult> getParkSeasons();

    @GET("api/parkseasonperiods")
    Call<ParkSeasonPeriodsResult> getParkSeasonPeriods();

    @GET("api/parktickets")
    Call<ParkTicketsResult> getParkTickets();

    @GET("api/parkticketreservations")
    Call<ParkTicketReservationsResult> getParkTicketReservations();

    @GET("api/parkseasons/{id}")
    Call<ParkSeasonResult> getParkSeason(@Path("id") String id);

    @GET("api/parkseasonperiods/{id}")
    Call<ParkSeasonPeriod> getParkSeasonPeriod(@Path("id") String id);

    @GET("api/parktickets/{id}")
    Call<ParkTicket> getParkTicket(@Path("id") String id);

    @GET("api/parkticketreservations/{id}")
    Call<ParkTicketReservation> getParkTicketReservation(@Path("id") String id);

    @POST("api/parkseasons")
    Call<ParkSeason> putParkSeason(@Body ParkSeason parkSeason);

    @POST("api/parkseasonperiods")
    Call<ParkSeasonPeriodsResult> putParkSeasonPeriod(@Body ParkSeasonPeriod parkSeasonPeriod);

    @POST("api/parktickets")
    Call<ParkTicketsResult> putParkTicket(@Body ParkTicket parkTicket);

    @POST("api/parkticketreservations")
    Call<ParkTicketReservationsResult> putParkTicketReservation(@Body ParkTicketReservation parkTicketReservation);

}
