package net.techtastic.witcheryrestitched.util;

import java.util.UUID;

public interface ModdedBedBlockInterface {
    boolean WitcheryRestitched$wasUsed();

    void WitcheryRestitched$setUsed(boolean sleptIn);

    UUID WitcheryRestitched$getUserUuid();

    void WitcheryRestitched$setUserUuid(UUID sleptUuid);

    String WitcheryRestitched$getUserName();

    void WitcheryRestitched$setUserName(String sleptName);
}
