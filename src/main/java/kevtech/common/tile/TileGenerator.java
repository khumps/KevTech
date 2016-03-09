package kevtech.common.tile;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import kevtech.Coord3D;
import kevtech.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileGenerator extends TileEnergyStorage implements IInventory, IEnergyProvider {

	private ItemStack[] inventory;
	private boolean running = true;
	private int runTime = 200;
	private static final int RF_PER_TICK = 100;
	private Coord3D coord;

	public TileGenerator() {
		super(5000, RF_PER_TICK, RF_PER_TICK);
		inventory = new ItemStack[1];
	}

	public void updateEntity() {
		generate();
		doExtract(getWorldObj());
	}

	protected void doExtract(World w) {
		if (!worldObj.isRemote) {
			for (ForgeDirection f : ForgeDirection.VALID_DIRECTIONS) {
				TileEntity t = w.getTileEntity(xCoord + f.offsetX, yCoord + f.offsetY, zCoord + f.offsetZ);
				coord = new Coord3D(xCoord + f.offsetX, yCoord + f.offsetY, zCoord + f.offsetZ);
				if (t instanceof IEnergyReceiver) {
					IEnergyReceiver r = (IEnergyReceiver) t;
					int extracted = energy.extractEnergy(energy.getMaxExtract(), true);
					int received = r.receiveEnergy(f.getOpposite(), extracted, false);
					energy.extractEnergy(received, false);
				}
			}
		}
	}

	protected void generate() {
		if (!worldObj.isRemote) {
			System.out.println(running + " " + runTime);
			if (running) {
				energy.receiveEnergy(RF_PER_TICK, false);
				runTime--;
			}
			if (running && runTime <= 0) {
				System.out.println("stopping");
				running = false;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata() - 5, 3);
				markDirty();
			}
			if (energy.getEnergyStored() < energy.getMaxEnergyStored()) {
				if (!running && Utils.isBurnable(inventory[0])) {
					runTime += Utils.getBurnTime(inventory[0]);
					decrStackSize(0, 1);
					running = true;
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata() + 5, 3);
					markDirty();
				}

			}
		}
	}

	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return energy.extractEnergy(maxExtract, simulate);
	}

	public int getEnergyStored(ForgeDirection from) {
		return energy.getEnergyStored();
	}

	public int getMaxEnergyStored(ForgeDirection from) {
		return energy.getMaxEnergyStored();
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int numItems) {
		ItemStack temp = inventory[slot];
		if (temp.stackSize <= numItems)
			temp = null;
		else
			temp.stackSize -= numItems;
		return temp;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

}
