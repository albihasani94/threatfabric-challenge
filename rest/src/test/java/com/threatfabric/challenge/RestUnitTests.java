package com.threatfabric.challenge;

import com.threatfabric.challenge.api.DetectionController;
import com.threatfabric.challenge.service.api.DetectionService;
import com.threatfabric.challenge.service.api.dto.DetectionReport;
import com.threatfabric.challenge.service.api.dto.Device;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DetectionController.class)
@AutoConfigureWebMvc
public class RestUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetectionService detectionService;

    @Test
    public void registerDetectionReportAndReturnDeviceMock() throws Exception {
        var device = new Device();
        device.setDeviceModel("P20 Pro");
        device.setDeviceType("ANDROID");
        device.setOsVersion("10.0");
        device.setDeviceId(UUID.randomUUID());
        var report = new DetectionReport();
        report.setDevice(device);

        given(detectionService.registerDetectionReport(any(DetectionReport.class))).willReturn(report);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/detections/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(device.getDeviceModel())));
    }

}
