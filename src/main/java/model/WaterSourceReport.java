package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Water Source Report class. This class is an information holder to store the
 * info submitted by users regarding water sources.
 *
 * Created by Neil on 10/16/2016.
 */
public class WaterSourceReport extends Report {

    private final ObjectProperty<WaterType> _type
            = new SimpleObjectProperty<>();
    private final ObjectProperty<SourceCondition> _condition
            = new SimpleObjectProperty<>();

    /**
     * Getter for water type
     * @return water type
     */
    public WaterType get_type() {
        return _type.get();
    }

    /**
     * Setter for water type
     * @param _type Water type to be set
     */
    private void set_type(WaterType _type) {
        this._type.set(_type);
    }

    /**
     * Getter for water condition
     * @return water condition
     */
    public SourceCondition get_condition() {
        return _condition.get();
    }

    /**
     * Setter for water condition
     * @param _condition water condition to be set
     */
    private void set_condition(SourceCondition _condition) {
        this._condition.set(_condition);
    }

    /**
     * Constructor for Water Source Report
     *
     * @param date date report was created
     * @param reportNum unique report id number
     * @param name username of user who submit report
     * @param longitude longitude of reported water source
     * @param latitude latitude of reported water source
     * @param type type of water
     * @param cond condition of water
     */
    public WaterSourceReport(Date date, Integer reportNum, String name,
                             Double longitude, Double latitude, WaterType type,
                             SourceCondition cond) {
        super(date, reportNum, name, longitude, latitude);
        set_type(type);
        set_condition(cond);
    }

    @Override
    public String getTitle() {
        return "Water Source Report";
    }
    public String toString() {
        return super.toString() + "<p>Type: " + get_type() + "</p>"
                + "<p>Condition: " + get_condition() + "</p>";
    }
    @Override
    public boolean equals(Object obj) {
        if (null == obj) { return false; }
        if(!(obj instanceof WaterSourceReport)) { return false;}
        if (this == obj) { return true; }
        WaterSourceReport temp = (WaterSourceReport) obj;
        return (this.get_date().equals(temp.get_date()))
                && (this.get_reportNumber() == (temp.get_reportNumber()))
                && (this.get_location().equals(temp.get_location()))
                && (this.get_workername().equals(temp.get_workername()))
                && (this.get_latitude().equals(temp.get_latitude()))
                && (this.get_longitude().equals(temp.get_longitude()))
                && (this.get_condition().equals(temp.get_condition()))
                && (this.get_type().equals(temp.get_type()));
    }

}
