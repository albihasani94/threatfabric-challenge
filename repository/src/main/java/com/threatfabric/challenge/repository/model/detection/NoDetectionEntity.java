package com.threatfabric.challenge.repository.model.detection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("NO_THREATS")
@Table(name = "detection_no_threats")
public class NoDetectionEntity extends DetectionEntity {
}
