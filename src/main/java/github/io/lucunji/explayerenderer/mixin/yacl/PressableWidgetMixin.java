package github.io.lucunji.explayerenderer.mixin.yacl;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import github.io.lucunji.explayerenderer.Main;
import github.io.lucunji.explayerenderer.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PressableWidget.class)
public abstract class PressableWidgetMixin {
    @Unique
    private static final ButtonTextures TEXTURES = new ButtonTextures(
            Identifier.of(Main.MOD_ID, "widget/button"),
            Identifier.of(Main.MOD_ID, "widget/button_disabled"),
            Identifier.of(Main.MOD_ID, "widget/button_highlighted"));

    @WrapOperation(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ButtonTextures;get(ZZ)Lnet/minecraft/util/Identifier;"))
    public Identifier drawTransparentButtonTexture(ButtonTextures instance, boolean enabled, boolean focused, Operation<Identifier> original) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (!Configs.isConfigScreen(minecraftClient.currentScreen))
            return original.call(instance, enabled, focused);
        return TEXTURES.get(enabled, focused);
    }
}