package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {

    private StringProperty type = new SimpleStringProperty();
    private StringProperty count = new SimpleStringProperty();
    private StringProperty month = new SimpleStringProperty();

    public Report(String type, String count, String month) {
        this.type.setValue(type);
        this.count.setValue(count);
        this.month.setValue(month);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }

    public String getMonth() {
        return month.get();
    }

    public StringProperty monthProperty() {
        return month;
    }
}
