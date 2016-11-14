package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.enums.PurityCondition;

import java.util.Date;

/**
 * Created by Neil on 10/11/2016.
 */
public class WaterQualityReport extends Report {

    public final ObjectProperty<PurityCondition> _condition = new SimpleObjectProperty<>();
    public final DoubleProperty _virusPPM = new SimpleDoubleProperty();
    public final DoubleProperty _chemPPM = new SimpleDoubleProperty();


    public WaterQualityReport(Date date, Integer reportnum,
                              String name,
                              Double longitude,
                              Double latitude,
                              PurityCondition cond,
                              Double virus,
                              Double chem) {
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

    @Override
    public String getTitle() {
        return "Water Purity Report";
    }

    @Override
    public String toString() {
        return super.toString() + "<p>Condition: " + get_condition() + "</p>"
                + "</br><p>VirusPPM: " + _virusPPM.get() + "</p><br><p>Chemical PPM: "
                + _chemPPM.get() + "</p>";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof WaterQualityReport)) { return false;}
        if (null == obj) { return false; }
        if (this == obj) { return true; }
        WaterQualityReport temp = (WaterQualityReport) obj;
        if (this.get_date().equals(temp.get_date())
                && this.get_reportNumber() == (temp.get_reportNumber())
                && this.get_location().equals(temp.get_location())
                && this.get_workername().equals(temp.get_workername())
                && this.get_latitude().equals(temp.get_latitude())
                && this.get_longitude().equals(temp.get_longitude())
                && this.get_condition().equals(temp.get_condition())
                && this.get_chemPPM() == (temp.get_chemPPM())
                && this.get_virusPPM() == temp.get_virusPPM()) {
            return true;
        }
        return false;
    }
}
