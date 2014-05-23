package mods.compactHydroGenerator;

import ic2.api.item.Items;

import java.util.logging.Level;

import mods.compactHydroGenerator.blocks.hydroGenerator;
import mods.compactHydroGenerator.tileEntities.TileEntityGeneratorHydro;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
		modid = "CompactHydroGenerator",
		name = "CompactHydroGenerator 1.5.2",
		version = "2.0.3")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true)
public class CompactHydroGenerator {

	@Instance("CompactHydroGenerator")
	public static CompactHydroGenerator instance;

	public static int Model_ID = -1;

	public static int[] COLOR_body = new int[7];

	@SidedProxy(clientSide = "mods.compactHydroGenerator.ClientProxy", serverSide = "mods.compactHydroGenerator.CommonProxy")
	public static CommonProxy proxy;

	public static boolean IC2_Installed;

	public static int[] ID_block = new int[7];
	public static Block[] BLOCK_generator = new Block[7];

	public static final CreativeTabs TABS = new CreativeTab("コンパクト\u6C34\u529B");



	public static int getColor(int blockID) {
		int index = getVer(blockID);
		return index == -1 ? 0xFFFFFF : COLOR_body[index];
	}

	public static int getVer(int blockID) {
		for(int i = 0; i < 7; i++) {
			if(blockID == ID_block[i])
				return i;
		}
		return -1;
	}

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			for(int i = 0; i < 5; i++)
				ID_block[i] = cfg.getBlock("Compact Hydro Generator MK" + (i + 1), 4014 + i).getInt();
			ID_block[5] = cfg.getBlock("Compact Hydro Generator SUPER", 4019).getInt();
			ID_block[6] = cfg.getBlock("Compact Hydro Generator HYPER", 4020).getInt();

			int[] colors;
			//色の取得(mk1)
			colors = cfg.get("COLOR","MK1-COLOR(RGB:0-255)", new int[]{255, 0, 0}).getIntList();
			COLOR_body[0] = (colors[0] % 256) << 16;
			COLOR_body[0] += (colors[1] % 256) << 8;
			COLOR_body[0] += colors[2] % 256;
			//色の取得(mk2)
			colors = cfg.get("COLOR","MK2-COLOR(RGB:0-255)", new int[]{0, 255, 0}).getIntList();
			COLOR_body[1] = (colors[0] % 256) << 16;
			COLOR_body[1] += (colors[1] % 256) << 8;
			COLOR_body[1] += colors[2] % 256;
			//色の取得(mk3)
			colors = cfg.get("COLOR","MK3-COLOR(RGB:0-255)", new int[]{255, 255, 0}).getIntList();
			COLOR_body[2] = (colors[0] % 256) << 16;
			COLOR_body[2] += (colors[1] % 256) << 8;
			COLOR_body[2] += colors[2] % 256;
			//色の取得(mk4)
			colors = cfg.get("COLOR","MK4-COLOR(RGB:0-255)", new int[]{0, 0, 255}).getIntList();
			COLOR_body[3] = (colors[0] % 256) << 16;
			COLOR_body[3] += (colors[1] % 256) << 8;
			COLOR_body[3] += colors[2] % 256;
			//色の取得(mk5)
			colors = cfg.get("COLOR","MK5-COLOR(RGB:0-255)", new int[]{255, 0, 255}).getIntList();
			COLOR_body[4] = (colors[0] % 256) << 16;
			COLOR_body[4] += (colors[1] % 256) << 8;
			COLOR_body[4] += colors[2] % 256;
			//色の取得(super)
			colors = cfg.get("COLOR","SUPER-COLOR(RGB:0-255)", new int[]{0, 255, 255}).getIntList();
			COLOR_body[5] = (colors[0] % 256) << 16;
			COLOR_body[5] += (colors[1] % 256) << 8;
			COLOR_body[5] += colors[2] % 256;
			//色の取得(hyper)
			colors = cfg.get("COLOR","HYPER-COLOR(RGB:0-255)", new int[]{0, 0, 0}).getIntList();
			COLOR_body[6] = (colors[0] % 256) << 16;
			COLOR_body[6] += (colors[1] % 256) << 8;
			COLOR_body[6] += colors[2] % 256;

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Error Message");
		} finally {
			cfg.save();
		}
	}

	@Mod.Init
	public void Init(FMLInitializationEvent event) {

		//ブロック中間処理
		initBlock();

		//テクスチャの登録
		proxy.registerTextures();
		//アニメーションの登録
		proxy.registerAnimation();
	}

	private void initBlock() {
		for(int i = 0; i < 7; i++) {
			BLOCK_generator[i] = (new hydroGenerator(ID_block[i])).setUnlocalizedName("Compact Hydro Generator MK" + (i + 1));
			GameRegistry.registerBlock(BLOCK_generator[i], "Compact Hydro Generator MK" + (i + 1));

			LanguageRegistry.addName(BLOCK_generator[i], "Compact Hydro Generator MK" + (i + 1));
			LanguageRegistry.instance().addNameForObject(BLOCK_generator[i], "ja_JP", "\u6C34\u529B\u767A\u96FB MK" + (i + 1));
			if(i == 5) {
				LanguageRegistry.addName(BLOCK_generator[i], "Compact Hydro Generator SUPER");
				LanguageRegistry.instance().addNameForObject(BLOCK_generator[i], "ja_JP", "\u6C34\u529B\u767A\u96FB \u30B9\u30FC\u30D1\u30FC");
			}
			if(i == 6) {
				LanguageRegistry.addName(BLOCK_generator[i], "Compact Hydro Generator HYPER");
				LanguageRegistry.instance().addNameForObject(BLOCK_generator[i], "ja_JP", "\u6C34\u529B\u767A\u96FB \u30CF\u30A4\u30D1\u30FC");
			}
		}
		GameRegistry.registerTileEntity(TileEntityGeneratorHydro.class, "tile.HydroGenerator");
	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event) {

		IC2_Installed = Loader.isModLoaded("IC2");

		//ブロック後半処理
		if (CompactHydroGenerator.IC2_Installed) {

				//mk1
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[0], 1),
						new Object[] { " B ", "BAB", " B ",
								Character.valueOf('A'), Items.getItem("rubberTrampoline"),
								Character.valueOf('B'), Items.getItem("waterMill") });
				//mk2
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[1], 1),
						new Object[] { "AAA", "ABA", "AAA",
								Character.valueOf('A'), BLOCK_generator[0],
								Character.valueOf('B'), Items.getItem("electronicCircuit") });
				//mk3
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[2], 1),
						new Object[] { "ABA", "BCB", "ABA",
								Character.valueOf('A'), Items.getItem("electronicCircuit"),
								Character.valueOf('B'), BLOCK_generator[1],
								Character.valueOf('C'), Item.ingotGold });
				//mk4
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[3], 1),
						new Object[] { "ABA", "BCB", "ABA",
								Character.valueOf('A'), Block.blockIron,
								Character.valueOf('B'), BLOCK_generator[2],
								Character.valueOf('C'), Items.getItem("advancedCircuit") });
				//mk5
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[4], 1),
						new Object[] { "ABA", "BCB", "ABA",
								Character.valueOf('A'), Block.blockGold,
								Character.valueOf('B'), BLOCK_generator[3],
								Character.valueOf('C'), Block.blockDiamond });
				//SUPER
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[5], 1),
						new Object[] { "ABA", "BCB", "ABA",
								Character.valueOf('A'), Block.blockDiamond,
								Character.valueOf('B'), BLOCK_generator[4],
								Character.valueOf('C'), Items.getItem("uraniumBlock") });
				//HYPER
				GameRegistry.addRecipe(new ItemStack(BLOCK_generator[6], 1),
						new Object[] { "ABA", "BCB", "ABA",
								Character.valueOf('A'), Items.getItem("uraniumBlock"),
								Character.valueOf('B'), BLOCK_generator[5],
								Character.valueOf('C'), Items.getItem("electrolyzedWaterCell") });
		}
	}

}
