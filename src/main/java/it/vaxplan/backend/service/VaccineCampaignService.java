package it.vaxplan.backend.service;

import it.vaxplan.backend.VaccineCampaign;
import lombok.Getter;

import java.util.LinkedList;

/**
 * Keep track of the currently running vaccine campaigns.
 */
public class VaccineCampaignService {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private VaccineCampaignService() {
        throw new IllegalArgumentException("Utility class (global service)");
    }

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

    /**
     * Return true if there are no currently running campaigns
     * @return truth value
     */
    public static boolean isEmpty() {
        return campaigns.isEmpty();
    }

}
