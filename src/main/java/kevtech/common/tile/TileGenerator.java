package kevtech.common.tile;

import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import kevtech.Coord3D;
import kevtech.Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileGenerator extends TileEnergyStorage {

	public TileGenerator() {
		super(5000);
	}

	public void updateEntity() {
		doExtract(getWorldObj());
	}

	protected void doExtract(World w) {
		for (ForgeDirection f : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity t = w.getTileEntity(xCoord + f.offsetX, yCoord + f.offsetY, zCoord + f.offsetZ);
			if (t instanceof IEnergyReceiver) {
				IEnergyReceiver r = (IEnergyReceiver) t;
				int extracted = energy.extractEnergy(energy.getMaxExtract(), true);
				int received = r.receiveEnergy(f.getOpposite(), extracted, false);
				energy.extractEnergy(received, false);
			}
		}
	}

}
