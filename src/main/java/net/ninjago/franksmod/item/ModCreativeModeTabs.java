package net.ninjago.franksmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.ninjago.franksmod.Franksmod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Franksmod.MODID);

    public static final RegistryObject<CreativeModeTab> FRANKS_MOD_TAB = CREATIVE_MODE_TABS.register("frank_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.FRANKS_TAB_ICON.get()))
                    .title(Component.literal("Frank's Totally Awesome Stuff"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.FRANKS_GUN.get());
                        pOutput.accept(ModItems.FRANKS_VOID.get());
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
