package cz.muni.soa.kingdom.domain;

public enum Classification {
    
    Novice(1, 3),
    Apprentice(4, 9),
    Prodigy(10, 15),
    Expert(16, 22),
    Master(23, 29),
    Legend(30, 30);
    
    public final int levelFrom;
    public final int levelTo;

    Classification(int levelFrom, int levelTo) {
        this.levelFrom = levelFrom;
        this.levelTo = levelTo;
    }
    
    public static Classification ofLevel(int level) {
        for (Classification c : Classification.values()) {
            if (c.levelFrom <= level && level <= c.levelTo)
                return c;
        }
        
        return null;
    }
    
}
