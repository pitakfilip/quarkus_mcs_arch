package cz.muni.soa.kingdom.assembler;

import cz.muni.soa.kingdom.dto.DtoProgress;
import cz.muni.soa.kingdom.domain.Progress;

public class ProgressAssembler {
    
    public static DtoProgress toDto(Progress progress) {
        DtoProgress dto = new DtoProgress();
        dto.level = progress.getLevel();
        dto.experience = progress.getExperience();
        
        return dto;
    }
    
    public static Progress fromDto(DtoProgress dto) {
        Progress progress = new Progress();
        progress.setLevel(dto.level);
        progress.setExperience(dto.experience);
        
        return progress;
    }
}
