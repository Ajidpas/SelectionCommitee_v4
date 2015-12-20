/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumers;

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
     * Rn method
     */
    @Override
    public void run() {
        Consumer activeConsumer = Consumer.MATH_CONSUMER;
        whilePoint:
        while ((queue.getSize() > 0) || (mathProducer.isAlive()) || (bioProducer.isAlive())) {
            switch (activeConsumer) {
                case MATH_CONSUMER:
                    queue.pop(mathConsumer);
                    activeConsumer = Consumer.BIO_CONSUMER;
                    break;
                case BIO_CONSUMER:
                    queue.pop(bioConsumer);
                    activeConsumer = Consumer.ALL_CONSUMER;
                    break;
                case ALL_CONSUMER:
                    int queueSize = queue.getSize();
                    int increment = queueSize > 5 ? 5 : queueSize;
                    for (int i = 0; i < increment; i++) {
                        queue.pop(allConsumer);
                    }
                    activeConsumer = Consumer.MATH_CONSUMER;
                    break;
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
