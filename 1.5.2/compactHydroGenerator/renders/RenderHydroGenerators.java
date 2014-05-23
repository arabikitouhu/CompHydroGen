package mods.compactHydroGenerator.renders;

import mods.compactHydroGenerator.CompactHydroGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderHydroGenerators implements ISimpleBlockRenderingHandler {


	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(modelID == this.getRenderId()) {
			int color = CompactHydroGenerator.getColor(block.blockID);

			Tessellator tessellator = Tessellator.instance;
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		//下
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1F, 0.0F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(0));
			tessellator.draw();
		//上
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(1));
			tessellator.setColorRGBA_I(block.getBlockColor(), 255);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(6));	//重ね(上下)
			tessellator.draw();
		//東
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(7));	//重ね(歯車)
			tessellator.draw();
		//西
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(3));
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(7));	//重ね(歯車)
			tessellator.draw();
		//北
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1F, 0.0F, 0.0F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(4));
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(7));	//重ね(歯車)
			tessellator.draw();
		//南
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.setColorRGBA_I(color, 255);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(5));
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(7));	//重ね(歯車)
			tessellator.draw();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return CompactHydroGenerator.Model_ID;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
		return false;
	}

}
