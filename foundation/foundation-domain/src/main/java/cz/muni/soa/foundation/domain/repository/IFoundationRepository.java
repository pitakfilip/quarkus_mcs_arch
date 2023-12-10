package cz.muni.soa.foundation.domain.repository;

import cz.muni.soa.foundation.domain.Foundation;

public interface IFoundationRepository {
    
    Foundation ofKingdom(long kingdomId);
    
    void persist(Foundation foundation);
}
