package kevtech.common.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import kevtech.Coord3D;
import kevtech.Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEnergyStorage extends TileEntity implements IEnergyHandler {

	protected EnergyStorage energy;
	private ForgeDirection output = null;

	public TileEnergyStorage() {
		energy = new EnergyStorage(50000);
	}

	public TileEnergyStorage(int maxEnergy) {
		energy = new EnergyStorage(maxEnergy);
		output = ForgeDirection.EAST;
	}

	public TileEnergyStorage(int maxEnergy, int maxIn, int maxOut) {
		energy = new EnergyStorage(maxEnergy, maxIn, maxOut);
		output = ForgeDirection.EAST;
	}

	@Override
	public void updateEntity() {
		if (output == null)
			output = ForgeDirection.getOrientation(getBlockMetadata());
		System.out.println(output);
		doExtract(getWorldObj());
		doReceive(getWorldObj());
		// System.out.println("Updating");
	}

	protected void doReceive(World w) {
		if (!worldObj.isRemote) {
			for (ForgeDirection f : ForgeDirection.VALID_DIRECTIONS) {
				if (f != output) {
					TileEntity t = w.getTileEntity(xCoord + f.offsetX, yCoord + f.offsetY, zCoord + f.offsetZ);
					if (t instanceof IEnergyProvider) {
						IEnergyProvider p = (IEnergyProvider) t;

						int extracted = p.extractEnergy(f.getOpposite(), energy.getMaxReceive(), true);
						int recieved = energy.receiveEnergy(extracted, false);
						p.extractEnergy(f, recieved, false);

						// System.out.println(p.extractEnergy(f.getOpposite(),
						// energy.getMaxReceive(), true));
						// receiveEnergy(f, p.extractEnergy(f.getOpposite(),
						// energy.getMaxReceive(), false), false);
					}
				}
			}
			System.out.println("ENERGY STORAGE" + energy.getEnergyStored());
		}
	}

	protected void doExtract(World w) {
		if (!worldObj.isRemote) {
			Coord3D offset = Utils.getAdjacent(xCoord, yCoord, zCoord, output);
			TileEntity t = w.getTileEntity(offset.x, offset.y, offset.z);
			if (t instanceof IEnergyReceiver) {
				IEnergyReceiver r = (IEnergyReceiver) t;
				int extracted = energy.extractEnergy(energy.getMaxExtract(), true);
				int received = r.receiveEnergy(output.getOpposite(), extracted, false);
				energy.extractEnergy(received, false);
			}
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		TileEntity t = worldObj.getTileEntity(xCoord + from.offsetX, yCoord + from.offsetY, xCoord + from.offsetZ);
		if (from.getOpposite() == output && t instanceof IEnergyReceiver)
			return true;
		if (from.getOpposite() != output && t instanceof IEnergyProvider)
			return true;
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if (!worldObj.isRemote) {
			if (from.getOpposite() != output) {
				TileEntity t = worldObj.getTileEntity(xCoord + from.offsetX, yCoord + from.offsetY,
						xCoord + from.offsetZ);
				if (t instanceof IEnergyProvider) {
					IEnergyProvider p = (IEnergyProvider) t;
					int extracted = p.extractEnergy(from.getOpposite(), energy.getMaxReceive(), true);
					int recieved = energy.receiveEnergy(extracted, false);
					return p.extractEnergy(from, recieved, false);

				}
			}
		}

		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if (!worldObj.isRemote) {
			if (from == output)
				return energy.extractEnergy(maxExtract, simulate);
		}
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energy.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return energy.getMaxEnergyStored();
	}

}
