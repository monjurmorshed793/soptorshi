package org.sofl.soptorshi.client.employee.management;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.History;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.AcademicInformation;
import org.sofl.soptorshi.model.Employee;
import org.sofl.soptorshi.model.ExperienceInformation;
import org.sofl.soptorshi.repository.AcademicInformationRepository;
import org.sofl.soptorshi.repository.EmployeeRepository;
import org.sofl.soptorshi.repository.ExperienceInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "employee-management", layout = MainView.class)
public class EmployeeManagementView extends VerticalLayout implements HasUrlParameter<String> {

    private EmployeeRepository employeeRepository;
    private AcademicInformationRepository academicInformationRepository;
    private ExperienceInformationRepository experienceInformationRepository;

    private String employeeId;
    private Employee employee;
    private AcademicInformation academicInformation;
    private ExperienceInformation experienceInformation;

    @Override
    public void setParameter(BeforeEvent beforeEvent,@OptionalParameter String employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeManagementView(@Autowired EmployeeRepository employeeRepository,
                                  @Autowired AcademicInformationRepository academicInformationRepository,
                                  @Autowired ExperienceInformationRepository experienceInformationRepository) {
        this.employeeRepository = employeeRepository;
        this.academicInformationRepository = academicInformationRepository;
        this.experienceInformationRepository = experienceInformationRepository;

        setSizeFull();
        createTab();
        Button backButton = new Button(VaadinIcon.BACKWARDS.create());
        backButton.addClickListener(e->goBackToPreviousPage());
        add(backButton);
        setAlignSelf(Alignment.END, backButton);
    }

    private void createTab(){
        Tab personalInformationTab = new Tab("Personal Information");
        Tab academicInformationTab = new Tab("Academic Information");
        Tab experienceInformationTab = new Tab("Experience Information");

        VerticalLayout personalInformationLayout = new VerticalLayout();
        personalInformationLayout.add(new Text("Hello world!"));
        VerticalLayout academicInformationLayout = new VerticalLayout();
        VerticalLayout experienceInformationLayout = new VerticalLayout();

        academicInformationLayout.setVisible(false);
        experienceInformationLayout.setVisible(false);

        createPersonalInformationSection(personalInformationLayout);
        createAcademicInformationSection(academicInformationLayout);
        createExperienceInformationSection(experienceInformationLayout);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(personalInformationTab, personalInformationLayout);
        tabsToPages.put(academicInformationTab, academicInformationLayout);
        tabsToPages.put(experienceInformationTab, experienceInformationLayout);
        Set<Component> pagesShown = Stream.of(personalInformationLayout)
                .collect(Collectors.toSet());

        Tabs tabs = new Tabs(personalInformationTab, academicInformationTab, experienceInformationTab);
        VerticalLayout pages = new VerticalLayout(personalInformationLayout, academicInformationLayout, experienceInformationLayout);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);

        tabs.addSelectedChangeListener(event->{
            pagesShown.forEach(page->page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });

        add(tabs, pages);
        setAlignSelf(Alignment.CENTER, tabs);
        setAlignSelf(Alignment.CENTER, pages);

    }

    private void createPersonalInformationSection(VerticalLayout content){
        FormLayout personalInformationForm = new FormLayout();
        Binder<Employee> binder = new Binder<>();

        TextField employeeId = new TextField();
        TextField name = new TextField();
        


    }

    private void createAcademicInformationSection(VerticalLayout content){
        content.add(new Text("In Academic Information Section"));
    }

    private void createExperienceInformationSection(VerticalLayout content){
        content.add(new Text("In Experience Section"));
    }

    private void goBackToPreviousPage(){
        History history = UI.getCurrent().getPage().getHistory();
        history.back();
    }
}
