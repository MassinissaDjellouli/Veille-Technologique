package com.massinissadjellouli.RPGmod.networking.packet;

import com.massinissadjellouli.RPGmod.client.ClientThirstData;
import com.massinissadjellouli.RPGmod.thirst.PlayerThirst;
import com.massinissadjellouli.RPGmod.thirst.PlayerThirstProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket {

        private final int thirst;
        public ThirstDataSyncS2CPacket(int thirst){
            this.thirst = thirst;

        }
        public ThirstDataSyncS2CPacket(FriendlyByteBuf buf){
            this.thirst = buf.readInt();

        }

        public void toBytes(FriendlyByteBuf buf){
            buf.writeInt(thirst);
        }

        public boolean handle(Supplier<NetworkEvent.Context> supplier){
            NetworkEvent.Context context = supplier.get();

            context.enqueueWork(()->{

            ClientThirstData.set(thirst);
            });
            return true;
        }




}