package net.techtastic.witcheryrestitched.screen;

import com.google.common.collect.Sets;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.techtastic.witcheryrestitched.WitcheryRestitched;

public class AltarScreen extends HandledScreen<AltarScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(WitcheryRestitched.MOD_ID, "textures/gui/altar_gui.png");

    public AltarScreen(AltarScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        this.backgroundWidth = 154;
        this.backgroundHeight = 81;
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(title)) / 2;
        this.titleY = this.backgroundHeight - 70;
        super.init();
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        int maxPower = handler.getMaxPower();
        int currentPower = handler.getCurrentPower();
        int rate = handler.getRate();

        Text altarDisplay = Text.literal(currentPower + " / " + maxPower + " (x" + rate + ")");
        int altarDisplayX = this.width / 2;
        int altarDisplayY = this.height / 2;
        drawCenteredText(matrices, this.textRenderer, altarDisplay, altarDisplayX, altarDisplayY, 0xFFFFFF);

        //drawCenteredText(matrices, this.textRenderer, this.title, this.titleX, this.titleY, 0xFFFFFF);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {

        this.textRenderer.draw(matrices, this.title, (float)this.titleX, (float)this.titleY, 0xFFFFFF);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
