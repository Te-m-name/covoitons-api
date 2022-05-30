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

    @Override
    public Boolean exist(Integer id) { return bookingRepository.existsById(id); }

    @Override
    public BookingDto toDto(RidesUsersEntity entity) {

        BookingDto dto = new BookingDto();

        dto.setId(entity.getId_ride());
        dto.setRide_id(entity.getId_ride());
        dto.setUser_id(entity.getId_user());

        return dto;
    }

    @Override
    public Boolean book(BookingDto dto) {

        RidesUsersEntity entity = new RidesUsersEntity();
        //diminuer le nombre de place disponibles
        //Voir la possibiliter de limiter la réservation
        //RideEntity rideEntity = rideRepository.findById(entity.getId_ride()).get();
        //rideEntity.setPlaces();

        entity.setAccepted(null);
        entity.setId_ride(dto.getRide_id());
        entity.setId_user(dto.getUser_id());

        bookingRepository.saveAndFlush(entity);

        return true;
    }

    @Override
    public Boolean canceleBooking(Integer id) {

        RidesUsersEntity entity = bookingRepository.findById(id).get();
        //Rétablir le nombre de place
        bookingRepository.deleteById(entity.getId());
        return true;
    }

    @Override
    public Boolean acceptBooking(Integer id) {

        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        RidesUsersEntity entity = bookingRepository.findById(id).get();

        RideEntity rideEntity = rideRepository.findById(entity.getId_ride()).get();

        Integer idDriver = rideEntity.getUserEntity().getID();

        if (idDriver != currentUser.getID())
            throw  new IllegalStateException("permission denied");

        entity.setAccepted(true);
        bookingRepository.saveAndFlush(entity);

        return true;
    }

    @Override
    public Boolean declineBooking(Integer id) {

        return true;
    }

    @Override
    public List<BookingDto> getBookingOnMyRide(Integer id) {

        List<RidesUsersEntity> bookingOnMyRide = bookingRepository.BookingOnMyRide(id);

        return bookingOnMyRide.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }
}
