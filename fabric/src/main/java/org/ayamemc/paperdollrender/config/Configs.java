/*
 *     Highly configurable paper doll mod, well integrated with Ayame.
 *     Copyright (C) 2024  LucunJi(Original author), CrystalNeko, HappyRespawnanchor
 *
 *     This file is part of PaperDoll Render.
 *
 *     PaperDoll Render is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     PaperDoll Render is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with PaperDoll Render.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.ayamemc.paperdollrender.config;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.ayamemc.paperdollrender.api.config.model.ConfigOption;
import org.ayamemc.paperdollrender.api.config.model.SimpleNumericOption;
import org.ayamemc.paperdollrender.api.config.model.SimpleOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.ayamemc.paperdollrender.PaperDollRender.id;

@SuppressWarnings("unused")
public class Configs {
    public static final ResourceLocation GENERAL_CATEGORY = id("general");
    public static final ResourceLocation ROTATIONS_CATEGORY = id("rotations");
    public static final ResourceLocation POSTURES_CATEGORY = id("postures");
    public static final ResourceLocation DETAILS_CATEGORY = id("details");
    public static final ResourceLocation HIDDEN_CATEGORY = id("hidden");
    public final SimpleOption<Boolean> enabled = new SimpleOption<>(GENERAL_CATEGORY, id("enabled"), true);
    public final SimpleNumericOption<Double> offsetX = new SimpleNumericOption<>(GENERAL_CATEGORY, id("offset_x"), 0.14, -0.5, 1.5);
    public final SimpleNumericOption<Double> offsetY = new SimpleNumericOption<>(GENERAL_CATEGORY, id("offset_y"), 1.27, -0.5, 2.5);
    public final SimpleNumericOption<Double> rotationX = new SimpleNumericOption<>(GENERAL_CATEGORY, id("rotation_x"), 0D, -180D, 180D);
    public final SimpleNumericOption<Double> rotationY = new SimpleNumericOption<>(GENERAL_CATEGORY, id("rotation_y"), 0D, -180D, 180D);
    public final SimpleNumericOption<Double> rotationZ = new SimpleNumericOption<>(GENERAL_CATEGORY, id("rotation_z"), 0D, -180D, 180D);
    public final SimpleNumericOption<Double> size = new SimpleNumericOption<>(GENERAL_CATEGORY, id("size"), 0.29, 0D, 2D);
    public final SimpleOption<Boolean> mirrored = new SimpleOption<>(GENERAL_CATEGORY, id("mirrored"), true);
    public final SimpleNumericOption<Double> pitch = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("pitch"), 0D, -90D, 90D);
    public final SimpleNumericOption<Double> pitchRange = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("pitch_range"), 20D, 0D, 90D);
    public final SimpleNumericOption<Double> headYaw = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("head_yaw"), -7.5D, -180D, 180D);
    public final SimpleNumericOption<Double> headYawRange = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("head_yaw_range"), 0D, 0D, 180D);
    public final SimpleNumericOption<Double> bodyYaw = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("body_yaw"), 0D, -180D, 180D);
    public final SimpleNumericOption<Double> bodyYawRange = new SimpleNumericOption<>(ROTATIONS_CATEGORY, id("body_yaw_range"), 0D, 0D, 180D);
    public final SimpleOption<PoseOffsetMethod> poseOffsetMethod = new SimpleOption<>(POSTURES_CATEGORY, id("pose_offset_method"), PoseOffsetMethod.AUTO);
    public final SimpleNumericOption<Double> sneakOffsetY = new SimpleNumericOption<>(POSTURES_CATEGORY, id("sneak_offset_y"), -0.35, -3D, 3D);
    public final SimpleNumericOption<Double> swimCrawlOffsetY = new SimpleNumericOption<>(POSTURES_CATEGORY, id("swim_crawl_offset_y"), -1.22, -3D, 3D);
    public final SimpleNumericOption<Double> elytraOffsetY = new SimpleNumericOption<>(POSTURES_CATEGORY, id("elytra_offset_y"), -1.22, -3D, 3D);
    public final SimpleOption<Boolean> hurtFlash = new SimpleOption<>(DETAILS_CATEGORY, id("hurt_flash"), true);
    public final SimpleOption<Boolean> swingHands = new SimpleOption<>(DETAILS_CATEGORY, id("swing_hands"), true);
    public final SimpleNumericOption<Double> lightDegree = new SimpleNumericOption<>(DETAILS_CATEGORY, id("light_degree"), 0D, -180D, 180D);
    public final SimpleOption<Boolean> useWorldLight = new SimpleOption<>(DETAILS_CATEGORY, id("use_world_light"), true);
    public final SimpleNumericOption<Integer> worldLightMin = new SimpleNumericOption<>(DETAILS_CATEGORY, id("world_light_min"), 2, 0, 15);
    public final SimpleOption<Boolean> renderVehicle = new SimpleOption<>(DETAILS_CATEGORY, id("render_vehicle"), true);
    public final SimpleOption<Boolean> hideUnderDebug = new SimpleOption<>(DETAILS_CATEGORY, id("hide_under_debug"), true);
    public final SimpleOption<Boolean> spectatorAutoSwitch = new SimpleOption<>(DETAILS_CATEGORY, id("spectator_auto_switch"), true);
    public final SimpleOption<String> playerName = new SimpleOption<>(DETAILS_CATEGORY, id("player_name"), "");
    public final SimpleOption<Integer> lastConfigTabIdx = new SimpleOption<>(HIDDEN_CATEGORY, id("last_config_tab_idx"), 0);
    public final Presets topLeft = new Presets.PresetsBuilder()
            .with(offsetX, 0.08)
            .with(offsetY, 0.23)
            .with(rotationX, -4.96)
            .with(rotationY, -4.96)
            .with(rotationZ, 0D)
            .with(size, 0.1)
            .with(mirrored, true)
            .build();
    public final Presets topRight = new Presets.PresetsBuilder()
            .with(offsetX, 0.91)
            .with(offsetY, 0.23)
            .with(rotationX, -4.96)
            .with(rotationY, -4.96)
            .with(rotationZ, 0D)
            .with(size, 0.1)
            .with(mirrored, false)
            .build();
    public final Presets bottomLeft = new Presets.PresetsBuilder()
            .with(offsetX, 0.14)
            .with(offsetY, 1.27)
            .with(rotationX, 0D)
            .with(rotationY, 0D)
            .with(rotationZ, 0D)
            .with(size, 0.29)
            .with(mirrored, true)
            .build();
    public final Presets bottomRight = new Presets.PresetsBuilder()
            .with(offsetX, 0.85)
            .with(offsetY, 1.27)
            .with(rotationX, 0D)
            .with(rotationY, 0D)
            .with(rotationZ, 0D)
            .with(size, 0.29)
            .with(mirrored, false)
            .build();
    private final List<? extends ConfigOption<?>> options;

    public Configs() {
        this.options = Arrays.stream(this.getClass().getFields())
                .map(field -> {
                    try {
                        return field.get(this);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(val -> val instanceof ConfigOption<?>)
                .map(f -> (ConfigOption<?>) f)
                .toList();

        var unique = new HashSet<Pair<ResourceLocation, ResourceLocation>>();
        for (ConfigOption<?> option : this.options) {
            if (!unique.add(Pair.of(option.getCategory(), option.getId()))) {
                throw new IllegalStateException("Duplicated option id: " + option.getId() + " in category " + option.getCategory());
            }
        }
    }

    public List<? extends ConfigOption<?>> getOptions() {
        return this.options;
    }

    public enum PoseOffsetMethod {
        AUTO, MANUAL, FORCE_STANDING, DISABLED
    }

    @FunctionalInterface
    public interface Presets {
        void load();

        class PresetsBuilder {
            private final List<Runnable> presets = new ArrayList<>();

            public <T> PresetsBuilder with(ConfigOption<T> option, T value) {
                this.presets.add(() -> option.setValue(value));
                return this;
            }

            public Presets build() {
                return () -> presets.forEach(Runnable::run);
            }
        }
    }
}