package home.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class JobProfile {

    private BigDecimal salaryPerHour = BigDecimal.TEN;

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
