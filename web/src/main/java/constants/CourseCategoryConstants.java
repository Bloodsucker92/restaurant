package constants;


public enum CourseCategoryConstants {

    PIZZA(1), DRINKS(2), SOUP(3), DESSERT (4), OTHER (5);

    private int numVal;

   CourseCategoryConstants (int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
