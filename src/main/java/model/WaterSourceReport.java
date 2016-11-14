package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Created by Neil on 10/16/2016.
 */
public class WaterSourceReport extends Report {

    private final ObjectProperty<WaterType> _type = new SimpleObjectProperty<>();
    private final ObjectProperty<SourceCondition> _condition = new SimpleObjectProperty<>();

    public WaterType get_type() {
        return _type.get();
    }

    public ObjectProperty<WaterType> _typeProperty() {
        return _type;
    }

    private void set_type(WaterType _type) {
        this._type.set(_type);
    }

    public SourceCondition get_condition() {
        return _condition.get();
    }

    public ObjectProperty<SourceCondition> _conditionProperty() {
        return _condition;
    }

    private void set_condition(SourceCondition _condition) {
        this._condition.set(_condition);
    }

    public WaterSourceReport(Date date, Integer reportnum, String name,
                             Double longitude, Double latitude, WaterType type,
                             SourceCondition
                                     cond) {
        super(date, reportnum, name, longitude, latitude);
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
