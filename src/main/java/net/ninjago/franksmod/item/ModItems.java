package net.ninjago.franksmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjago.franksmod.Franksmod;
import net.ninjago.franksmod.item.custom.FranksGunItem;
import net.ninjago.franksmod.item.custom.FranksVoidItem;
import net.ninjago.franksmod.item.custom.SuperHoeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Franksmod.MODID);

    public static final RegistryObject<Item> FRANKS_GUN = ITEMS.register("franks_gun", () -> new FranksGunItem(new Item.Properties()));
    public static final RegistryObject<Item> FRANKS_VOID = ITEMS.register("franks_void", () -> new FranksVoidItem(new Item.Properties()));
    public static final RegistryObject<Item> FRANKS_TAB_ICON = ITEMS.register("franks_tab_icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SUPER_HOE = ITEMS.register("super_hoe", () -> new SuperHoeItem(new Item.Properties()
            .defaultDurability(250))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
