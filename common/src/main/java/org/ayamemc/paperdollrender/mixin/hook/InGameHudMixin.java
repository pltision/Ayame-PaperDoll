/*
 *     Highly configurable paper doll mod, well integrated with Ayame.
 *     Copyright (C) 2024  LucunJi(Original author), CrystalNeko, HappyRespawnanchor, pertaz(Icon Designer)
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

package org.ayamemc.paperdollrender.mixin.hook;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.ayamemc.paperdollrender.PaperDollRender;
import org.ayamemc.paperdollrender.hud.ExtraPlayerHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * <h2>
 * Order of rendering HUDs (as of 1.21):
 * </h2>
 * <br/>
 * ({@link net.minecraft.client.Options#hideGui} controls all but {@link Gui#renderSleepOverlay})
 * <br/>
 * <ul>
 *     <li>{@link Gui#renderCameraOverlays}</li>
 *     <ul>
 *         <li>spyglass</li>
 *         <li>pumpkin</li>
 *         <li>powdered snow</li>
 *         <li>portal</li>
 *     </ul>
 *     <li>{@link Gui#renderCrosshair}</li>
 *     <li>{@link Gui#renderHotbarAndDecorations}</li>
 *     <ul>
 *         <li>spectator menu / hotbar</li>
 *         <li>mount jumping bar / experience bar</li>
 *         <li>mount health</li>
 *         <li>handheld item tooltip / spectator menu tooltip</li>
 *     </ul>
 *     <li>{@link Gui#renderExperienceLevel} (the text)</li>
 *     <li>{@link Gui#renderEffects}</li>
 *     <li>{@link Gui#bossBarHud}</li>
 *     <li>{@link Gui#renderSleepOverlay}</li>
 *     <li>{@link Gui#renderDemoOverlay}</li>
 *     <li>{@link Gui#debugHud}</li>
 *     <li>{@link Gui#renderScoreboardSidebar}</li>
 *     <li>{@link Gui#renderOverlayMessage}</li>
 *     <li>{@link Gui#renderTitle} (message given by /title)</li>
 *     <li>{@link Gui#renderChat}</li>
 *     <li>{@link Gui#renderTabList}</li>
 *     <li>{@link Gui#subtitlesHud} (sound subtitles)</li>
 * </ul>
 */
@Mixin(Gui.class)
public class InGameHudMixin {
    //@Shadow
    @Final
    private Minecraft client = Minecraft.getInstance();
    @Unique
    private ExtraPlayerHud extraPlayerHud;

    /**
     * Initialization should go after initialization to prevent using uninitialized fields.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    public void onInit(Minecraft client, CallbackInfo ci) {
        this.extraPlayerHud = new ExtraPlayerHud(this.client);
    }

    /**
     * Should render when:
     * <ul>
     *     <li>Hud is NOT hidden (F1),</li>
     *     <li>and NOT showing debug HUD (F3) or the corresponding option is turned off,</li>
     *     <li>game is not showing any screen</li>
     * </ul>
     * <p>
     * By injecting into one of the stages,
     * we hook ourselves into {@link Gui#layers},
     * and need to obey the rules of {@link net.minecraft.client.gui.LayeredDraw} (especially in {@link net.minecraft.client.gui.LayeredDraw#renderInner})
     * to act like a layer of HUD.
     */
    @Inject(method = "renderCameraOverlays", at = @At("RETURN"))
    void onRenderMiscOverlayFinish(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (!this.client.options.hideGui
                && !(PaperDollRender.CONFIGS.hideUnderDebug.getValue() && this.client.getDebugOverlay().showDebugScreen())
                && this.client.screen == null) {
            this.extraPlayerHud.render(tickCounter.getGameTimeDeltaPartialTick(true));
        }
        // follow convention in LayeredDrawer#renderInternal
        context.pose().translate(0, 0, 200);
    }

    public void setClient(Minecraft client) {
        this.client = client;
    }
}
