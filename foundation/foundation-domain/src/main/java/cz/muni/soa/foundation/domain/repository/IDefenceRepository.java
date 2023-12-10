package cz.muni.soa.foundation.domain.repository;

import cz.muni.soa.foundation.domain.defence.Defence;

import java.util.List;

public interface IDefenceRepository {
    
    void persist(Defence defence);
    
    void persist(List<Defence> defences);
    
}
