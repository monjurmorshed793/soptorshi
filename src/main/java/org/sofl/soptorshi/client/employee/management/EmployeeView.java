package org.sofl.soptorshi.client.employee.management;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.Employee;
import org.sofl.soptorshi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "Employee", layout = MainView.class)
public class EmployeeView extends VerticalLayout {
    private EmployeeRepository employeeRepository;

    public EmployeeView(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        setSizeFull();

        HorizontalLayout buttonContainer = new HorizontalLayout();
        Button addBtn = new Button("Add New Employee");
        addBtn.setIcon(VaadinIcon.PLUS.create());
        buttonContainer.add(addBtn);

        add(buttonContainer);
        setAlignSelf(Alignment.END, buttonContainer);

        Grid<Employee> employeeGrid = getEmployeeGrid();
        add(employeeGrid);
    }

    private Grid<Employee> getEmployeeGrid(){
        Grid<Employee> employeeGrid = new Grid<>();
        DataProvider<Employee, Void> dataProvider = DataProvider.fromCallbacks(
                query -> {
                    List<Employee> employeeList = employeeRepository.findAll(PageRequest.of(query.getOffset(), query.getLimit())).getContent();
                    return employeeList.stream();
                },
                query -> Integer.parseInt(employeeRepository.count()+"")
        );
        employeeGrid.setDataProvider(dataProvider);
        employeeGrid.addColumn(Employee::getEmployeeId).setHeader("Employee Id");
        employeeGrid.addColumn(Employee::getName).setHeader("Employee Name");
        employeeGrid.addColumn(e->e.getDepartment()==null?"": e.getDepartment().getName()).setHeader("Department");
        employeeGrid.addColumn(e->e.getDesignation()!=null?e.getDesignation().getName():"").setHeader("Designation");
        return employeeGrid;
    }
}
