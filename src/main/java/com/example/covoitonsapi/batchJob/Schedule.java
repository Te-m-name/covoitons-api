package com.example.covoitonsapi.batchJob;

import com.example.covoitonsapi.entity.RecurrentRideEntity;
import com.example.covoitonsapi.repository.RecurrentRideRepository;
import com.example.covoitonsapi.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class Schedule {

    @Autowired
    private RecurrentRideRepository recurrentRideRepository;

    @Autowired
    private RideService rideService;

    @Scheduled( initialDelay = 60 * 1000, fixedDelay = 300 * 1000)
    public void GenerateRecurrentRide() {

        System.out.println("génération des trajets récurrents");

        LocalDateTime dateMax = LocalDateTime.now().plusDays(45);
        List<RecurrentRideEntity> rides = recurrentRideRepository.selectRecurrentsRides();

        for (RecurrentRideEntity ride : rides) {

            ChronoLocalDateTime dateEnd;

            if(ride.getEnd_date()!= null){
                dateEnd = LocalDateTime.from(ride.getEnd_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            }else {
                dateEnd = dateMax;
            }

            while (ride.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(ride.getDelais().getDelais()).isBefore(LocalDateTime.from(dateMax)) &&
                   ride.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(ride.getDelais().getDelais()).isBefore(dateEnd)){

                LocalDateTime date = ride.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(ride.getDelais().getDelais());
                ride.setDate(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
                ride.setDate(rideService.generateRecurrentRide(ride));

                if(ride.getEnd_date()!=null){
                    if(ride.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(ride.getDelais().getDelais()).isAfter(dateEnd)){
                        ride.setEnable(false);
                    }
                }
            }
        }
        recurrentRideRepository.saveAllAndFlush(rides);
    }
}
