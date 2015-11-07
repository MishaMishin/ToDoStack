package home.service;


import home.domain.Employee;
import home.domain.EmployeeRepository;
import home.domain.JobProfile;
import home.domain.JobProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class EmployeeSalaryService {

    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private JobProfileRepository jobProfileRepository;
    @Autowired private WorkHoursPerMonthRepository workHoursPerMonthRepository;

    public BigDecimal calculateEmployeeSalaryForMonth(String employeeId) {
        Employee employee = employeeRepository.getById(employeeId);
        if(employee != null) {
            JobProfile jobProfile = jobProfileRepository.getByEmployee(employee);
            BigDecimal salaryPerHour = jobProfile.getSalaryPerHour();
        }
        return null;
    }
}
