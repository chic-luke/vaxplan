package it.vaxplan.backend;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VaccineCampaignTest {

    @Test
    void slotsTest() {
        var startDate = LocalDate.of(2021, 9, 1);
        var endDate = LocalDate.of(2021, 9, 10);
        var dailyStartTime = LocalTime.of(10, 0);
        var dailyEndTime = LocalTime.of(17, 0);

        Set<VaccineSite> sites = new HashSet<>();
        var site = new VaccineSite("Sito esempio");
        sites.add(site);

        var campaign = new VaccineCampaign("Test", Vaccine.FLU, 10, startDate, endDate,
                dailyStartTime, dailyEndTime, sites, new HashSet<>(), new LinkedList<>());

        campaign.bookTimeSlot(LocalDate.of(2021, 9, 4), LocalTime.of(10, 0),  site);

        System.out.println(campaign.getSlotsPerSite());
        System.out.println("BOOKED SLOTS: \n" + campaign.getBookedSlots());
    }

}