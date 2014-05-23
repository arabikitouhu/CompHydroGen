package mods.compactHydroGenerator.blocks;

import mods.compactHydroGenerator.CompactHydroGenerator;
import mods.compactHydroGenerator.tileEntities.TileEntityGeneratorHydro;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class hydroGenerator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public hydroGenerator(int id) {
		super(id, Material.iron);

		setHardness(3.0F);
		setResistance(2000.0F);									// 爆破耐性 黒曜石　同等
		setCreativeTab(CompactHydroGenerator.TABS);		// クリエイティブタブ
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGeneratorHydro();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.textures = new Icon[ ] {
			iconRegister.registerIcon("compactHydroGenerator:Skin1"),
			iconRegister.registerIcon("compactHydroGenerator:Skin2"),
			iconRegister.registerIcon("compactHydroGenerator:Skin3"),
			iconRegister.registerIcon("compactHydroGenerator:Equip1"),
			iconRegister.registerIcon("compactHydroGenerator:Equip2")
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if(side == 0)
			return textures[0];
		else if(side == 1)
			return textures[1];
		else if(side >= 2 && side <= 5)
			return textures[2];
		else if(side == 6)
			return textures[3];
		else if(side == 7)
			return textures[4];
		else
			return blockIcon;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return CompactHydroGenerator.Model_ID;
	}

}
