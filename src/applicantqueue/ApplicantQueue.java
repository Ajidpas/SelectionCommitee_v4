/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicantqueue;

import applicantqueue.Applicant.Specialisation;
import consumers.Consumer;
import java.util.LinkedList;
import java.util.Queue;
import producers.Producer;

/**
 * Applicant queue that keeps applicants temporary 
 * @author Sasha
 */
public class ApplicantQueue {
    
    /** applicants queue */
    private Queue<Applicant> applicants = new LinkedList<>();
    
    /** producer of mathematicians */
    private Producer mathProducer;
    
    /** producer of biologists */
    private Producer bioProducer;
    
    /** max capacity of applicants queue */
    private static final int MAX_CAPACITY = 50;
    
    /** minimum capacity of applicants queue */
    private static final int MIN_CAPACITY = 25;
    
    /**
     * Put applicant into the applicant queue
     * @param newApplicant new applicant migth be added
     */
    public synchronized void put(Applicant newApplicant) {
        if (newApplicant == null) {
            return;
        }
        while (applicants.size() >= MAX_CAPACITY) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception was occured: " + e.getMessage());
            }
        }
        applicants.add(newApplicant);
        this.notifyAll();
    }
    
    /**
     * Return and remove (if it is need) applicant from the queue
     * @param consumer next consumer in the queue of consumers
     */
    public synchronized void pop(Consumer consumer) {
        while ((applicants.size() <= MIN_CAPACITY) && (mathProducer.isAlive() || bioProducer.isAlive())) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception was occured: " + e.getMessage());
            }
        }
        if (applicants.size() > 0) {
            switch (consumer) {
                case MATH_CONSUMER:
                    if (applicants.peek().getSpecialisation().equals(Specialisation.MATHEMATICIAN)) {
                        Applicant newApplicant = applicants.poll();
                        consumer.put(newApplicant);
                    }
                    break;
                case BIO_CONSUMER:
                    if (applicants.peek().getSpecialisation().equals(Specialisation.BIOLOGIST)) {
                        Applicant newApplicant = applicants.poll();
                        consumer.put(newApplicant);
                    }
                    break;
                case ALL_CONSUMER:
                    Applicant newApplicant = applicants.poll();
                    consumer.put(newApplicant);
                    break;
            }
        }
        this.notifyAll();
    }
    
    /**
     * Return size of the applicants queue
     * @return size of the applicants queue
     */
    public synchronized int getSize() {
        return applicants.size();
    }
    
    /**
     * Set producer object that produses of mathematicians
     * @param mathProducer producer object that produses of mathematicians
     */
    public void setMathProducer(Producer mathProducer) {
        this.mathProducer = mathProducer;
    }
    
    /**
     * Set producer object that produses of biologists
     * @param bioProducer producer object that produses of biologists 
     */
    public void setBioProducer(Producer bioProducer) {
        this.bioProducer = bioProducer;
    }
}
