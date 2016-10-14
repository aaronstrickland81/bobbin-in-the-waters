package model;


import javafx.beans.property.*;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Neil on 10/11/2016.
 */
public class WaterPurityReport extends Report {

    public final ObjectProperty<Condition> _condition = new SimpleObjectProperty<>();
    public final IntegerProperty _virusPPM = new SimpleIntegerProperty();
    public final IntegerProperty _chemPPM = new SimpleIntegerProperty();


    public WaterPurityReport(Date date, Time time, Integer reportnum, String name, String location, Condition cond, Integer virus, Integer chem) {
        super(date, time, reportnum, name, location);
        set_condition(cond);
        set_virusPPM(virus);
        set_chemPPM(chem);
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
