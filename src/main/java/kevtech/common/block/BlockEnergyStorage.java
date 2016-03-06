package kevtech.common.block;

import kevtech.common.tile.TileEnergyStorage;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyStorage extends BaseBlockTE {

	public BlockEnergyStorage(String name) {
		super(Material.rock, name);
	}

	public TileEntity createNewTileEntity(World world, int var) {
		System.out.println("Placed Energy Cell");
		return new TileEnergyStorage();
	}

}
