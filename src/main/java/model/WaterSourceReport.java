package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Neil on 10/16/2016.
 */
public class WaterSourceReport extends Report {

    public final ObjectProperty<WaterType> _type = new SimpleObjectProperty<>();
    public final ObjectProperty<Condition> _condition = new SimpleObjectProperty<>();

    public WaterType get_type() {
        return _type.get();
    }

    public ObjectProperty<WaterType> _typeProperty() {
        return _type;
    }

    public void set_type(WaterType _type) {
        this._type.set(_type);
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

    public WaterSourceReport(Date date, Time time, Integer reportnum, String name, String location, WaterType type, Condition cond) {
        super(date, time, reportnum, name, location);
        set_type(type);
        set_condition(cond);
    }
}
