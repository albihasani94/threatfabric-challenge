package com.threatfabric.challenge.repository.model.detection;

import com.threatfabric.challenge.repository.model.device.DeviceEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detection")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class DetectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long time;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

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

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetectionEntity that = (DetectionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(time, that.time) &&
                Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, device);
    }

    @Override
    public String toString() {
        return "DetectionEntity{" +
                "id=" + id +
                ", time=" + time +
                ", device=" + device +
                '}';
    }
}
