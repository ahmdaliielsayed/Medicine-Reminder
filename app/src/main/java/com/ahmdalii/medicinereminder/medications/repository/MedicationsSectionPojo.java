package com.ahmdalii.medicinereminder.medications.repository;

import java.util.List;

public class MedicationsSectionPojo {
    String sectionName;
    List<MedsPojo> medsList;

    public MedicationsSectionPojo(String sectionName, List<MedsPojo> medPojo) {
        this.sectionName = sectionName;
        this.medsList = medPojo;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<MedsPojo> getMedPojo() {
        return medsList;
    }

    public void setMedPojo(List<MedsPojo> medPojo) {
        this.medsList = medPojo;
    }
}
