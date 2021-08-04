package it.vaxplan.backend;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class Patient {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "/^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04L Q][1-9 MNP-V]|[15 MR][\\dLMNP-V]|[26 NS][0-8L MNP-U])|[DHPS][37 PT][0L]|[ACELMRT][37 PT][01L M]|[AC-EHLMPR-T][26 NS][9 V])|(?:[02468L NQSU][048L QU]|[13579 MPRTV][26 NS])B[26 NS][9 V])(?:[A-MZ][1-9 MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9 MNP-V][\\dLMNP-V]|[0L][1-9 MNP-V]))[A-Z]$/i")
    private String fiscalCode;

    @NotBlank
    private String birthPlace;

    @PastOrPresent
    @Getter
    private LocalDate birthDay;

    @NotNull
    private Sex sex;

    private final UUID uuid = UUID.randomUUID();

    private boolean healthCareWorker;

    /**
     * Gets the age of a patient by calculating the amount of time between
     * the current moment and the patient's birthday
     * @return Patient's age
     */
    public long getIntAge() {
        return ChronoUnit.YEARS.between(birthDay, LocalDate.now());
    }

    /**
     * Checks whether a patient is an adult, which means over the age of adultAgeThreshold
     * @return Whether the patient is adult
     */
    public boolean isAdult() {
        return getIntAge() > Constants.adultAgeThreshold;
    }

    @Override
    public String toString() {
        return "----Patient----" + "\n" +
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "fiscalCode: " + fiscalCode + "\n" +
                "birthPlace: " + birthPlace + "\n" +
                "birthDay: " + birthDay + "\n" +
                "sex: " + sex + "\n" +
                "code: " + "placeholder" + "\n" +
                "isHealthCareWorker: " + healthCareWorker + "\n";
    }

}
