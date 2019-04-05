package org.sofl.soptorshi.client.employee.management;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.sofl.soptorshi.model.Employee;
import org.sofl.soptorshi.model.enums.EmploymentType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeInformationService {
    public VerticalLayout getEmployeeInformation(Employee employee, Boolean editable){
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        VerticalLayout personalTabLayout = new VerticalLayout();
        VerticalLayout educationTabLayout = new VerticalLayout();
        VerticalLayout experienceTabLayout = new VerticalLayout();
        Tab personalInformationTab = new Tab("Personal Information");
        Tab educationTab = new Tab("Educational Qualifications");
        Tab experienceTab = new Tab("Experiences");

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(personalInformationTab, personalTabLayout);
        tabsToPages.put(educationTab, educationTabLayout);
        tabsToPages.put(experienceTab, experienceTabLayout);

        Set<Component> pagesShown = Stream.of(personalTabLayout)
                .collect(Collectors.toSet());

        Tabs tabs = new Tabs(personalInformationTab, educationTab, experienceTab);
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setSelectedTab(personalInformationTab);
        tabs.addSelectedChangeListener(event->{
            pagesShown.forEach(page->page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        content.add(tabs);
        return content;
    }


    private void createPersonalInformationLayout(Employee employee, Boolean editable, VerticalLayout content){
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        FormLayout formLayout = new FormLayout();
        Binder<Employee> employeeBinder = new Binder<>();

        TextField employeeId = new TextField();
        employeeId.setValueChangeMode(ValueChangeMode.EAGER);
        TextField name = new TextField();
        name.setValueChangeMode(ValueChangeMode.EAGER);
        Select<EmploymentType> employmentTypeSelect = new Select<>(EmploymentType.ACTIVE, EmploymentType.RESIGNED, EmploymentType.SUSPENDED);


    }

}
