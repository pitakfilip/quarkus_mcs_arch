package cz.muni.soa.foundation.domain;

public interface FoundationRepository {
    
    Foundation ofKingdom(long kingdomId);
    
    Foundation persist(Foundation foundation);
}
