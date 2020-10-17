package com.YTrollman.CentrifugeTiers.gui.screen;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier4;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.resourcefulbees.resourcefulbees.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;

public class CentrifugeMultiblockScreenTier4 extends ContainerScreen<CentrifugeMultiblockContainerTier4> {
    public CentrifugeMultiblockScreenTier4(CentrifugeMultiblockContainerTier4 screenContainer, PlayerInventory inventory, ITextComponent titleIn) {
        super(screenContainer, inventory, titleIn);
        this.xSize = 176;
        this.ySize = 228;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
        Minecraft client = this.minecraft;
        if (client != null) {
            client.getTextureManager().bindTexture(new ResourceLocation(CentrifugeTiers.MOD_ID,"textures/gui/multiblock_centrifuge_tier_4.png"));
            int i = this.guiLeft;
            int j = this.guiTop;
            this.blit(matrix, i, j, 0, 0, this.xSize, this.ySize);
            int scaledprogress1 = 16 * this.container.getTime(0) / Math.max(this.container.centrifugeTileEntityTier4.totalTime,1);
            this.blit(matrix, i + 52, j + 26, 176, 1, 16, scaledprogress1);
            int scaledprogress2 = 16 * this.container.getTime(1) / Math.max(this.container.centrifugeTileEntityTier4.totalTime,1);
            this.blit(matrix, i + 70, j + 26, 176, 1, 16, scaledprogress2);
            int scaledprogress3 = 16 * this.container.getTime(2) / Math.max(this.container.centrifugeTileEntityTier4.totalTime,1);
            this.blit(matrix, i + 88, j + 26, 176, 1, 16, scaledprogress3);
            int scaledprogress4 = 16 * this.container.getTime(3) / Math.max(this.container.centrifugeTileEntityTier4.totalTime,1);
            this.blit(matrix, i + 106, j + 26, 176, 1, 16, scaledprogress4);
            int scaledprogress5 = 16 * this.container.getTime(4) / Math.max(this.container.centrifugeTileEntityTier4.totalTime,1);
            this.blit(matrix, i + 124, j + 26, 176, 1, 16, scaledprogress5);
            int scaledRF = 58 * this.container.getEnergy() / Math.max(Config.MAX_CENTRIFUGE_RF.get() * 5,1);
            this.blit(matrix, i + 10, j + 38 + (58-scaledRF), 176, 28 + (58-scaledRF), 12, scaledRF);
        }
    }

    @Override
    public void render(@Nonnull MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrix, mouseX, mouseY);
        DecimalFormat decimalFormat = new DecimalFormat("##0.0");
        if (mouseX >= this.guiLeft + 10 && mouseX <= this.guiLeft + 22 && mouseY >= this.guiTop + 38 && mouseY <= this.guiTop + 96){
            if (Screen.hasShiftDown() || this.container.getEnergy() < 500) this.renderTooltip(matrix, new StringTextComponent(this.container.getEnergy() + " RF"), mouseX, mouseY);
            else this.renderTooltip(matrix, new StringTextComponent(decimalFormat.format((double)this.container.getEnergy() / 1000) + " kRF"), mouseX, mouseY);
        }
    }

    @Override
    public void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrix, int mouseX, int mouseY) {
    }
}
