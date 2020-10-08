package com.threatfabric.challenge.repository.model.device;

import com.threatfabric.challenge.repository.model.detection.DetectionEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "device")
public class DeviceEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    private String model;
    private String osVersion;

    @OneToMany(mappedBy = "device", cascade = CascadeType.PERSIST)
    private List<DetectionEntity> detections = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public List<DetectionEntity> getDetections() {
        return detections;
    }

    public void setDetections(List<DetectionEntity> detections) {
        this.detections = detections;
    }

    public void addDetection(DetectionEntity detectionEntity) {
        this.getDetections().add(detectionEntity);
        detectionEntity.setDevice(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEntity that = (DeviceEntity) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(model, that.model) &&
                Objects.equals(osVersion, that.osVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, model, osVersion);
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
