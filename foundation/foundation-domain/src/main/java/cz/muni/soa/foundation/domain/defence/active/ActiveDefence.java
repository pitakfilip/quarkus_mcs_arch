package cz.muni.soa.foundation.domain.defence.active;

import cz.muni.soa.foundation.domain.defence.Defence;

public abstract class ActiveDefence extends Defence {
    
    private int dps;

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }
}
