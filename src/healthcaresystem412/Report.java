
package healthcaresystem412;
import java.lang.System;
import java.time.LocalDate;

public class Report {
    
    private final long reportId;
    private final String reportTitle;
    //private final User user;
    private final String age;
    private final String reportText;
    private final LocalDate reportDate;
    private final String patientName;
    
    
    public Report(String patientName, String reportText, String age, String reportTitle) {
        this.patientName = patientName;
        this.reportId = System.currentTimeMillis();
        this.reportTitle = reportTitle;
        this.reportText = reportText;
        this.reportDate = LocalDate.now();
        this.age = age;
    }

    public long getReportId() {
        return reportId;
    }

    /*
    public User getUserName() {
        return user;
    }
    */

    public String getReportText() {
        return reportText;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
    
    public String getAge() {
        return age;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
 
    

}
