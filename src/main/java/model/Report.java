package model;

import javafx.beans.property.*;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Neil on 10/13/2016.
 */
public abstract class Report {
    public final ObjectProperty<Date> _date = new SimpleObjectProperty<>();
    public final IntegerProperty _reportNumber = new SimpleIntegerProperty();
    public final StringProperty _workername = new SimpleStringProperty();
    public final StringProperty _location = new SimpleStringProperty();

    public Report(Date date, Integer reportnum, String name, String location) {
        set_date(date);
        set_reportNumber(reportnum);
        set_workername(name);
        set_location(location);
    }

    public Date get_date() {
        return _date.get();
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

}
