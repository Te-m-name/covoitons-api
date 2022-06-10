package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.RidesUsersEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.BookingRepository;
import com.example.covoitonsapi.repository.RideRepository;
import com.example.covoitonsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    private Integer places;

    @Override
    public Boolean exist(Integer id) { return bookingRepository.existsById(id); }

    @Override
    public BookingDto toDto(RidesUsersEntity entity) {

        BookingDto dto = new BookingDto();

        UserEntity user = entity.getUser();

        RideEntity ride = entity.getRide();

        dto.setId(entity.getId());
        dto.setRide_id(ride.getId());
        dto.setUser_id(user.getID());
        dto.setAccepted(entity.getAccepted());
        dto.setDeparture_date(ride.getDeparture_time());
        dto.setArrival_time(ride.getArrivalTime());
        dto.setUserIdentity(user.getFirstname() + " " + user.getLastname());
        dto.setDriverIdentity(ride.getUserEntity().getFirstname()+ " " +ride.getUserEntity().getLastname());

        if (ride.getHome_to_office()){
            dto.setDeparture(ride.getStreet() + " " + ride.getPost_code() + " " + ride.getCity());
            dto.setArrival("Ipipoe");

            return dto;
        } else {
            dto.setDeparture("Ipipoe");
            dto.setArrival(ride.getStreet() + " " + ride.getPost_code() + " " + ride.getCity());
            return dto;
        }
    }

    @Override
    public Integer book(BookingDto dto) {

        RidesUsersEntity entity = new RidesUsersEntity();
        //Voir la possibiliter de limiter la réservation

        RideEntity rideEntity = rideRepository.findById(dto.getRide_id()).get();

        UserEntity userEntity = userRepository.findById(rideEntity.getUserEntity().getID()).get();

        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (rideEntity.getPlaces() == 0)
            throw  new IllegalStateException("No places available");

        if (currentUser == userEntity)
            throw  new IllegalStateException("permission denied, you are the driver");

        entity.setRide(rideEntity);
        places = rideEntity.getPlaces()-1;
        rideEntity.setPlaces(places);

        entity.setAccepted(null);

        entity.setUser(currentUser);

        bookingRepository.saveAndFlush(entity);

        return entity.getId();
    }

    @Override
    public Boolean canceleBooking(Integer id) {

        RidesUsersEntity entity = bookingRepository.findById(id).get();

        RideEntity rideEntity = rideRepository.findById(entity.getRide().getId()).get();

        //Rétablir le nombre de place
        places = rideEntity.getPlaces()+1;
        rideEntity.setPlaces(places);

        bookingRepository.deleteById(entity.getId());
        return true;
    }

    @Override
    public Integer acceptBooking(Integer id) {

        RidesUsersEntity entity = bookingRepository.findById(id).get();

        RideEntity rideEntity = rideRepository.findById(entity.getRide().getId()).get();

        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Integer idDriver = rideEntity.getUserEntity().getID();

        if (idDriver != currentUser.getID())
            throw  new IllegalStateException("permission denied");

        entity.setAccepted(true);
        bookingRepository.saveAndFlush(entity);

        return entity.getId();
    }

    @Override
    public Integer declineBooking(Integer id) {

        RidesUsersEntity entity = bookingRepository.findById(id).get();

        RideEntity rideEntity = rideRepository.findById(entity.getRide().getId()).get();

        Integer idDriver = rideEntity.getUserEntity().getID();

        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (idDriver != currentUser.getID())
            throw  new IllegalStateException("permission denied");

        places = rideEntity.getPlaces()+1;
        rideEntity.setPlaces(places);

        entity.setAccepted(false);
        bookingRepository.saveAndFlush(entity);

        return entity.getId();
    }

    @Override
    public Boolean verify(Integer id) {

        // Méthode pour eviter ma repetition du code
        /*
        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        RidesUsersEntity entity = bookingRepository.findById(id).get();

        RideEntity rideEntity = rideRepository.findById(entity.getRide().getId()).get();

        Integer idDriver = rideEntity.getUserEntity().getID();

        if (idDriver != currentUser.getID())
            throw  new IllegalStateException("permission denied");

        */
        return true;
    }

    @Override
    public List<BookingDto> getBookingOnMyRide(Integer id) {

        List<RidesUsersEntity> bookingOnMyRide = bookingRepository.BookingOnMyRide(id);

        return bookingOnMyRide.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getMyBooking(Integer id) {

        List<RidesUsersEntity> myBookingRequest = bookingRepository.MyBookingRequest(id);

        return myBookingRequest.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }
}
