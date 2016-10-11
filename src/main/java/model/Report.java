package model;


import javafx.beans.property.*;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Neil on 10/11/2016.
 */
public class Report {

    public final ObjectProperty<Date> _date = new SimpleObjectProperty<>();
    public final ObjectProperty<Time> _time = new SimpleObjectProperty<>();
    public final IntegerProperty _reportNumber = new SimpleIntegerProperty();
    public final StringProperty _workername = new SimpleStringProperty();
    public final StringProperty _location = new SimpleStringProperty();
    public final ObjectProperty<Condition> _condition = new SimpleObjectProperty<>();
    public final IntegerProperty _virusPPM = new SimpleIntegerProperty();
    public final IntegerProperty _chemPPM = new SimpleIntegerProperty();


    public Report(Date date, Time time, Integer reportnum, String name, String location, Condition cond, Integer virus, Integer chem) {
        set_date(date);
        set_time(time);
        set_reportNumber(reportnum);
        set_workername(name);
        set_location(location);
        set_condition(cond);
        set_virusPPM(virus);
        set_chemPPM(chem);
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

    public Time get_time() {
        return _time.get();
    }

    public ObjectProperty<Time> _timeProperty() {
        return _time;
    }

    public void set_time(Time _time) {
        this._time.set(_time);
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

    public Condition get_condition() {
        return _condition.get();
    }

    public ObjectProperty<Condition> _conditionProperty() {
        return _condition;
    }

    public void set_condition(Condition _condition) {
        this._condition.set(_condition);
    }

    public int get_virusPPM() {
        return _virusPPM.get();
    }

    public IntegerProperty _virusPPMProperty() {
        return _virusPPM;
    }

    public void set_virusPPM(int _virusPPM) {
        this._virusPPM.set(_virusPPM);
    }

    public int get_chemPPM() {
        return _chemPPM.get();
    }

    public IntegerProperty _chemPPMProperty() {
        return _chemPPM;
    }

    public void set_chemPPM(int _chemPPM) {
        this._chemPPM.set(_chemPPM);
    }
}
