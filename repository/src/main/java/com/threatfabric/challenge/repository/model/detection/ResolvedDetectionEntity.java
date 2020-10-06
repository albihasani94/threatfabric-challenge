package com.threatfabric.challenge.repository.model.detection;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("RESOLVED")
@Table(name = "detection_resolved")
public class ResolvedDetectionEntity extends DetectionEntity {

    @OneToOne
    @JoinColumn(name = "earlier_detection", referencedColumnName = "detectionuuid")
    private NewDetectionEntity earlierDetection;

    public NewDetectionEntity getEarlierDetection() {
        return earlierDetection;
    }

    public void setEarlierDetection(NewDetectionEntity earlierDetection) {
        this.earlierDetection = earlierDetection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResolvedDetectionEntity that = (ResolvedDetectionEntity) o;
        return Objects.equals(earlierDetection, that.earlierDetection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), earlierDetection);
    }

    @Override
    public String toString() {
        return "ResolvedDetectionEntity{" +
                "earlierDetection=" + earlierDetection +
                '}';
    }
}
