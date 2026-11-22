package com.sekwah.sekclib.util;

import com.sekwah.sekclib.network.SekCPacketHandler;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class PlayerUtil {
    private PlayerUtil() {
    }

    public static void setVelocity(Player player, Vec3 velocity, boolean ignoreGround) {
        player.setDeltaMovement(velocity);
        if (velocity.y > 0) player.fallDistance = 0;
        if (player instanceof ServerPlayer serverPlayer) {
            if (ignoreGround)
                SekCPacketHandler.sendVelocity(serverPlayer, (int) (Mth.clamp(velocity.x, -3.9, 3.9) * 8000), (int) (Mth.clamp(velocity.y, -3.9, 3.9) * 8000), (int) (Mth.clamp(velocity.z, -3.9, 3.9) * 8000));
            else serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(player.getId(), velocity));
        }
    }

    public static void setVelocity(Player player, double x, double y, double z, boolean ignoreGround) {
        setVelocity(player, new Vec3(x, y, z), ignoreGround);
    }
}
