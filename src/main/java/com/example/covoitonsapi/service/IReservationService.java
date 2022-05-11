package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ReservationDto;

public interface IReservationService {

    Boolean reserved(ReservationDto dto);
}
