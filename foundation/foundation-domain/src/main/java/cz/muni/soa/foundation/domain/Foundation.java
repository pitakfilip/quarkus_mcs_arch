package cz.muni.soa.foundation.domain;

import cz.muni.soa.foundation.domain.defence.Defence;
import cz.muni.soa.foundation.domain.resource.ResourceStorage;
import cz.muni.soa.foundation.domain.resource.producer.ResourceProducer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Foundation {

    @Id
    @GeneratedValue
    private Long id;

    private Long kingdomId;

    @Embedded
    private ResourceStorage storage;

    @OneToMany
    @JoinTable(
            name = "defences",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "defence_id")
    )
    private List<Defence> defences;

    @OneToMany
    @JoinTable(
            name = "producers",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private List<ResourceProducer> producers;

    public Foundation() {
    }

    public Foundation(long kingdomId) {
        this.kingdomId = kingdomId;
        defences = new ArrayList<>();
        producers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(Long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public ResourceStorage getStorage() {
        return storage;
    }

    public void setStorage(ResourceStorage storage) {
        this.storage = storage;
    }

    public List<Defence> getDefences() {
        return defences;
    }

    public void setDefences(List<Defence> defences) {
        this.defences = defences;
    }

    public List<ResourceProducer> getProducers() {
        return producers;
    }

    public void setProducers(List<ResourceProducer> producers) {
        this.producers = producers;
    }
}
