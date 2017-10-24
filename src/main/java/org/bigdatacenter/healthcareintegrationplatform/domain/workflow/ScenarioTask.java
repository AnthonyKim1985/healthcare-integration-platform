package org.bigdatacenter.healthcareintegrationplatform.domain.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioTask implements Serializable {
    private List<String> queries;
}
