package com.hrms.dto;

public class HiringDto {
    private String candidate_info;
	private String jobOpenings;
    private String Status;
    private String lastEmail;
    

    public HiringDto(){

    }
    public HiringDto(String candidate_info, String jobOpenings, String Status, String lastEmail){
       super();
        this.candidate_info= candidate_info;
        this.jobOpenings=jobOpenings;
        this.Status=Status;
        this.lastEmail=lastEmail;
        
    }
    public String getcandidate_info() {
        return candidate_info;
    }

    public void setName(String candidate_info) {
        this.candidate_info = candidate_info;
    }

    public String getjobOpenings () {
        return jobOpenings;
    }

    public void setjobOpenings(String jobOpenings) {
        this.jobOpenings = jobOpenings;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getlastEmail() {
        return lastEmail;
    }

    public void setlastEmail(String lastEmail) {
        this.lastEmail = lastEmail;
    }

}
