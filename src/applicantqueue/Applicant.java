/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicantqueue;

/**
 * Applicant class
 * @author Sasha
 */
public class Applicant {
    
    /** applicant id */
    private int id;
    
    /** native language exam mark */
    private int nativeLanguage;
    
    /** mathimatics exam mark */
    private int mathimatics;
    
    /** physics exam mark */ 
    private int physics;
    
    /** biology exam mark */ 
    private int biology;
    
    /** geography exam mark */ 
    private int geography;
    
    /** specialisation */ 
    private Specialisation specialisation;
    
    /**
     * Specialisation
     */
    public enum Specialisation {
        BIOLOGIST, 
        MATHEMATICIAN
    }
    
    /**
     * Default constructor
     */
    public Applicant() {}
    
    /**
     * Constructor
     * @param id applicant id
     * @param nativeLanguage
     * @param mathimatics
     * @param physics
     * @param biology
     * @param geography
     * @param specialisation 
     */
    public Applicant(int id, int nativeLanguage, int mathimatics, int physics, 
            int biology, int geography, Specialisation specialisation) {
        this.id = id;
        this.nativeLanguage = nativeLanguage;
        this.mathimatics = mathimatics;
        this.physics = physics;
        this.biology = biology;
        this.geography = geography;
        this.specialisation = specialisation;
    }
    
    /**
     * get native language
     * @return 
     */
    public int getNativeLanguage() {
        return nativeLanguage;
    }

    /**
     * Get mathimatics exam mark
     * @return 
     */
    public int getMathimatics() {
        return mathimatics;
    }

    /**
     * Get physics exam mark
     * @return 
     */
    public int getPhysics() {
        return physics;
    }

    /**
     * Get biology exam mark
     * @return 
     */
    public int getBiology() {
        return biology;
    }

    /**
     * Get geography exam mark
     * @return 
     */
    public int getGeography() {
        return geography;
    }

    /**
     * Get specialisation exam mark
     * @return 
     */
    public Specialisation getSpecialisation() {
        return specialisation;
    }
    
    /**
     * Get applicant id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Set native language exam mark
     * @param nativeLanguage 
     */
    public void setNativeLanguage(int nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    /**
     * Set mathimatics exam mark
     * @param mathimatics 
     */
    public void setMathimatics(int mathimatics) {
        this.mathimatics = mathimatics;
    }

    /**
     * Set physics exam mark
     * @param physics 
     */
    public void setPhysics(int physics) {
        this.physics = physics;
    }

    /**
     * Set biology exam mark
     * @param biology 
     */
    public void setBiology(int biology) {
        this.biology = biology;
    }

    /**
     * Set geography exam mark
     * @param geography 
     */
    public void setGeography(int geography) {
        this.geography = geography;
    }

    /**
     * Set specialisation
     * @param specialisation 
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }
    
    /**
     * Set applicant id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
}
