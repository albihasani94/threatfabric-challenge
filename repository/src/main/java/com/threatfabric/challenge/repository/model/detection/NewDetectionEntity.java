package com.threatfabric.challenge.repository.model.detection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@DiscriminatorValue("new")
@Table(name = "detection_new")
public class NewDetectionEntity extends DetectionEntity implements Serializable {

    private static final long serialVersionUID = 5212435962267767785L;

    @Column(name = "detection_uuid")
    private UUID detectionUuid;

    @Column(name = "name_of_app")
    private String nameOfApp;

    @Column(name = "type_of_app")
    private String typeOfApp;

    public UUID getDetectionUuid() {
        return detectionUuid;
    }

    public void setDetectionUuid(UUID detectionUuid) {
        this.detectionUuid = detectionUuid;
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
        return Objects.equals(detectionUuid, that.detectionUuid) &&
                Objects.equals(nameOfApp, that.nameOfApp) &&
                Objects.equals(typeOfApp, that.typeOfApp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), detectionUuid, nameOfApp, typeOfApp);
    }
}
