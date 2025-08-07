package com.moltencore.pocket_sand;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PocketSandMod.MODID);

    public static final RegistryObject<EntityType<SandpileEntity>> SANDPILE =
            ENTITIES.register("sandpile", () -> EntityType.Builder.<SandpileEntity>of(
                            SandpileEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(64) // Makes sure it’s rendered at distance
                    .updateInterval(10)       // How often to sync
                    .setShouldReceiveVelocityUpdates(true) // Required for movement to sync
                    .build("sandpile"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
