package mods.compactHydroGenerator;

import ic2.api.item.Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs
{

	public CreativeTab(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
		if(CompactHydroGenerator.IC2_Installed){
			return Items.getItem("waterMill");
		} else {
			return new ItemStack(Block.blockDiamond);
		}
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "コンパクト\u6C34\u529B";
	}
}