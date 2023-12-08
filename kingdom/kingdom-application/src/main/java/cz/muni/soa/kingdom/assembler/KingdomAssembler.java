package cz.muni.soa.kingdom.assembler;

import cz.muni.soa.kingdom.dto.DtoClassification;
import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.kingdom.domain.Kingdom;

import java.util.List;

public class KingdomAssembler {
    
    public static DtoKingdom toDto (Kingdom kingdom) {
        DtoKingdom dto = new DtoKingdom();
        dto.id = kingdom.getId();
        dto.name = kingdom.getName();
        dto.progress = ProgressAssembler.toDto(kingdom.getProgress());
        dto.classification = DtoClassification.valueOf(kingdom.getClassification().name());
        
        return dto;
    }
    
    public static List<DtoKingdom> toDto (List<Kingdom> kingdoms) {
        return kingdoms.stream()
                .map(KingdomAssembler::toDto)
                .toList();
    }
    
    public static Kingdom fromDto (DtoKingdom dto) {
        Kingdom k = new Kingdom(dto.id, dto.name);
        k.setProgress(ProgressAssembler.fromDto(dto.progress));
        
        return k;
    }
    
    public static List<Kingdom> fromDto (List<DtoKingdom> dtos) {
        return dtos.stream()
                .map(KingdomAssembler::fromDto)
                .toList();
    }
}
