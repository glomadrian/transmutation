package com.glomadrian.transmutation.model;

/**
 * @author Adrian Garcia Lomas
 */
public class Employee {

    private String employeeName;
    private String employeeCompleteName;
    private int age;
    private String job;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCompleteName() {
        return employeeCompleteName;
    }

    public void setEmployeeCompleteName(String employeeCompleteName) {
        this.employeeCompleteName = employeeCompleteName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
