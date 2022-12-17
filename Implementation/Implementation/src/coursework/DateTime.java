package coursework;

public class DateTime {

    //Creating variables
    private int date;
    private int month;
    private int year;

    //Creating constructors for variables
    public DateTime(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    //Creating getters and setters for date
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    //Creating getters and setters for month
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    //Creating getters and setters for year
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return date+"-"+month+"-"+year;
    }
}
