package com.hrms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String employeeName;
    private String email;
    private String attendance;
    @Column(columnDefinition = "TEXT")
    private String tasksPerformed;
    @Column(columnDefinition = "TEXT")
    private String technicalChallenges;
    private String projectName;
    @Column(columnDefinition = "TEXT")
    private String selfLearningTopic;
    @Column(columnDefinition = "TEXT")
    private String otherActivities;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getTasksPerformed() {
		return tasksPerformed;
	}
	public void setTasksPerformed(String tasksPerformed) {
		this.tasksPerformed = tasksPerformed;
	}
	public String getTechnicalChallenges() {
		return technicalChallenges;
	}
	public void setTechnicalChallenges(String technicalChallenges) {
		this.technicalChallenges = technicalChallenges;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSelfLearningTopic() {
		return selfLearningTopic;
	}
	public void setSelfLearningTopic(String selfLearningTopic) {
		this.selfLearningTopic = selfLearningTopic;
	}
	public String getOtherActivities() {
		return otherActivities;
	}
	public void setOtherActivities(String otherActivities) {
		this.otherActivities = otherActivities;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	

    
}

