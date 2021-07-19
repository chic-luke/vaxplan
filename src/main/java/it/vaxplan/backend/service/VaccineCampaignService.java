package it.vaxplan.backend.service;

import it.vaxplan.backend.VaccineCampaign;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Return true if there are no currently running campaigns
     * @return truth value
     */
    public static boolean isEmpty() {
        return campaigns.isEmpty();
    }

}
