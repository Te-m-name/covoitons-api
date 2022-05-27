package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.BookingDto;

public interface IBookingService {

    Boolean reserved(BookingDto dto);

    Boolean canceled(BookingDto dto);
}
