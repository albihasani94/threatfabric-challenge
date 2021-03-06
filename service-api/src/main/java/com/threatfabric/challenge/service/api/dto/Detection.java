package com.threatfabric.challenge.service.api.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.Valid;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "new", value = NewDetection.class),
        @JsonSubTypes.Type(name = "resolved", value = ResolvedDetection.class),
        @JsonSubTypes.Type(name = "no_threats", value = NoDetection.class)
})
public abstract class Detection {
    private Long id;
    @Valid
    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
