package it.vaxplan.backend;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
abstract class Vaccine {

    Map<User, Booking> bookings = new HashMap<>();
    //ArrayList<String> sites = new ArrayList<String>();
    //ArrayList<String> dates = new ArrayList<String>();
    int availableSlots = 6;

    /**
     * Determines if a patient is eligible for a given vaccine campaign.
     * @return Truth value for whether a patient is eligible or not
     */
    public abstract boolean isEligible();

    /*public void readBufferSedi(Predicate<String> selector, ArrayList<String> save) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("prenotazioni.txt"));
        String line = reader.readLine();
        while(line != null)
        {
            for(String word: line.split("//W+"))
                if(selector.test(word))
                    save.add(word);

            line = reader.readLine();
        }
    }*/

    /**
     * Getter for number of available slots
     * @return Number of available slots
     */
    /*
    public int getAvailableSlots()
    {
        return availableSlots;
    }

     */
}
