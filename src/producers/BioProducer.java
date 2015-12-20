/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producers;

import applicantqueue.Applicant;
import applicantqueue.ApplicantQueue;

/**
 * Producer od biologists
 * @author Sasha
 */
public class BioProducer extends Producer {
    
    /**
     * Constructor
     * @param queue applicant queue
     */
    public BioProducer(ApplicantQueue queue) {
        super(queue);
    }
    
    /**
     * Run method
     */
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            Applicant newApplicant = new Applicant(i + 10, 180, 35, 5, 200, 200, Applicant.Specialisation.BIOLOGIST);
            queue.put(newApplicant);
        }
    }
}
