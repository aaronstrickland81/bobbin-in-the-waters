package model;

import javafx.beans.property.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Neil on 10/13/2016.
 */
public abstract class Report {
    public final ObjectProperty<Date> _date = new SimpleObjectProperty<>();
    public final IntegerProperty _reportNumber = new SimpleIntegerProperty();
    public final StringProperty _workername = new SimpleStringProperty();
    public final StringProperty _location = new SimpleStringProperty();
    public final DoubleProperty _longitude = new SimpleDoubleProperty();
    public final DoubleProperty _latitude = new SimpleDoubleProperty();

    public Report(Date date, Integer reportnum, String name, Double
            longitude, Double latitude) {
        set_date(date);
        set_reportNumber(reportnum);
        set_workername(name);
        set_location(latitude + " Lat, " + longitude + " Long ");
        set_longitude(longitude);
        set_latitude(latitude);
    }

    public Date get_date() {
        return _date.get();
    }

    public int get_year() {
        Calendar dateOfReport = Calendar.getInstance();
        dateOfReport.setTime(this.get_date());
        return dateOfReport.get(Calendar.YEAR);
    }

    public int get_month() {
        Calendar dateOfReport = Calendar.getInstance();
        dateOfReport.setTime(this.get_date());
        return dateOfReport.get(Calendar.MONTH);
    }

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
            case 11: monthString = "Dec";
                break;
            default: monthString = "Invalid month";
                break;
        }

        return monthString;
    }

    public ObjectProperty<Date> _dateProperty() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date.set(_date);
    }


    public int get_reportNumber() {
        return _reportNumber.get();
    }

    public IntegerProperty _reportNumberProperty() {
        return _reportNumber;
    }

    public void set_reportNumber(int _reportNumber) {
        this._reportNumber.set(_reportNumber);
    }


    public String get_workername() {
        return _workername.get();
    }

    public StringProperty _workernameProperty() {
        return _workername;
    }

    public void set_workername(String _workername) {
        this._workername.set(_workername);
    }


    public String get_location() {
        return _location.get();
    }

    public StringProperty _locationProperty() {
        return _location;
    }

    public void set_location(String _location) {
        this._location.set(_location);
    }


    public Double get_longitude() {
        return this._longitude.get();
    }

    public DoubleProperty _longitudeProperty() {
        return _longitude;
    }

    public void set_longitude(Double _longitude) {
        this._longitude.set(_longitude);
    }


    public Double get_latitude() {
        return this._latitude.get();
    }

    public DoubleProperty _latitudeProperty() {
        return _latitude;
    }

    public void set_latitude(Double _latitude) {
        this._latitude.set(_latitude);
    }

    /**
     * Returns a string representation of the
     * type of water report
     *
     * @return the title of water report.
     */
    public abstract String getTitle();

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        return "<h2>" + this.getTitle() + "</h2>"
                + "<p>Report #" + _reportNumber.get()
                + " submitted on " + df.format(_date.get()) + "</p>"
                + "<p>Location: " + _location.get() + "</p></br>";
    }

}
