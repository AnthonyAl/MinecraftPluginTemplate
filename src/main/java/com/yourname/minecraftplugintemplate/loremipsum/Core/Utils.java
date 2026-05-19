package com.yourname.minecraftplugintemplate.loremipsum.Core;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import java.util.*;

@SuppressWarnings(value="unused")
public final class Utils {

    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }

    public static boolean roll20(int threshold) {
        threshold = Math.max(1, threshold);
        threshold = Math.min(20, threshold);
        return random.nextInt(1, 20) <= threshold;
    }

    public static boolean roll100(int threshold) {
        threshold = Math.max(1, threshold);
        threshold = Math.min(100, threshold);
        return random.nextInt(1, 100) <= threshold;
    }

    public static boolean roll1000(int threshold) {
        threshold = Math.max(1, threshold);
        threshold = Math.min(1000, threshold);
        return random.nextInt(1, 1000) <= threshold;
    }

    public static boolean roll(int threshold, int maximum) {
        threshold = Math.max(1, threshold);
        threshold = Math.min(maximum, threshold);
        return random.nextInt(1, maximum) <= threshold;
    }

    public static List<String> serialize(List<Component> lore) {
        if(lore == null) lore = new ArrayList<>();

        List<String> serLore = new ArrayList<>();
        for(Component line : lore) serLore.add(LegacyComponentSerializer.legacyAmpersand().serialize(line));

        return serLore;
    }

    public static List<Component> parse(List<String> serLore) {
        if(serLore == null) serLore = new ArrayList<>();

        List<Component> lore = new ArrayList<>();
        for(String line : serLore) lore.add(LegacyComponentSerializer.legacyAmpersand().deserialize(line));

        return lore;
    }

    public static String serialize(Component lore) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(lore);
    }

    public static Component parse(String serLore) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(serLore);
    }

}
