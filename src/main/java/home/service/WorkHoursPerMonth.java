package home.service;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class WorkHoursPerMonth {
    private BigDecimal hours = BigDecimal.TEN;

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }
}
