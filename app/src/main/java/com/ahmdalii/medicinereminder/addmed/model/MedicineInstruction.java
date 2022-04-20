package com.ahmdalii.medicinereminder.addmed.model;

public enum MedicineInstruction {

    BEFORE_EATING("before_eating"),
    WHILE_EATING("while_eating"),
    AFTER_EATING("after_eating");

    private String instruction;

    MedicineInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }
}
