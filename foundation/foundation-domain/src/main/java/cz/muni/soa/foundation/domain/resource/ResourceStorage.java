package cz.muni.soa.foundation.domain.resource;

import jakarta.persistence.Embeddable;

@Embeddable
public class ResourceStorage {
    
    private long wood;
    
    private long iron;
    
    private long gold;

    public ResourceStorage() {
        wood = 0;
        iron = 0;
        gold = 0;
    }
    
    public long getResource(ResourceType type) {
        return switch (type){
            case WOOD -> wood;
            case IRON -> iron;
            case GOLD -> gold;
        };                
    }

    public void add(ResourceType type, long amount) {
        switch (type) {
            case WOOD:
                wood = wood + amount;
                return;
            case IRON:
                iron = iron + amount;
                return;
            case GOLD:
                gold = gold + amount;
                return;
            default:
                return;
        }
    }
    
    public void subtract(ResourceType type, long amount) {
        if (getResource(type) < amount)
            return;
        
        switch (type) {
            case WOOD:
                wood = wood - amount;
                return;
            case IRON:
                iron = iron - amount;
                return;
            case GOLD:
                gold = gold - amount;
                return;
            default:
                return;
        }
    }
    

    public long getWood() {
        return wood;
    }

    public void setWood(long wood) {
        this.wood = wood;
    }

    public long getIron() {
        return iron;
    }

    public void setIron(long iron) {
        this.iron = iron;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }
}
