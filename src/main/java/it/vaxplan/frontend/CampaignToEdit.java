package it.vaxplan.frontend;

import it.vaxplan.backend.VaccineCampaign;

public class CampaignToEdit {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private CampaignToEdit() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Holds the selected vaccine campaign to be edited.
     */
    public static VaccineCampaign campaign;

}
