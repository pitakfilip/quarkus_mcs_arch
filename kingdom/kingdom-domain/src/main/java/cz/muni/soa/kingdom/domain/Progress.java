package cz.muni.soa.kingdom.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class Progress {
    
    private int level;
    
    private int experience;
    
    @Transient
    private final Integer MAX_LEVEL = 50;

    private static int STARTING_MAX_EXP = 250;

    /**
     * Difference between level's max_experience, creating an arithmetic sequence 
     * final max_experience of level = STARTING_MAX_EXP + ( (level - 1) * LEVEL_EXP_INCREMENT )
     */
    private static int LEVEL_EXP_INCREMENT = 25;
    
    public Progress() {
        level = 1;
        experience = 0;
    }
    
    public int getMaxExperience() {
        return STARTING_MAX_EXP + (level * LEVEL_EXP_INCREMENT);
    }

    public void addExperience(int amount) {
        while (amount > 0) {
            if (level == MAX_LEVEL)
                break;
            
            int levelUp = (getMaxExperience() - experience);
            if ( levelUp < amount ) {
                experience += amount;
                break;
            }
            
            // Adding experience leads to level-up
            amount -= levelUp;
            experience = 0;
            level += 1;    
        }
    }
    
    
    
    // GETTERS SETTERS
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
