package it.vaxplan.backend;

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
public class User {
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private String birthPlace;
    private String birthDay;
    private String sex;
    private String code;
    private boolean healthCareWorker;

    /**
     * Checks whether a string is a valid fiscal code or not.
     * @return whether a fiscal code is valid or not
     */
    boolean isValid() {
        var codiceFiscaleValido = "";
        String year = getYear();
        codiceFiscaleValido = codiceFiscaleValido.concat(toCheck(lastName)).concat(toCheck(firstName))
                .concat("" + year.charAt(2) + year.charAt(3)).concat(checkMonth());
        if (sex.equals("M"))
            codiceFiscaleValido = codiceFiscaleValido.concat(getDay());
        else {
            codiceFiscaleValido = codiceFiscaleValido.concat(getDayFemale());
        }
        codiceFiscaleValido = codiceFiscaleValido.concat(code);

        return true;
    }

    boolean isCodeValid() {
        // Is it 14 chars long?
        if (fiscalCode.length() != 14)
            return false;
        // Are the first 6 characters letters?
        for (var i = 0; i < 6; ++i) {
            if (!Character.isLetter(fiscalCode.charAt(i)) && !Character.isUpperCase(fiscalCode.charAt(i)))
                return false;
        }

        // Are the next 2 characters valid numbers?

        // regex



        return true;
    }

    // TODO fix
    /** Check the first 6 digits of the fiscal code which represent the full name.
     * Try consonants first: if there are none, fall back to constants. If last name is shorter than 3 chars, set the
     * remaining chars to X.
     *
     * @param n Name or last name to check
     * @return String of three letters that can be used as a key to compare two identities
     */
    private String toCheck(String n) {
        var check = "";
        for (var i = 0; i < n.length() && check.length() < 3; i++) {
            if (n.charAt(i) != 'A' && n.charAt(i) != 'E' && n.charAt(i) != 'I' && n.charAt(i) != 'O' && n.charAt(i) != 'U') {
                check = check.concat("" + n.charAt(i));
            }
        }
        for (var i = 0; i < n.length() && check.length() < 3; i++) {
            if (n.charAt(i) == 'A' || n.charAt(i) == 'E' || n.charAt(i) == 'I' || n.charAt(i) == 'O' || n.charAt(i) == 'U') {
                check = check.concat("" + n.charAt(i));
            }
        }
        if (check.length() < 3 && n.length() < 2) {
            return check.concat("X");
        }

        return check;
    }

    /**
     * Returns year of birth as String.
     * @return String containing the year of birth.
     */
    public String getYear() {
        var year = "";
        for (int i = birthDay.length() - 4; i < birthDay.length(); i++) {
            year = year.concat("" + birthDay.charAt(i));
        }

        return year;
    }

    /**
     * Returns month of birth as String.
     * @return String containing the year of birth.
     */
    private String getMonth() {
        return "" + birthDay.charAt(3) + birthDay.charAt(4);
    }

    /**
     * Italian fiscal codes associate various months with non-obvious letters. This function associates
     * a month name with the corresponding letter on a fiscal code.
     * @return Letter associated to the month of birth.
     */
    private String checkMonth() {
        switch (getMonth()) {
            case "01":
                return "A";
            case "02":
                return "B";
            case "03":
                return "C";
            case "04":
                return "D";
            case "05":
                return "E";
            case "06":
                return "H";
            case "07":
                return "L";
            case "08":
                return "M";
            case "09":
                return "P";
            case "10":
                return "R";
            case "11":
                return "S";
            default:
                return "T";
        }
    }

    /**
     * Getter for birthday on the fiscal code.
     * @return birthday
     */
    private String getDay() {
        return "" + birthDay.charAt(0) + birthDay.charAt(1);
    }

    /**
     * Getter for birthday on the fiscal code, for poeple with sex F.
     * As a convention, 40 gets added to the base birth date in this case.
     * @return birthday + 40
     */
    private String getDayFemale() {
        return Integer.toString(Integer.parseInt(getDay()) + 40);
    }
}
