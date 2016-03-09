package kevtech.common.block;

import kevtech.common.tile.TileEnergyStorage;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockEnergyStorage extends BaseBlockTE {

	public BlockEnergyStorage(String name) {
		super(Material.rock, name);
	}

	public TileEntity createNewTileEntity(World world, int var) {
		System.out.println("Placed Energy Cell");
		return new TileEnergyStorage();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (stack.hasDisplayName()) {
			((TileEntityFurnace) world.getTileEntity(x, y, z)).func_145951_a(stack.getDisplayName());
		}
	}

}
