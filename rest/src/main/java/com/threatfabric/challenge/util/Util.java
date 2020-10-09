package com.threatfabric.challenge.util;

import org.springframework.http.ResponseEntity;

public class Util {

    private Util() {
    }

    public static ResponseEntity noContentResponse() {
        return ResponseEntity.noContent().build();
    }

}
