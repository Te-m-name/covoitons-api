package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RidesUsersEntity;

import java.util.List;

public interface IBookingService {

    Boolean exist(Integer id);

    BookingDto toDto(RidesUsersEntity entity);

    Boolean book(BookingDto dto);

    Boolean canceleBooking(Integer id);

    Boolean acceptBooking(Integer id);

    Boolean declineBooking(Integer id);

    List<BookingDto> getBookingOnMyRide(Integer id);

}
