package it.vaxplan.backend.service;

import it.vaxplan.backend.VaccineSite;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class VaccineSiteService {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private VaccineSiteService() {
        throw new IllegalArgumentException("Utility class");
    }

    @Getter
    private static final Set<VaccineSite> sites = new HashSet<>();

    /**
     * Add a new vaccination site to the list of running vaccine campaigns.
     * @param site VaccineSite to add
     */
    public static void addSite (VaccineSite site) {
        sites.add(site);
    }

    /**
     * Remove a vaccination site from the list of running vaccine campaigns.
     * @param site VaccineSite to remove
     */
    public static void removeSite (VaccineSite site) {
        sites.remove(site);
    }

    /**
     * Return true if there are no vaccination sites in the set
     * @return truth value
     */
    public static boolean isEmpty() {
        return sites.isEmpty();
    }

}
