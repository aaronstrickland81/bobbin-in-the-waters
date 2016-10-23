package model;


import javafx.beans.property.*;
import model.enums.PurityCondition;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Neil on 10/11/2016.
 */
public class WaterPurityReport extends Report {

    public final ObjectProperty<PurityCondition> _condition = new SimpleObjectProperty<>();
    public final DoubleProperty _virusPPM = new SimpleDoubleProperty();
    public final DoubleProperty _chemPPM = new SimpleDoubleProperty();


    public WaterPurityReport(Date date, Integer reportnum, String name,
                             Double longitude, Double latitude,
                             PurityCondition cond,
                             Double virus, Double chem) {
        super(date, reportnum, name, longitude, latitude);
        set_condition(cond);
        set_virusPPM(virus);
        set_chemPPM(chem);
    }



    public PurityCondition get_condition() {
        return _condition.get();
    }

    public ObjectProperty<PurityCondition> _conditionProperty() {
        return _condition;
    }

    public void set_condition(PurityCondition _Overall_condition) {
        this._condition.set(_Overall_condition);
    }

    public double get_virusPPM() {
        return _virusPPM.get();
    }

    public DoubleProperty _virusPPMProperty() {
        return _virusPPM;
    }

    public void set_virusPPM(double _virusPPM) {
        this._virusPPM.set(_virusPPM);
    }

    public double get_chemPPM() {
        return _chemPPM.get();
    }

    public DoubleProperty _chemPPMProperty() {
        return _chemPPM;
    }

    public void set_chemPPM(double _chemPPM) {
        this._chemPPM.set(_chemPPM);
    }
}
