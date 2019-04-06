package org.sofl.soptorshi.model;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String path;
    @ManyToOne
    private AcademicInformation academicInformation;
    @ManyToOne
    private TrainingInformation trainingInformation;
    @ManyToOne
    private ExperienceInformation experienceInformation;

    public Attachment(){

    }

    public Attachment(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AcademicInformation getAcademicInformation() {
        return academicInformation;
    }

    public void setAcademicInformation(AcademicInformation academicInformation) {
        this.academicInformation = academicInformation;
    }

    public TrainingInformation getTrainingInformation() {
        return trainingInformation;
    }

    public void setTrainingInformation(TrainingInformation trainingInformation) {
        this.trainingInformation = trainingInformation;
    }

    public ExperienceInformation getExperienceInformation() {
        return experienceInformation;
    }

    public void setExperienceInformation(ExperienceInformation experienceInformation) {
        this.experienceInformation = experienceInformation;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
