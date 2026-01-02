package com.thenikehades.addon.modules;

import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.utils.player.FindItemResult;
import meteordevelopment.meteorclient.utils.player.InvUtils;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class NikeDupe extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final SettingGroup sgVisuals = settings.createGroup("Visuals");

    // --- GENEL AYARLAR ---
    private final Setting<List<Item>> whitelist = sgGeneral.add(new ItemListSetting.Builder().name("Items").description("Dupe yapılacak eşyalar (Boşsa her şeyi yapar).").build());
    private final Setting<Double> range = sgGeneral.add(new DoubleSetting.Builder().name("Range").defaultValue(4.5).min(1.0).sliderMax(6.0).build());
    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder().name("Tick Delay").defaultValue(0).min(0).sliderMax(20).build());
    private final Setting<Boolean> multiTask = sgGeneral.add(new BoolSetting.Builder().name("Multi Task").description("Aynı anda tüm çerçeveleri işler.").defaultValue(true).build());
    private final Setting<Boolean> autoReplace = sgGeneral.add(new BoolSetting.Builder().name("Auto Replace").description("Kırılan çerçeveleri geri koyar.").defaultValue(true).build());

    // --- GÖRSEL AYARLAR ---
    private final Setting<Boolean> render = sgVisuals.add(new BoolSetting.Builder().name("Render Box").defaultValue(true).build());
    private final Setting<SettingColor> sideColor = sgVisuals.add(new ColorSetting.Builder().name("Nike Color").defaultValue(new SettingColor(255, 255, 0, 80)).build());

    private final Map<BlockPos, Direction> lastPosMap = new HashMap<>();
    private int ticksPassed = 0;

    public NikeDupe() {
        super(Categories.Misc, "TheNikeHades-Dupe", "Profesyonel Multi-Tasking Item Frame Dupe Otomasyonu.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null || mc.world == null) return;

        if (ticksPassed < delay.get()) {
            ticksPassed++;
            return;
        }
        ticksPassed = 0;

        // Menzildeki çerçeveleri tara
        List<ItemFrameEntity> frames = mc.world.getEntitiesByClass(ItemFrameEntity.class, 
            mc.player.getBoundingBox().expand(range.get()), f -> f != null && f.isAlive());

        // Konumları Replace için kaydet
        for (ItemFrameEntity f : frames) lastPosMap.put(f.getBlockPos(), f.getHorizontalFacing());

        // AUTO REPLACE MEKANİZMASI
        if (autoReplace.get() && frames.isEmpty() && !lastPosMap.isEmpty()) {
            FindItemResult frameItem = InvUtils.findInHotbar(Items.ITEM_FRAME);
            if (frameItem.found()) {
                for (Map.Entry<BlockPos, Direction> entry : lastPosMap.entrySet()) {
                    if (mc.player.getPos().distanceTo(entry.getKey().toCenterPos()) <= range.get()) {
                        InvUtils.swap(frameItem.slot(), true);
                        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, new BlockHitResult(entry.getKey().toCenterPos(), entry.getValue(), entry.getKey(), false));
                        InvUtils.swapBack();
                        if (!multiTask.get()) break;
                    }
                }
            }
        }

        // MULTI-TASK DUPE DÖNGÜSÜ
        for (ItemFrameEntity frame : frames) {
            if (frame.getHeldItemStack().isEmpty()) {
                // Eşya Koy
                for (int i = 0; i < 9; i++) {
                    Item item = mc.player.getInventory().getStack(i).getItem();
                    if (item != Items.AIR && item != Items.ITEM_FRAME && (whitelist.get().isEmpty() || whitelist.get().contains(item))) {
                        InvUtils.swap(i, true);
                        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interact(frame, false, Hand.MAIN_HAND));
                        InvUtils.swapBack();
                        break; 
                    }
                }
            } else {
                // Tokatla (Vur)
                mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(frame, false));
            }

            // Multi Task kapalıysa her tick sadece 1 işlem yap
            if (!multiTask.get()) break;
        }
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        if (!render.get()) return;
        mc.world.getEntitiesByClass(ItemFrameEntity.class, mc.player.getBoundingBox().expand(range.get()), e -> true)
            .forEach(f -> event.renderer.box(f.getBoundingBox(), sideColor.get(), sideColor.get(), ShapeMode.Both, 0));
    }
}
