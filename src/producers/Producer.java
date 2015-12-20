/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producers;

import applicantqueue.ApplicantQueue;

/**
 *
 * @author Sasha
 */
public abstract class Producer extends Thread {
    
    /** applicant queue */
    protected final ApplicantQueue queue;
    
    /**
     * Constructor
     * @param queue applicant queue
     */
    public Producer(ApplicantQueue queue) {
        this.queue = queue;
    }
}
