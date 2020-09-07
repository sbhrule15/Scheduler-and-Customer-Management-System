package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {

    private StringProperty value1 = new SimpleStringProperty();
    private StringProperty value2 = new SimpleStringProperty();
    private StringProperty value3 = new SimpleStringProperty();
    private StringProperty value4 = new SimpleStringProperty();

    public Report(String val1, String val2, String val3, String val4) {
        this.value1.setValue(val1);
        this.value2.setValue(val2);
        this.value3.setValue(val3);
        this.value4.setValue(val4);

    }

    public String getValue1() {
        return value1.get();
    }

    public StringProperty value1Property() {
        return value1;
    }

    public String getValue2() {
        return value2.get();
    }

    public StringProperty value2Property() {
        return value2;
    }

    public String getValue3() {
        return value3.get();
    }

    public StringProperty value3Property() {
        return value3;
    }

    public String getValue4() {
        return value4.get();
    }

    public StringProperty value4Property() {
        return value4;
    }
}
