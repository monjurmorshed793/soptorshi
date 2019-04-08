package org.sofl.soptorshi.client.employee.management;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.History;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.sofl.soptorshi.MainView;
import org.sofl.soptorshi.model.*;
import org.sofl.soptorshi.model.enums.*;
import org.sofl.soptorshi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "employee-management", layout = MainView.class)
public class EmployeeManagementView extends VerticalLayout implements HasUrlParameter<String>  {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private AcademicInformationRepository academicInformationRepository;
    private ExperienceInformationRepository experienceInformationRepository;
    private DesignationRepository designationRepository;
    private RoleRepository roleRepository;
    private DepartmentRepository departmentRepository;
    private WingsRepository wingsRepository;
    private LocationRepository locationRepository;


    private String employeeId;
    private Employee employee;
    private AcademicInformation academicInformation;
    private ExperienceInformation experienceInformation;

    @Override
    public void setParameter(BeforeEvent beforeEvent,@OptionalParameter String employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeManagementView(@Autowired EmployeeRepository employeeRepository,
                                  @Autowired UserRepository userRepository,
                                  @Autowired AcademicInformationRepository academicInformationRepository,
                                  @Autowired ExperienceInformationRepository experienceInformationRepository,
                                  @Autowired DesignationRepository designationRepository,
                                  @Autowired RoleRepository roleRepository,
                                  @Autowired DepartmentRepository departmentRepository,
                                  @Autowired WingsRepository wingsRepository,
                                  @Autowired LocationRepository locationRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.academicInformationRepository = academicInformationRepository;
        this.experienceInformationRepository = experienceInformationRepository;
        this.designationRepository = designationRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.wingsRepository = wingsRepository;
        this.locationRepository = locationRepository;

        setSizeFull();
        createTab();
        Button backButton = new Button(VaadinIcon.BACKWARDS.create());
        backButton.addClickListener(e->goBackToPreviousPage());
        add(backButton);
        setAlignSelf(Alignment.END, backButton);
    }

    private void createTab() {
        Tab personalInformationTab = new Tab("Personal Information");
        Tab academicInformationTab = new Tab("Academic Information");
        Tab experienceInformationTab = new Tab("Experience Information");

        VerticalLayout personalInformationLayout = new VerticalLayout();
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
        FormLayout formLayout = new FormLayout();
        Binder<Employee> binder = new Binder<>(Employee.class);
        if(employee==null) {
            employee = new Employee();
        }
        try{
            binder.writeBean(employee);
        }catch (Exception e){
            Notification.show("Error in binding the existing data");
        }
        TextField employeeId = new TextField();
        employeeId.setWidth("100%");
        binder.bind(employeeId, Employee::getEmployeeId, Employee::setEmployeeId);
        formLayout.addFormItem(employeeId,"Employee ID");

        TextField name = new TextField();
        name.setWidth("100%");
        binder.bind(name, Employee::getName, Employee::setName);
        formLayout.addFormItem(name, "Employee Name");

        TextField fathersName = new TextField();
        fathersName.setWidth("100%");
        binder.bind(fathersName, Employee::getFatherName, Employee::setFatherName);
        formLayout.addFormItem(fathersName, "Father's Name");

        TextField motherName = new TextField();
        binder.bind(motherName, Employee::getMotherName, Employee::setMotherName);
        motherName.setWidth("100%");
        formLayout.addFormItem(motherName, "Mother's Name");

        TextArea presentAddress = new TextArea();
        presentAddress.setWidth("100%");
        binder.bind(presentAddress, Employee::getPresentAddress, Employee::setPresentAddress);
        formLayout.addFormItem(presentAddress, "Present Address").getElement().setAttribute("colspan", "2");

        TextArea permanentAddress = new TextArea();
        permanentAddress.setWidth("100%");
        binder.bind(permanentAddress, Employee::getPermanentAddress, Employee::setPermanentAddress);
        formLayout.addFormItem(permanentAddress, "Permanent Address").getElement().setAttribute("colspan", "2");

        TextField contactNumber = new TextField();
        contactNumber.setWidth("100%");
        binder.bind(contactNumber, Employee::getContactNumber, Employee::setContactNumber);
        formLayout.addFormItem(contactNumber, "Contact Number");

        TextArea emergencyContactNumber = new TextArea();
        emergencyContactNumber.setWidth("100%");
        binder.bind(emergencyContactNumber, Employee::getEmergencyContact, Employee::setEmergencyContact);
        formLayout.addFormItem(emergencyContactNumber, "Emergency Contact");

        DatePicker birthDate = new DatePicker();
        birthDate.setWidth("100%");
        binder.bind(birthDate, Employee::getDateOfBirth, Employee::setDateOfBirth);
        formLayout.addFormItem(birthDate, "Birth Date");

        TextField nationalId = new TextField();
        nationalId.setWidth("100%");
        binder.bind(nationalId, Employee::getNationalId, Employee::setNationalId);
        formLayout.addFormItem(nationalId, "National Id Number");

        TextField tinNumber = new TextField();
        tinNumber.setWidth("100%");
        binder.bind(tinNumber, Employee::getTinNumber, Employee::setTinNumber);
        formLayout.addFormItem(tinNumber, "TIN Number");

        Select<BloodGroup> bloodGroupSelect = new Select<>(BloodGroup.values());
        bloodGroupSelect.setWidth("100%");
        binder.bind(bloodGroupSelect, Employee::getBloodGroup, Employee::setBloodGroup);
        formLayout.addFormItem(bloodGroupSelect, "Blood Group");

        EmailField email = new EmailField();
        email.setWidth("100%");
        binder.forField(email)
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(Employee::getEmailAddress, Employee::setEmailAddress);
        formLayout.addFormItem(email, "Email");

        Select<MaritalStatus> maritalStatusSelect = new Select<>(MaritalStatus.values());
        maritalStatusSelect.setWidth("100%");
        binder.bind(maritalStatusSelect, Employee::getMaritalStatus, Employee::setMaritalStatus);
        formLayout.addFormItem(maritalStatusSelect, "Marital Status");

        Select<Gender> genderSelect = new Select<>(Gender.values());
        genderSelect.setWidth("100%");
        binder.bind(genderSelect, Employee::getGender, Employee::setGender);
        formLayout.addFormItem(genderSelect, "Gender");

        Select<Religion> religionSelect = new Select<>(Religion.values());
        religionSelect.setWidth("100%");
        binder.bind(religionSelect, Employee::getReligion, Employee::setReligion);
        formLayout.addFormItem(religionSelect, "Religion");


        Select<Employeestatus> employeeStatusSelect = new Select<>(Employeestatus.values());
        employeeStatusSelect.setWidth("100%");
        binder.bind(employeeStatusSelect, Employee::getEmployeestatus, Employee::setEmployeestatus);
        formLayout.addFormItem(employeeStatusSelect, "Employee Status");

        Select<EmploymentType> employmentTypeSelect = new Select<>(EmploymentType.values());
        employmentTypeSelect.setWidth("100%");
        binder.bind(employmentTypeSelect, Employee::getEmploymentType, Employee::setEmploymentType);
        formLayout.addFormItem(employmentTypeSelect, "Employment Type");

        List<Department> departmentList = departmentRepository.findAll();
        Select<Department> departmentSelect = new Select<>();
        departmentSelect.setWidth("100%");
        binder.bind(departmentSelect, Employee::getDepartment, Employee::setDepartment);
        departmentSelect.setItems(departmentList);
        departmentSelect.setTextRenderer(Department::getName);
        formLayout.addFormItem(departmentSelect, "Department");

        List<Wings> wingsList = wingsRepository.findAll();
        Select<Wings> wingsSelect = new Select<>();
        wingsSelect.setWidth("100%");
//        binder.bind(wingsSelect, Employee::getWings, Employee::setWings);
        wingsSelect.setTextRenderer(Wings::getName);
        wingsSelect.setItems(wingsList);
        formLayout.addFormItem(wingsSelect, "Wings");

        List<Location> locationList = locationRepository.findAll();
        Select<Location> locationSelect = new Select<>();
        locationSelect.setWidth("100%");
        binder.bind(locationSelect, Employee::getLocation, Employee::setLocation);
        locationSelect.setTextRenderer(Location::getName);
        locationSelect.setItems(locationList);
        formLayout.addFormItem(locationSelect, "Location");

        DatePicker joiningDate = new DatePicker();
        joiningDate.setWidth("100%");
        binder.bind(joiningDate, Employee::getJoinDate, Employee::setJoinDate);
        formLayout.addFormItem(joiningDate, "Joining Date");

        DatePicker terminationDate = new DatePicker();
        terminationDate.setWidth("100%");
        binder.bind(terminationDate, Employee::getTerminationDate, Employee::setTerminationDate);
        formLayout.addFormItem(terminationDate, "Termination Date");

        TextArea reasonOfTermination = new TextArea();
        reasonOfTermination.setWidth("100%");
        binder.bind(reasonOfTermination, Employee::getReasonOfTermination, Employee::setReasonOfTermination);
        formLayout.addFormItem(reasonOfTermination, "Reason of Termination").getElement().setAttribute("colspan", "100%");

        content.add(formLayout);
        content.add(new Hr());

        Button saveButton = new Button("Save", VaadinIcon.TABS.create());
        Button goBack = new Button("Back", VaadinIcon.CHEVRON_CIRCLE_LEFT_O.create());

        saveButton.addClickListener(e->saveEmployeeAndCreateUser(formLayout, binder));
        goBack.addClickListener(e-> goBackToPreviousPage());

        HorizontalLayout buttonGroup = new HorizontalLayout();
        buttonGroup.add(saveButton, goBack);
        content.add(buttonGroup);
        content.setAlignSelf(Alignment.END, buttonGroup);
        content.setAlignSelf(Alignment.CENTER, formLayout);

    }




    private void createAcademicInformationSection(VerticalLayout content){
        FormLayout formLayout = new FormLayout();

    }

    private void createExperienceInformationSection(VerticalLayout content){
        content.add(new Text("In Experience Section"));
    }


    @Transactional
    public void  saveEmployeeAndCreateUser(FormLayout formLayout,  Binder<Employee> binder){
        if(binder.isValid()){
            try{
                employeeRepository.save(employee);
                if(userRepository.existsById(employee.getId())){
                    Notification.show("Personal Information Updated");
                }else{
                    if(employee.getEmployeestatus().equals(Employeestatus.ACTIVE_WITH_ACCOUNT)){
                        SecureRandom secureRandom = new SecureRandom();
                        byte bytes[] = new byte[6];
                        secureRandom.nextBytes(bytes);
                        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
                        String password = encoder.encodeToString(bytes);
                        User user = createUser(employee, password);
                        userRepository.save(user);
                        Notification.show("Employee & User created with user id--->"+employee.getEmployeeId()+" and password--->"+password, 200000, Notification.Position.TOP_CENTER);

                    }else{
                        Notification.show("Personal Information Saved");
                    }
                }
                formLayout.setEnabled(false);
            }catch (Exception e){
                e.printStackTrace();
                Notification.show("Error in saving data");
                formLayout.setEnabled(true);
            }
        }
    }

    private User createUser(Employee employee, String password){
        User user = new User();
        user.setUserId(employee.getEmployeeId());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setStatus(UserStatus.ACTIVE);
        user.setLastModified(Instant.now());
        return user;
    }

    private void goBackToPreviousPage(){
        History history = UI.getCurrent().getPage().getHistory();
        history.back();
    }
}
