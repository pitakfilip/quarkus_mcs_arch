package cz.muni.soa.kingdom.repository;

import cz.muni.soa.kingdom.domain.Kingdom;

public interface IKingdomRepository {
    
    Kingdom getById(long id);

    /**
     * Create or update kingdom instance.
     * @param kingdom
     */
    void persist(Kingdom kingdom);
}
