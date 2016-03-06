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
	private ForgeDirection output;

	public TileEnergyStorage() {
		energy = new EnergyStorage(50000);
		output = ForgeDirection.UP;
	}

	public TileEnergyStorage(int maxEnergy) {
		energy = new EnergyStorage(maxEnergy);
		output = ForgeDirection.EAST;
	}

	@Override
	public void updateEntity() {
		System.out.println(energy.getEnergyStored());
		doExtract(getWorldObj());
		doReceive(getWorldObj());
		System.out.println("Updating");
	}

	protected void doReceive(World w) {
		for (ForgeDirection f : ForgeDirection.VALID_DIRECTIONS) {
			if (f != output) {
				TileEntity t = w.getTileEntity(xCoord + f.offsetX, yCoord + f.offsetY, zCoord + f.offsetZ);
				if (t instanceof IEnergyProvider) {
					IEnergyProvider p = (IEnergyProvider) t;
					int extracted = p.extractEnergy(f.getOpposite(), energy.getMaxReceive(), true);
					int recieved = energy.receiveEnergy(extracted, false);
					p.extractEnergy(f, recieved, false);
				}
			}
		}
	}

	protected void doExtract(World w) {
		Coord3D offset = Utils.getAdjacent(xCoord, yCoord, zCoord, output);
		TileEntity t = w.getTileEntity(offset.x, offset.y, offset.z);
		if (t instanceof IEnergyReceiver) {
			IEnergyReceiver r = (IEnergyReceiver) t;
			int extracted = energy.extractEnergy(energy.getMaxExtract(), true);
			int received = r.receiveEnergy(output.getOpposite(), extracted, false);
			energy.extractEnergy(received, false);
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if (from == output)
			return energy.extractEnergy(maxExtract, simulate);
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
