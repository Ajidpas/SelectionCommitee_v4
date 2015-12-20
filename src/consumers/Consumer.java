/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumers;

import applicantqueue.Applicant;
import java.util.ArrayList;
import java.util.List;

/**
 * Consumer enum class
 * @author Sasha
 */
public enum Consumer {
    MATH_CONSUMER,
    BIO_CONSUMER,
    ALL_CONSUMER;
    
    /** list of students */
    private List<Applicant> students;

    /**
     * Constructor
     */
    private Consumer() {
        this.students = new ArrayList<>();
    }
    
    /**
     * Add new applicants to student list
     * @param newApplicant 
     */
    public void put(Applicant newApplicant) {
        students.add(newApplicant);
    }
    
    /**
     * Get all students of this university
     * @return 
     */
    public List<Applicant> getStudents() {
        return students;
    }
    
    /**
     * Print all students of this university
     */
    public void printStudents() {
        for (Applicant applicant : students) {
            System.out.println(applicant.getSpecialisation().toString());
        }
    }
    
}
