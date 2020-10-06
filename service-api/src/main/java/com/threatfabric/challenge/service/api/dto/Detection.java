package com.threatfabric.challenge.service.api.dto;

import java.util.Objects;
import java.util.UUID;

// TODO: Work on this dto fields
public class Detection {
    private UUID detectionId;
    private String nameOfApp;
    private String typeOfApp;
    private Long time;

    public UUID getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(UUID detectionId) {
        this.detectionId = detectionId;
    }

    public String getNameOfApp() {
        return nameOfApp;
    }

    public void setNameOfApp(String nameOfApp) {
        this.nameOfApp = nameOfApp;
    }

    public String getTypeOfApp() {
        return typeOfApp;
    }

    public void setTypeOfApp(String typeOfApp) {
        this.typeOfApp = typeOfApp;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detection detection = (Detection) o;
        return Objects.equals(detectionId, detection.detectionId) &&
                Objects.equals(nameOfApp, detection.nameOfApp) &&
                Objects.equals(typeOfApp, detection.typeOfApp) &&
                Objects.equals(time, detection.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detectionId, nameOfApp, typeOfApp, time);
    }

    @Override
    public String toString() {
        return "Detection{" +
                "detectionId=" + detectionId +
                ", nameOfApp='" + nameOfApp + '\'' +
                ", typeOfApp='" + typeOfApp + '\'' +
                ", time=" + time +
                '}';
    }
}
