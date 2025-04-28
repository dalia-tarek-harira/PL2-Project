/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

public class Bugs {

    private int bugId;
    private String bugName;
    private String bugType;
    private String priority;
    private String bugLevel;
    private static String bugStatus;
    private String bugDate;
    private String bugScreenshot;
    private String projectName;

    public Bugs() {
        bugStatus = "Opened";
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel;
    }

    public void setBugDate(String bugDate) {
        this.bugDate = bugDate;
    }

    public void setBugScreenshot(String bugScreenshot) {
        this.bugScreenshot = bugScreenshot;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getBugId() {
        return this.bugId;
    }

    public String getBugName() {
        return this.bugName;
    }

    public String getBugType() {
        return this.bugType;
    }

    public String getPriority() {
        return this.priority;
    }

    public String getBugLevel() {
        return this.bugLevel;
    }

    public String getBugStatus() {
        return Bugs.bugStatus;
    }

    public String getBugDate() {
        return this.bugDate;
    }

    public String getBugScreenshot() {
        return this.bugScreenshot;
    }

    public String getProjectName() {
        return this.projectName;
    }
}
