package org.sofl.soptorshi.client.employee.management;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.Employee;
import org.sofl.soptorshi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "employee-details", layout = MainView.class)
public class EmployeeDetailsView  extends VerticalLayout implements HasUrlParameter<String> {
    private String employeeId;
    private EmployeeRepository employeeRepository;
    private List<Employee> employeeList;
    private Employee selectedEmployee;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        if(parameter!=null)
            employeeId = parameter;
    }

    public EmployeeDetailsView(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        addSearchBox();

    }

    public void addSearchBox(){
        ComboBox<Employee> employeeComboBox = new ComboBox<>();
        employeeComboBox.setLabel("Employee selection");
        employeeComboBox.setItemLabelGenerator(Employee::getName);
        employeeComboBox.setItems(employeeRepository.findAll());
        add(employeeComboBox);
        setAlignSelf(Alignment.CENTER, employeeComboBox);
    }

    public void personalInformationDetails(){
        FormLayout employeeForm = new FormLayout();

    }


}
