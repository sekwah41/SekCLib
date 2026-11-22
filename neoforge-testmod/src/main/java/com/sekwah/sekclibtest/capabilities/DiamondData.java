package com.sekwah.sekclibtest.capabilities;

import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;

public final class DiamondData implements IDiamondData {
    @Sync(syncGlobally = true)
    private boolean diamondMode;

    @Override
    public boolean isDiamond() { return diamondMode; }

    @Override
    public void setDiamond(boolean diamond) { this.diamondMode = diamond; }
}
