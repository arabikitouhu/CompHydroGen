package mods.compactHydroGenerator;

import mods.compactHydroGenerator.renders.RenderHydroGenerators;
import mods.compactHydroGenerator.tileEntities.TileEntityGeneratorHydro;
import mods.compactHydroGenerator.tileEntities.renderers.TileEntityRendererHydroGenerators;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void registerTextures() {
	}

	@Override
	public void registerAnimation() {
		CompactHydroGenerator.Model_ID = RenderingRegistry.getNextAvailableRenderId();	//新規モデルIDの発行

		RenderingRegistry.registerBlockHandler(new RenderHydroGenerators());		//レンダー登録
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGeneratorHydro.class, new TileEntityRendererHydroGenerators());
	}



}
