package net.techtastic.witcheryrestitched.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class DistilleryScreen extends HandledScreen<DistilleryScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(WitcheryRestitched.MOD_ID, "textures/gui/distillery_gui.png");

    public DistilleryScreen(DistilleryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if(handler.isCrafting()) {
            drawTexture(matrices, x + 63, y + 15, 176, 30, handler.getScaledCraftingProgress(), 35);
        }

        if(handler.hasPower()) {
            drawTexture(matrices, x + 29, y + 19 + 27 - handler.getScaledPowerProgress(), 184,
                    29 - handler.getScaledPowerProgress(), 11, handler.getScaledPowerProgress());
        } else {
            drawTexture(matrices, x + 30, y + 60, 176, 0, 8, 8);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
