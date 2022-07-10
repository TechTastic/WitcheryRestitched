package net.techtastic.witcheryrestitched.util;

import java.util.UUID;

public interface ModdedBedBlockInterface {
    default boolean wasUsed() {
        return false;
    }

    default void setUsed(boolean sleptIn) {
    }

    default UUID getUserUuid() {
        return null;
    }

    default void setUserUuid(UUID sleptUuid) {
    }

    default String getUserName() {
        return "";
    }

    default void setUserName(String sleptName) {
    }
}
