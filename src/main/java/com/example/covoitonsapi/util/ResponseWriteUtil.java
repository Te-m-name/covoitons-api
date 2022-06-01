package com.example.covoitonsapi.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.example.covoitonsapi.dto.ResponseStatusDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class ResponseWriteUtil {

    public static void writeErrorResponse(HttpServletResponse response, String message) {
        ResponseStatusDto status = new ResponseStatusDto();
        status.setStatusCode(ResponseStatusDto.StatusCode.ERROR);
        status.setMessage(message);

        try (PrintWriter writer = response.getWriter()) {
            String json = new ObjectMapper().writeValueAsString(status);

            writer.write(json);
            writer.flush();
        } catch (IOException ie) {
            log.error("Problem writing output to response!", ie);
        }
    }
}
