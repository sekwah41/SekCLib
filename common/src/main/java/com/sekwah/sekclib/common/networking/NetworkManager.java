package com.sekwah.sekclib.common.networking;

import com.google.common.base.Objects;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class NetworkManager {

    public static class ChannelBuilder {

        /***
         * Must be unique
         */
        private ResourceLocation id;
        private Supplier<String> protocolVersion;
        private Predicate<String> doesClientSupport = s -> {
            return Objects.equal(s, protocolVersion.get());
        };
        private Predicate<String> doesServerSupport = s -> {
            return Objects.equal(s, protocolVersion.get());
        };
        private Predicate<NetworkChannel> registerPackets;

        public static ChannelBuilder id(ResourceLocation id) {
            ChannelBuilder builder = new ChannelBuilder();
            builder.id = id;
            return new ChannelBuilder();
        }

        public ChannelBuilder networkProtocolVersion(Supplier<String> networkProtocolVersion)
        {
            this.protocolVersion = networkProtocolVersion;
            return this;
        }

        public ChannelBuilder doesClientSupport(Predicate<String> clientAcceptedVersions)
        {
            this.doesClientSupport = clientAcceptedVersions;
            return this;
        }
        public ChannelBuilder registerPackets(Predicate<NetworkChannel> registerPackets)
        {
            this.registerPackets = registerPackets;
            return this;
        }
    }
}
