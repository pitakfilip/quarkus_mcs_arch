package cz.muni.soa.kingdom.domain;

import jakarta.persistence.*;

@Entity
public class Kingdom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Embedded
    private Progress progress;

    @Deprecated
    public Kingdom() {
        // JPA
    }

    public Kingdom(String name) {
        this.name = name;
        this.progress = new Progress();
    }

    /**
     * @return Current kingdom classification based on progress.
     */
    public Classification getClassification() {
        return Classification.ofLevel(progress.getLevel());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
