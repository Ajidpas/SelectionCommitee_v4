/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producers;

import applicantqueue.Applicant;
import applicantqueue.Applicant.Specialisation;
import applicantqueue.ApplicantQueue;

/**
 * Producer of mathematicians
 * @author Sasha
 */
public class MathProducer extends Producer {
    
    /**
     * Constructor
     * @param queue applicant queue
     */
    public MathProducer(ApplicantQueue queue) {
        super(queue);
    }
    
    /**
     * Run method
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Applicant newApplicant = new Applicant(i + 100, 180, 200, 200, 35, 5, Specialisation.MATHEMATICIAN);
            queue.put(newApplicant);
        }
    }
}
