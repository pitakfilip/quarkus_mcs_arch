//package cz.muni.soa.warfare.domain.Costs;
//
//import cz.muni.soa.warfare.domain.Resource.Resource;
//import cz.muni.soa.warfare.domain.TroopClass;
//import jakarta.persistence.*;
//
//import java.util.Map;
//
//@Entity
//public class Costs {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Long id;
//
//
//    @ElementCollection
//    @Enumerated(EnumType.STRING)
//    @MapKeyJoinColumn(name = "troop_costs")
//    private Map<TroopClass, Resource> costs;
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Map<TroopClass, Resource> getCosts() {
//        return costs;
//    }
//
//    public void setCosts(Map<TroopClass, Resource> costs) {
//        this.costs = costs;
//    }
//}
