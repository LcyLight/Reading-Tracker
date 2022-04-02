package readtracker.demo3;

public enum Month {
    // Create month constants
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    // Getter method that takes the monthNum 1-12 and returns the month word
    public static Month getMonthWord(int monthNum){
        if (monthNum == 1){
            return JANUARY;
        }
        else if (monthNum == 2){
            return FEBRUARY;
        }
        else if (monthNum == 3){
            return MARCH;
        }
        else if (monthNum == 4){
            return APRIL;
        }
        else if (monthNum == 5){
            return MAY;
        }
        else if (monthNum == 6){
            return JUNE;
        }
        else if (monthNum == 7){
            return JULY;
        }
        else if (monthNum == 8){
            return AUGUST;
        }
        else if (monthNum == 9){
            return SEPTEMBER;
        }
        else if (monthNum == 10){
            return OCTOBER;
        }
        else if (monthNum == 11){
            return NOVEMBER;
        }
        else if (monthNum == 12){
            return DECEMBER;
        }
        else {
            throw new IllegalArgumentException("Month digit " + monthNum + " is invalid, must be an integer 1-12");
        }
    }
}
