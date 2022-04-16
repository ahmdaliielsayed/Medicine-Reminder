package com.ahmdalii.medicinereminder.medications.repository;

import java.util.List;

public class MedicationsSectionPojo {
    String sectionName;
    List<MedicationsPojo> medsList;

    public MedicationsSectionPojo(String sectionName, List<MedicationsPojo> medPojo) {
        this.sectionName = sectionName;
        this.medsList = medPojo;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<MedicationsPojo> getMedPojo() {
        return medsList;
    }

    public void setMedPojo(List<MedicationsPojo> medPojo) {
        this.medsList = medPojo;
    }
}
