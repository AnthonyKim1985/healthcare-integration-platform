package org.bigdatacenter.healthcareintegrationplatform.domain.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkFlowRequest implements Serializable {
    private Integer dataSetUID;
    private ScenarioTask scenarioTask;
}
