package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Neil on 10/13/2016.
 */
public abstract class Report {
    private final ObjectProperty<Date> _date = new SimpleObjectProperty<>();
    private final IntegerProperty _reportNumber = new SimpleIntegerProperty();
    private final StringProperty _workername = new SimpleStringProperty();
    private final StringProperty _location = new SimpleStringProperty();
    private final DoubleProperty _longitude = new SimpleDoubleProperty();
    private final DoubleProperty _latitude = new SimpleDoubleProperty();

    /**
     * Full parameter constructor to create an instance of report
     *
     * @param date the date
     * @param reportnum the report number
     * @param name name of worker
     * @param longitude the longitude
     * @param latitude the latitude
     */
    Report(Date date, Integer reportnum, String name, Double
            longitude, Double latitude) {
        set_date(date);
        set_reportNumber(reportnum);
        set_workername(name);
        set_location(latitude + " Lat, " + longitude + " Long ");
        set_longitude(longitude);
        set_latitude(latitude);
    }

    /**
     * gets the date from the property
     *
     * @return Date object
     */
    public Date get_date() {
        return _date.get();
    }

    /**
     * Gets the year this report was submitted
     *
     * @return Year report was submitted
     */
    public int get_year() {
        Calendar dateOfReport = Calendar.getInstance();
        dateOfReport.setTime(this.get_date());
        return dateOfReport.get(Calendar.YEAR);
    }

    /**
     * Gets the month this report was submitted
     *
     * @return Month report was submitted
     */
    public int get_month() {
        Calendar dateOfReport = Calendar.getInstance();
        dateOfReport.setTime(this.get_date());
        return dateOfReport.get(Calendar.MONTH);
    }

    /**
     * Gets the name of the month this report was submitted
     *
     * @return Name of month report was submitted
     */
    public String get_month_name() {
        String monthString;
        switch (this.get_month()) {
            case 0:  monthString = "Jan";
                break;
            case 1:  monthString = "Feb";
                break;
            case 2:  monthString = "Mar";
                break;
            case 3:  monthString = "Apr";
                break;
            case 4:  monthString = "May";
                break;
            case 5:  monthString = "Jun";
                break;
            case 6:  monthString = "Jul";
                break;
            case 7:  monthString = "Aug";
                break;
            case 8:  monthString = "Sep";
                break;
            case 9: monthString = "Oct";
                break;
            case 10: monthString = "Nov";
                break;
            //noinspection MagicNumber
            case 11: monthString = "Dec";
                break;
            default: monthString = "Invalid month";
                break;
        }

        return monthString;
    }

    /**
     * Sets the date for this report
     *
     * @param _date Sets the date to this date
     */
    private void set_date(Date _date) {
        this._date.set(_date);
    }


    /**
     * Gets the report number
     *
     * @return the report number
     */
    public int get_reportNumber() {
        return _reportNumber.get();
    }

    /**
     * Sets the report number for this report
     *
     * @param _reportNumber Sets this report's number to this number
     */
    private void set_reportNumber(int _reportNumber) {
        this._reportNumber.set(_reportNumber);
    }

    /**
     * Get worker name associated with this report
     *
     * @return worker name associated with this report
     */
    public String get_workername() {
        return _workername.get();
    }

    /**
     * Sets the worker's name associated with this report
     *
     * @param _workername the worker's name to associate with this report
     */
    private void set_workername(String _workername) {
        this._workername.set(_workername);
    }

    /**
     * Gets the string representation of the location of this report
     *
     * @return the location of water source for this report
     */
    public String get_location() {
        return _location.get();
    }

    /**
     * Sets the location association with this report
     *
     * @param _location the new location of this water report
     */
    private void set_location(String _location) {
        this._location.set(_location);
    }

    /**
     * Gets the longitude of this report
     *
     * @return the longitude of report
     */
    public Double get_longitude() {
        return this._longitude.get();
    }

    /**
     * Sets the longitude of this report
     *
     * @param _longitude the longitude for this report
     */
    private void set_longitude(Double _longitude) {
        this._longitude.set(_longitude);
    }

    /**
     * Gets the latitude associate with this report
     *
     * @return the latitude of this report
     */
    public Double get_latitude() {
        return this._latitude.get();
    }

    /**
     * Sets the latitude property of this report
     *
     * @param _latitude the new latitude for this report
     */
    private void set_latitude(Double _latitude) {
        this._latitude.set(_latitude);
    }

    /**
     * Returns a string representation of the
     * accountType of water report
     *
     * @return the title of water report.
     */
    protected abstract String getTitle();

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        return "<h2>" + this.getTitle() + "</h2>"
                + "<p>Report #" + _reportNumber.get()
                + " submitted on " + df.format(_date.get()) + "</p>"
                + "<p>Location: " + _location.get() + "</p></br>";
    }

}
