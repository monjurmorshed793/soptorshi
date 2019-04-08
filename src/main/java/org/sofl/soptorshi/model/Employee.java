package org.sofl.soptorshi.model;

import org.sofl.soptorshi.model.enums.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private EmploymentType employmentType;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="designation_id")
    private Designation designation;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_map",
            joinColumns = {@JoinColumn(name="employee_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")}
    )
    private List<Role> role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
    private Department department;

    private LocalDate joinDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wings_id")
    private Wings wings;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Location location;

    private String fatherName;

    private String motherName;

    private String presentAddress;

    private String permanentAddress;

    private LocalDate dateOfBirth;

    private String nationalId;

    private String tinNumber;

    private String contactNumber;

    private String emailAddress;

    @Enumerated(EnumType.ORDINAL)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.ORDINAL)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private Religion religion;

    @Enumerated(EnumType.ORDINAL)
    private Employeestatus employeestatus;

    private String emergencyContact;

    private LocalDate terminationDate;

    private String reasonOfTermination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="photo_id")
    private Photo photo;

    public Employee(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Wings getWings() {
        return wings;
    }

    public void setWings(Wings wings) {
        this.wings = wings;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Employeestatus getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(Employeestatus employeestatus) {
        this.employeestatus = employeestatus;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getReasonOfTermination() {
        return reasonOfTermination;
    }

    public void setReasonOfTermination(String reasonOfTermination) {
        this.reasonOfTermination = reasonOfTermination;
    }
}
