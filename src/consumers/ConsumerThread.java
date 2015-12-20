/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumers;

import applicantqueue.Applicant;
import applicantqueue.Applicant.Specialisation;
import applicantqueue.ApplicantQueue;
import producers.Producer;

/**
 * Thread that deligates with three university consumers
 * @author Sasha
 */
public class ConsumerThread extends Thread {
    
    /** applicant queue */
    private final ApplicantQueue queue;
    
    /** producer of biologists */
    private final Producer bioProducer;
    
    /** producers of mathematicians */
    private final Producer mathProducer; 
    
    /** consumer of mathematicians */
    private final Consumer mathConsumer;
    
    /** consumer of biologists */
    private final Consumer bioConsumer;
    
    /** consumer of all students */
    private final Consumer allConsumer;

    /**
     * Constructor 
     * @param mathConsumer consumer of mathematicians
     * @param bioConsumer consumer of biologists
     * @param allConsumer consumer of all students
     * @param mathProducer producer of mathematicians
     * @param bioProducer producer of biologists
     * @param queue applicant queue
     */
    public ConsumerThread(Consumer mathConsumer, Consumer bioConsumer, 
            Consumer allConsumer, Producer mathProducer, Producer bioProducer, 
            ApplicantQueue queue) {
        this.mathConsumer = mathConsumer;
        this.bioConsumer = bioConsumer;
        this.allConsumer = allConsumer;
        this.mathProducer = mathProducer;
        this.bioProducer = bioProducer;
        this.queue = queue;
    }
    
    /**
     * Run method
     */
    @Override
    public void run() {
        int inc = 0;
        Consumer activeConsumer = Consumer.MATH_CONSUMER;
        whilePoint:
        while ((queue.getSize() > 0) || (mathProducer.isAlive()) || (bioProducer.isAlive())) {
            Applicant newApplicant = queue.pop();
            if (newApplicant == null) {
                continue;
            }
            switch (activeConsumer) {
                case MATH_CONSUMER:
                    if (newApplicant.getSpecialisation().equals(Specialisation.MATHEMATICIAN)) {
                        mathConsumer.put(newApplicant);
                        break;
                    }
                    activeConsumer = Consumer.BIO_CONSUMER;
                case BIO_CONSUMER:
                    if (newApplicant.getSpecialisation().equals(Specialisation.BIOLOGIST)) {
                        bioConsumer.put(newApplicant);
                        break;
                    }
                    activeConsumer = Consumer.ALL_CONSUMER;
                case ALL_CONSUMER:
                    allConsumer.put(newApplicant);
                    if ((++inc) >= 5) {
                        inc = 0;
                        activeConsumer = Consumer.MATH_CONSUMER;
                    }
            }
        }
        System.out.println("Math consumer students:");
        mathConsumer.printStudents();
        System.out.println("---------------------------------------------------");
        System.out.println("Bio consumer students:");
        bioConsumer.printStudents();
        System.out.println("---------------------------------------------------");
        System.out.println("All consumer students:");
        allConsumer.printStudents();
        System.out.println("Counter = " + (mathConsumer.getStudents().size() + 
                bioConsumer.getStudents().size() + allConsumer.getStudents().size()));
    }
    
}
