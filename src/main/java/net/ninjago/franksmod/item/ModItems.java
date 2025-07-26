package net.ninjago.franksmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.ninjago.franksmod.Franksmod;
import net.ninjago.franksmod.item.custom.FranksGunItem;
import net.ninjago.franksmod.item.custom.FranksVoidItem;

public class ModItems {
    public static final RegistryObject<Item> FRANKS_GUN = Franksmod.ITEMS.register("franks_gun", () -> new FranksGunItem(new Item.Properties()));
    public static final RegistryObject<Item> FRANKS_VOID = Franksmod.ITEMS.register("franks_void", () -> new FranksVoidItem(new Item.Properties()));
    public static final RegistryObject<Item> FRANKS_TAB_ICON = Franksmod.ITEMS.register("franks_tab_icon", () -> new Item(new Item.Properties()));

    public static void registerItems() {
        Franksmod.LOGGER.info("Registering items for franks mod");
    }
}
