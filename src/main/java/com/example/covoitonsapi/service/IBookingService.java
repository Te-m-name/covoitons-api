package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RidesUsersEntity;

import java.util.List;

public interface IBookingService {

    Boolean exist(Integer id);

    BookingDto toDto(RidesUsersEntity entity);

    Integer book(BookingDto dto);

    Boolean canceleBooking(Integer id);

    Integer acceptBooking(Integer id);

    Integer declineBooking(Integer id);

    Boolean verify (Integer id);

    List<BookingDto> getBookingOnMyRide(Integer id);

}
