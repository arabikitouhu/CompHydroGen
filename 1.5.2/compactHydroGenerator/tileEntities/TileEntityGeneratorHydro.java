package mods.compactHydroGenerator.tileEntities;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileSourceEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.network.NetworkHelper;
import ic2.api.tile.IWrenchable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import mods.compactHydroGenerator.CompactHydroGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityGeneratorHydro extends TileEntity implements IEnergySource, INetworkDataProvider, INetworkUpdateListener, IWrenchable {
	private boolean initialized;
	public int tick;
	private int energyProduction = 0;
	protected Random rand;

	public TileEntityGeneratorHydro() {
		super();
		this.rand = new Random();
		this.tick = 10;
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, Direction direction) {
		return true;
	}

	@Override
	public void updateEntity() {

		if (!initialized && worldObj != null) {
			if (worldObj.isRemote) {
				if(CompactHydroGenerator.IC2_Installed) {
					NetworkHelper.requestInitialData(this); }
			} else {
				EnergyTileLoadEvent loadEvent = new EnergyTileLoadEvent(this);
				MinecraftForge.EVENT_BUS.post(loadEvent);
			}
			int blockID = worldObj.getBlockId(xCoord, yCoord, zCoord);
			worldObj.notifyBlockOfNeighborChange(xCoord, yCoord, zCoord, blockID);
			initialized = true;
		}

		if (tick-- == 0) {
			energyProduction = updateGenState();
			tick = 10;
		}

		if (energyProduction > 0) {
		    EnergyTileSourceEvent sourceEvent = new EnergyTileSourceEvent(this, energyProduction);
		    MinecraftForge.EVENT_BUS.post(sourceEvent);

		}
	}

	public int updateGenState() {

		//初期化
		int energyProduction = 0;

		//周囲3x3 水の数計測
		for(int dx=-1; dx<2; dx++) {
			for(int dy=-1; dy<2; dy++) {
				for(int dz=-1; dz<2; dz++) {
					if(worldObj.getBlockMaterial(xCoord + dx, yCoord + dy, zCoord + dz).equals(Material.water))
						energyProduction++;
		} } }

			return this.getMaxEnergyOutput() * energyProduction / 25;

	}
	public int generateEnergy() {
		return this.energyProduction;
	}

	@Override
	public boolean isAddedToEnergyNet() {
		return initialized;
	}

	@Override
	public void onNetworkUpdate(String field) { }

	private static List<String> fields=Arrays.asList(new String[0]);
	@Override
	public List<String> getNetworkedFields() {
		return fields;
	}

	@Override
	public int getMaxEnergyOutput() {
		int meta = CompactHydroGenerator.getVer(worldObj.getBlockId(xCoord, yCoord, zCoord));
		if(meta == 0) return 1;
		if(meta == 1) return 8;
		if(meta == 2) return 32;
		if(meta == 3) return 128;
		if(meta == 4) return 512;
		if(meta == 5) return 2048;
		if(meta == 6) return 8192;
		return 1;
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
		return false;
	}

	@Override
	public short getFacing() { return 0; }

	@Override
	public void setFacing(short facing) { }

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) { return true; }

	@Override
	public float getWrenchDropRate() { return 1.0F; }

	public void invalidate() {
		EnergyTileUnloadEvent unloadEvent = new EnergyTileUnloadEvent(this);
		MinecraftForge.EVENT_BUS.post(unloadEvent);
		super.invalidate();
	}

	@Override
	public boolean canUpdate(){
		return this.worldObj != null;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		int meta = CompactHydroGenerator.getVer(worldObj.getBlockId(xCoord, yCoord, zCoord));
		if(meta == -1) return null;
		return new ItemStack(CompactHydroGenerator.BLOCK_generator[meta], 1);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		tick = nbttagcompound.getInteger("tick");	//NBTからtickを読み取る
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("tick", tick);	// NBTにtickの記憶領域を確保
	}

	//パケットの送信
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tileTag = new NBTTagCompound();
		this.writeToNBT(tileTag);
		return new Packet132TileEntityData(
				this.xCoord, this.yCoord, this.zCoord, 0, tileTag );
	}

	//パケットの受信
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}
}
