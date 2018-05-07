package edu.aku.family_hifazat.models;

/**
 * Created by hamza.ahmed on 5/4/2018.
 */

public class HealthSummaryHistoryModel {

    private Subhealthindicator modelHeightOrSystolic;
    private Subhealthindicator modelWeightOrDiastolic;


    public HealthSummaryHistoryModel(Subhealthindicator modelHeightOrSystolic, Subhealthindicator modelWeightOrDiastolic) {
        this.modelHeightOrSystolic = modelHeightOrSystolic;
        this.modelWeightOrDiastolic = modelWeightOrDiastolic;
    }

    public Subhealthindicator getModelHeightOrSystolic() {
        return modelHeightOrSystolic;
    }

    public void setModelHeightOrSystolic(Subhealthindicator modelHeightOrSystolic) {
        this.modelHeightOrSystolic = modelHeightOrSystolic;
    }

    public Subhealthindicator getModelWeightOrDiastolic() {
        return modelWeightOrDiastolic;
    }

    public void setModelWeightOrDiastolic(Subhealthindicator modelWeightOrDiastolic) {
        this.modelWeightOrDiastolic = modelWeightOrDiastolic;
    }
}
