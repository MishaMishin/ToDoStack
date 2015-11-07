package home.service;

import home.domain.Employee;
import home.domain.EmployeeRepository;
import home.domain.JobProfileRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeSalaryServiceTest {

    @InjectMocks EmployeeSalaryService employeeSalaryService;
    @Mock JobProfileRepository jobProfileRepository;
    @Mock EmployeeRepository employeeRepository;
    @Mock WorkHoursPerMonthRepository workHoursPerMonthRepository;

    private String employeeId = UUID.randomUUID().toString();

    /*
      Расчитать конкретному сотруднику зарплату за месяц.
      Исходя из того, что сотрудник получает ЗП почасово
      - Получить сотрудника из репозитория по ИД
      - Определить ЗП сотрудника в час
      - Определить, сколько часов за месяц отработано
      - Рассчитать ЗП = кол-во часов * ЗП в час
    */

    @Test
    public void calculateEmployeeSalaryForMonth_Invocation_employeeRepositoryGetByIdIsInvoked() throws Exception {
        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verify(employeeRepository).getById(employeeId);
    }

    @Test
    public void calculateEmployeeSalaryForMonth_EmployeeIsNull_DoesNotInvokeJobProfileRepo() {
        when(employeeRepository.getById(employeeId)).thenReturn(null);

        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verifyZeroInteractions(jobProfileRepository);
    }

    @Test
    public void calculateEmployeeSalaryForMonth_EmployeeIsPresent_InvokesJobProfileRepo() {
        Employee employee = new Employee();
        when(employeeRepository.getById(employeeId)).thenReturn(employee);

        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verify(jobProfileRepository).getByEmployee(employee);
    }

    //@Entity WorkHoursPerMonth - HOURS WHICH EMPLOYEE WORKED PER CERTAIN MONTH
    //Employee
    //BigDecimal - getHours()
    //String month
    //WorkHoursPerMonthRepository
    // get by employee and month


    /*
     проверяем вызывается ли метод
     workHoursPerMonthRepository.getByEmployeeAndMonth(employee, month)
     для получения сущности WorkHoursPerMonth
    */
    @Test
    public void calculateEmployeeSalaryForMonth_Invocation_WorkHoursPerMonthRepositoryByEmployeeAndMonth() throws Exception{
        Employee employee = new Employee();
        String month = "January";

        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verify(workHoursPerMonthRepository.getByEmployeeAndMonth(employee, month));
    }

    /*
    * проверяем вызывается ли метод workHoursPerMonth.getHours()
    */
    @Test
    public void calculateEmployeeSalaryForMonth_Invocation_WorkHoursPerMonthGetHours() throws Exception{
        Employee employee = new Employee();
        String month = "January";
        WorkHoursPerMonth workHoursPerMonth = new WorkHoursPerMonth();

        when(workHoursPerMonthRepository.getByEmployeeAndMonth(employee, month)).thenReturn(workHoursPerMonth);


        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verify(workHoursPerMonth).getHours();
    }


    /*
    * если workHoursPerMonth пуст(null) то у него метод не вызывается.... workHoursPerMonth.getHours()
    * */
    @Test
    public void calculateEmployeeSalaryForMonth_WorkHoursPerMonthIsNull_DontInvokeWorkHoursPerMonthGetHours(){
        Employee employee = new Employee();
        String month = "January";
        WorkHoursPerMonth workHoursPerMonth = new WorkHoursPerMonth();
        when(workHoursPerMonthRepository.getByEmployeeAndMonth(employee, month)).thenReturn(null);


        employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        verifyZeroInteractions(workHoursPerMonth);


    }



    /*Проверяем возвращение зарплаты при передачи ID сотрудника в метод*/
    @Test
    public void calculateEmployeeSalaryForMonth_() throws Exception {
        BigDecimal salary = employeeSalaryService.calculateEmployeeSalaryForMonth(employeeId);

        Assert.assertEquals(2500,salary);
    }
}