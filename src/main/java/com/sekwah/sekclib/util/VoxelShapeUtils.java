package com.sekwah.sekclib.util;

import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

/**
 * Just a couple of utils for manipulating voxel shapes.
 */
public class VoxelShapeUtils {

    public static VoxelShape scale(VoxelShape shape, double scale) {
        return scale(shape, scale, scale, scale);
    }

    /**
     * This is mostly in case you are feeding in data such as basing bounding boxes on the obj models.
     *
     * @param shape
     * @param scaleX
     * @param scaleY
     * @param scaleZ
     * @return
     */
    public static VoxelShape scale(VoxelShape shape, double scaleX, double scaleY, double scaleZ) {
        if(shape.isEmpty()) {
            return Shapes.empty();
        }
        List<VoxelShape> boxes = new ArrayList<>();
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            boxes.add(Shapes.create(minX * scaleX, minY * scaleY, minZ * scaleZ, maxX * scaleX, maxY * scaleY, maxZ * scaleZ));
        });
        return boxes.stream().reduce(Shapes.empty(), Shapes::or);
    }


    /**
     * This is mostly a function to help our mods for processing bounding boxes created from checking blender models.
     *
     *
     *
     * @return shifted and scaled VoxelShape
     */
    public static VoxelShape prepBlenderCoords(int alterX) {
        return Shapes.empty();

    }

    /**
     * Rotations will be applied around the bottom left front block but the id will still be the same.
     *
     * The order will be going, along +X, back +Z and up +Y.
     *
     * That way the multiblock structures just need to remember which id they are in the structure and return the rotated shape.
     *
     * This should also align with how the models are selected.
     *
     * Just to note if you are using blender at least the z axis is the y axis, the y axis is the z axis
     *
     * example for 3x1x2 block
     *
     * 54                   035                    01
     * 32 - 90 clockwise -> 124 - 90 clockwise ->  23
     * 01                                          45
     *
     * @param shape the shape to slice with 0,0,0 being the bottom left front of the block.
     * @param sizeX in the default rotation, how many blocks in the x coordinate it is
     * @param sizeY the height of the shape
     * @param sizeZ the depth of the shape
     * @return four rotations and slices of the voxelshape [rotations 0-3][slices 0-length]
     */
    public static VoxelShape[][] sliceAndRotate(VoxelShape shape, int sizeX, int sizeY, int sizeZ) {
        return new VoxelShape[][]{};
    }
}
