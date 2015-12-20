/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectioncommitee_v2;

import applicantqueue.ApplicantQueue;
import consumers.Consumer;
import consumers.ConsumerThread;
import producers.BioProducer;
import producers.MathProducer;
import producers.Producer;

/**
 * Selection commitee
 * @author Sasha
 */
public class SelectionCommitee_v2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initialize();
        System.out.println("main method is ended!");
    }
    
    /**
     * Create and execute all threads in this programm
     */
    public static void initialize() {
        ApplicantQueue queue = new ApplicantQueue();
        
        Producer bioProducer = new BioProducer(queue);
        Producer mathProducer = new MathProducer(queue);
        
        queue.setMathProducer(mathProducer);
        queue.setBioProducer(bioProducer);
        
        Consumer mathConsumer = Consumer.MATH_CONSUMER;
        Consumer bioConsumer = Consumer.BIO_CONSUMER;
        Consumer allConsumer = Consumer.ALL_CONSUMER;
        
        ConsumerThread consumerThread = new ConsumerThread(mathConsumer, 
                bioConsumer, allConsumer, mathProducer, bioProducer, queue);
        
        bioProducer.start();
        mathProducer.start();
        consumerThread.start();
    }
    
}
