package org.sofl.soptorshi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "academic_information")
public class AcademicInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Employee employee;
    private String nameOfDegree;
    private String boardOrUniversity;
    private Number passingYear;
    private String groupOrDepartment;
    @OneToMany(mappedBy = "academicInformation")
    private Set<Attachment> attachmentList = new HashSet<>();

    public AcademicInformation(){

    }

    public AcademicInformation(String nameOfDegree, String boardOrUniversity, Number passingYear) {
        this.nameOfDegree = nameOfDegree;
        this.boardOrUniversity = boardOrUniversity;
        this.passingYear = passingYear;
    }

    public String getGroupOrDepartment() {
        return groupOrDepartment;
    }

    public void setGroupOrDepartment(String groupOrDepartment) {
        this.groupOrDepartment = groupOrDepartment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(Set<Attachment> attachmentList) {
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
        return "AcademicInformation{" +
                "id=" + id +
                ", nameOfDegree='" + nameOfDegree + '\'' +
                ", boardOrUniversity='" + boardOrUniversity + '\'' +
                ", passingYear=" + passingYear +
                '}';
    }
}
