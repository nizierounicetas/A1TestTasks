package A1.data.models.csvModels;

import com.opencsv.bean.CsvBindByName;

public class RawCsvLogin {

    @CsvBindByName(column = "Application")
    private String application;

    @CsvBindByName(column = "AppAccountName")
    private String appAccountName;

    @CsvBindByName(column = "IsActive")
    private String isActive;

    @CsvBindByName(column = "JobTitle")
    private String jobTitle;

    @CsvBindByName(column = "Department")
    private String department;

    public String getApplication() {
        return application;
    }

    public String getAppAccountName() {
        return appAccountName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getDepartment() {
        return department;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setAppAccountName(String appAccountName) {
        this.appAccountName = appAccountName;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return String.format("Application: %s; AppAccountName: %s; IsActive: %s; JobTitle: %s; Department: %s",
                application, appAccountName, isActive, jobTitle, department);
    }
}

