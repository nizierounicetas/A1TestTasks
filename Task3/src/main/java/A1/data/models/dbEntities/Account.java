package A1.data.models.dbEntities;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public Department getDepartment() {
        return department;
    }

    public Application getApplication() {
        return application;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }
}

