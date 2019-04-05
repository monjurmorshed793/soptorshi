package org.sofl.soptorshi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "academic_information")
public class EmployeeAcademicInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id")
    private Employee employee;
    private String nameOfDegree;
    private String boardOrUniversity;
    private Number passingYear;
    @JoinTable(
            name="academic_attachment_map",
            joinColumns = {@JoinColumn(name="academic_information_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "attachment_id", referencedColumnName = "id")}
    )
    private List<Attachment> attachmentList;

    public EmployeeAcademicInformation(){

    }

    public EmployeeAcademicInformation(String nameOfDegree, String boardOrUniversity, Number passingYear) {
        this.nameOfDegree = nameOfDegree;
        this.boardOrUniversity = boardOrUniversity;
        this.passingYear = passingYear;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfDegree() {
        return nameOfDegree;
    }

    public void setNameOfDegree(String nameOfDegree) {
        this.nameOfDegree = nameOfDegree;
    }

    public String getBoardOrUniversity() {
        return boardOrUniversity;
    }

    public void setBoardOrUniversity(String boardOrUniversity) {
        this.boardOrUniversity = boardOrUniversity;
    }

    public Number getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(Number passingYear) {
        this.passingYear = passingYear;
    }

    @Override
    public String toString() {
        return "EmployeeAcademicInformation{" +
                "id=" + id +
                ", nameOfDegree='" + nameOfDegree + '\'' +
                ", boardOrUniversity='" + boardOrUniversity + '\'' +
                ", passingYear=" + passingYear +
                '}';
    }
}
