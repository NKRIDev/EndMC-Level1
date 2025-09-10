package fr.nkri.zone.managers.selectors.models;

import lombok.Getter;

@Getter
public class PosSelector {

    private final double blockX, blockY;

    /**
     * Coordinate of one of the blocks clicked by the player
     *
     * @param blockX X position of the block clicked by the player
     * @param blockY Y position of the block clicked by the player
     */
    public PosSelector(double blockX, double blockY) {
        this.blockX = blockX;
        this.blockY = blockY;
    }
}
