package mods.compactHydroGenerator.tileEntities.renderers;

import mods.compactHydroGenerator.CompactHydroGenerator;
import mods.compactHydroGenerator.tileEntities.TileEntityGeneratorHydro;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class TileEntityRendererHydroGenerators extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float envTime) {
		 this.renderTileEntityAt_Generator(tileEntity, x, y, z, envTime);
	}

	//座標(x,y,z)を中心に下面テクスチャを表示する（ボード）side=0
	private void createFaceBottom(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  0.0D, 0.0D,  1.0D };	//左上
		double[] p_rt = {  1.0D, 0.0D,  1.0D };	//右上
		double[] p_lb = {  0.0D, 0.0D,  0.0D };	//左下
		double[] p_rb = {  1.0D, 0.0D,  0.0D };	//右下

		tes.setNormal(0.0F, -1.0F, 0.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_min, v_max);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_max, v_min);
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_min);

	}

	//座標(x,y,z)を中心に上面テクスチャを表示する（ボード）side=1
	private void createFaceTop(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  0.0D, 1.0D,  1.0D };	//左上
		double[] p_rt = {  1.0D, 1.0D,  1.0D };	//右上
		double[] p_lb = {  0.0D, 1.0D,  0.0D };	//左下
		double[] p_rb = {  1.0D, 1.0D,  0.0D };	//右下

		tes.setNormal(0.0F, 1.0F, 0.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_min);
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_max, v_min);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_min, v_max);

	}

	//座標(x,y,z)を中心に東側テクスチャを表示する（ボード）side=2
	private void createFaceEast(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  1.0D, 1.0D, 0.0D };	//左上
		double[] p_rt = {  1.0D, 1.0D, 1.0D };	//右上
		double[] p_lb = {  1.0D, 0.0D, 0.0D };	//左下
		double[] p_rb = {  1.0D, 0.0D, 1.0D };	//右下

		tes.setNormal(0.0F, 0.0F, -1.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_min);
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_max, v_min);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_min, v_max);

	}

	//座標(x,y,z)を中心に西側テクスチャを表示する（ボード）side=3
	private void createFaceWest(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  0.0D, 1.0D, 0.0D };	//左上
		double[] p_rt = {  0.0D, 1.0D, 1.0D };	//右上
		double[] p_lb = {  0.0D, 0.0D, 0.0D };	//左下
		double[] p_rb = {  0.0D, 0.0D, 1.0D };	//右下

		tes.setNormal(0.0F, 0.0F, 1.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_min, v_max);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_max, v_min);
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_min);

	}

	//座標(x,y,z)を中心に北側テクスチャを表示する（ボード）side=4
	private void createFaceNorth(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  1.0D, 0.0D, 0.0D };	//左上
		double[] p_rt = {  1.0D, 1.0D, 0.0D };	//右上
		double[] p_lb = {  0.0D, 0.0D, 0.0D };	//左下
		double[] p_rb = {  0.0D, 1.0D, 0.0D };	//右下

		tes.setNormal(-1.0F, 0.0F, 0.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_max);
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_min);
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_min, v_min);
	}

	//座標(x,y,z)を中心に南側テクスチャを表示する（ボード）side=5
	private void createFaceSouth(Tessellator tes, double x, double y, double z, int iconIndex) {

		//TextureUVの計算
		double u_min = (double)(iconIndex & 0xF) / 16.0D;
		double u_max = u_min + 1.0D / 16.0D;

		double v_min = (double)((iconIndex >> 4) & 0xF) / 16.0D;
		double v_max = v_min + 1.0D / 16.0D;

		//左手座標系と右手座標系の違いは、ｚ軸    だったはず
		//vertexの四辺計算(x,y,z)

		double[] p_lt = {  1.0D, 0.0D, 1.0D };	//左上
		double[] p_rt = {  1.0D, 1.0D, 1.0D };	//右上
		double[] p_lb = {  0.0D, 0.0D, 1.0D };	//左下
		double[] p_rb = {  0.0D, 1.0D, 1.0D };	//右下

		tes.setNormal(1.0F, 0.0F, 0.0F);		//法線の設定

		//ポリゴンの設定
		tes.addVertexWithUV(x + p_rt[0], y+ p_rt[1], z+ p_rt[2], u_min, v_min);
		tes.addVertexWithUV(x + p_rb[0], y+ p_rb[1], z+ p_rb[2], u_max, v_min);
		tes.addVertexWithUV(x + p_lb[0], y+ p_lb[1], z+ p_lb[2], u_max, v_max);
		tes.addVertexWithUV(x + p_lt[0], y+ p_lt[1], z+ p_lt[2], u_min, v_max);
	}

	//座標(x,y,z)にシンプルブロックを表示する。
	private void createStanderdBlock(Tessellator tes, double x, double y, double z, int color){
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceTop(tes, x, y, z, 0);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceBottom(tes, x, y, z, 1);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceEast(tes, x, y, z, 2);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceWest(tes, x, y, z, 2);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceNorth(tes, x, y, z, 2);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceSouth(tes, x, y, z, 2);
		tes.draw();
	}

	//座標(x,y,z)に四方向テクスチャを表示する。
	private void createStanderdWall(Tessellator tes, double x, double y, double z, int color){
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceEast(tes, x, y, z, 3);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceWest(tes, x, y, z, 3);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceNorth(tes, x, y, z, 3);
		tes.draw();
		tes.startDrawingQuads();
		tes.setColorRGBA_I(color, 255);
		this.createFaceSouth(tes, x, y, z, 3);
		tes.draw();
	}

	private void renderTileEntityAt_Generator(TileEntity tileEntity, double x, double y, double z, float envTime) {

		Tessellator tes = Tessellator.instance;			//テセレータの用意

        GL11.glEnable(GL11.GL_TEXTURE_2D);
		this.bindTextureByName("/mods/compactHydroGenerator/textures/blocks/generator.png");	//テクスチャのバインド
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);

		int color = CompactHydroGenerator.getColor(tileEntity.worldObj.getBlockId(tileEntity.xCoord,tileEntity.yCoord, tileEntity.zCoord));
		int frame = 4;
		int gen = ((TileEntityGeneratorHydro)tileEntity).updateGenState();
		if(gen > 0) frame = 26 - (int)(10 * envTime);

		this.createStanderdBlock(tes, x, y, z, color);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		tes.startDrawingQuads();
		this.createFaceTop(tes, x, y, z, frame);		//上
		tes.draw();

		this.createStanderdWall(tes, x, y, z, color);	//側面

		GL11.glDisable(GL11.GL_BLEND);

	}

}
