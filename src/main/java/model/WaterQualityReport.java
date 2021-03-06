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

    private final ObjectProperty<PurityCondition> _condition
            = new SimpleObjectProperty<>();
    private final DoubleProperty _virusPPM = new SimpleDoubleProperty();
    private final DoubleProperty _chemPPM = new SimpleDoubleProperty();

    /**
     * Constructor for Water Quality Reports
     * @param date date of report
     * @param reportnum report number
     * @param name worker name
     * @param longitude longitude
     * @param latitude latitude
     * @param cond condition
     * @param virus virus ppm
     * @param chem chemical ppm
     */
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


    /**
     * getter for purity condition
     * @return the report's purity condition
     */
    public PurityCondition get_condition() {
        return _condition.get();
    }

    /**
     * Setter for purity condition
     * @param _Overall_condition condition to be set
     */
    private void set_condition(PurityCondition _Overall_condition) {
        this._condition.set(_Overall_condition);
    }

    /**
     * Getter for virus PPM
     * @return virus PPM
     */
    public double get_virusPPM() {
        return _virusPPM.get();
    }

    /**
     * Setter for virus PPM
     * @param _virusPPM virus PPM to be set
     */
    private void set_virusPPM(double _virusPPM) {
        this._virusPPM.set(_virusPPM);
    }

    /**
     * Getter for chem PPM
     * @return chem PPM
     */
    public double get_chemPPM() {
        return _chemPPM.get();
    }

    /**
     * Setter for chem PPM
     * @param _chemPPM chem PPM to be set
     */
    private void set_chemPPM(double _chemPPM) {
        this._chemPPM.set(_chemPPM);
    }

    @Override
    public String getTitle() {
        return "Water Purity Report";
    }

    @Override
    public String toString() {
        return super.toString() + "<p>Condition: " + get_condition() + "</p>"
                + "</br><p>VirusPPM: " + _virusPPM.get() +
                "</p><br><p>Chemical PPM: " + _chemPPM.get() + "</p>";
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) { return false; }
        if(!(obj instanceof WaterQualityReport)) { return false;}
        if (this == obj) { return true; }
        WaterQualityReport temp = (WaterQualityReport) obj;
        return (this.get_date().equals(temp.get_date()))
                && (this.get_reportNumber() == (temp.get_reportNumber()))
                && (this.get_location().equals(temp.get_location()))
                && (this.get_workername().equals(temp.get_workername()))
                && (this.get_latitude().equals(temp.get_latitude()))
                && (this.get_longitude().equals(temp.get_longitude()))
                && (this.get_condition().equals(temp.get_condition()))
                && (this.get_chemPPM() == (temp.get_chemPPM()))
                && (this.get_virusPPM() == temp.get_virusPPM());
    }
}
