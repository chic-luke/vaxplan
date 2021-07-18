package it.vaxplan.backend;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Keep track of the currently running vaccine campaigns.
 */
public class VaccineCampaignService {

    @Getter
    private static final LinkedList<VaccineCampaign> campaigns = new LinkedList<>();

    /**
     * Add a new vaccine campaign to the list of running vaccine campaigns.
     * @param campaign new campaign
     */
    public static void addCampaign (VaccineCampaign campaign) {
        campaigns.add(campaign);
    }

    /**
     * Remove a vaccine campaign from the list of running vaccine campaigns.
     * @param campaign campaign to be removed
     */
    public static void removeCampaign (VaccineCampaign campaign) {
        campaigns.remove(campaign);
    }

}
