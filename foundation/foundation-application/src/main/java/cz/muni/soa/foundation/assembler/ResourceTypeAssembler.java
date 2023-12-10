package cz.muni.soa.foundation.assembler;

import cz.muni.soa.foundation.domain.resource.ResourceType;
import cz.muni.soa.foundation.dto.DtoResourceType;

public class ResourceTypeAssembler {
    
    public static DtoResourceType toDto(ResourceType resourceType) {
        return DtoResourceType.valueOf(resourceType.name());
    }
    
    public static ResourceType fromDto(DtoResourceType dto) {
        return ResourceType.valueOf(dto.name());
    }
}
