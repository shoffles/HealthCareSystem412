
package healthcaresystem412;
import java.lang.System;
import java.time.LocalDate;

public class Report {
    
    private long reportId;
    private LocalDate reportDate;
    private String reportTitle;
    private String reportBody;
    
    private String username;
    private String name;
    private String dob;
    
    
    
    public Report(String title, String body, String username, String name, String dob) {
        this.reportDate = LocalDate.now();
        this.reportTitle = title;
        this.reportBody = body;
        this.username = username;
        this.name = name;
        this.dob = dob; 
    }

    public long getReportId() {
        return reportId;
    }

    public String getReportDate() {
        return reportDate.toString();
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public String getReportBody() {
        return reportBody;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

   
 
    

}
