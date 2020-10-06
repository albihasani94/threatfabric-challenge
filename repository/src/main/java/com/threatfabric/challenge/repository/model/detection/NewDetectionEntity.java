package com.threatfabric.challenge.repository.model.detection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@DiscriminatorValue("NEW")
@Table(name = "detection_new")
public class NewDetectionEntity extends DetectionEntity {

    private UUID detectionUUID;
    private String nameOfApp;
    private String typeOfApp;

    public UUID getDetectionUUID() {
        return detectionUUID;
    }

    public void setDetectionUUID(UUID detectionUUID) {
        this.detectionUUID = detectionUUID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewDetectionEntity that = (NewDetectionEntity) o;
        return Objects.equals(detectionUUID, that.detectionUUID) &&
                Objects.equals(nameOfApp, that.nameOfApp) &&
                Objects.equals(typeOfApp, that.typeOfApp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), detectionUUID, nameOfApp, typeOfApp);
    }

    @Override
    public String toString() {
        return "NewDetectionEntity{" +
                "detectionUUID=" + detectionUUID +
                ", nameOfApp='" + nameOfApp + '\'' +
                ", typeOfApp='" + typeOfApp + '\'' +
                ", resolvedDetection=" +
                '}';
    }
}
