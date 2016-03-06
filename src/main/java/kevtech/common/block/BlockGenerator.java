package kevtech.common.block;

import kevtech.KevTech;
import kevtech.common.tile.TileGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockGenerator extends BaseBlockTE {

	public IIcon front;
	public IIcon side;
	public IIcon lit;

	protected BlockGenerator(String name) {
		super(Material.rock, name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var) {
		return new TileGenerator();
	}

	public void registerBlockIcons(IIconRegister register) {
		front = register.registerIcon(KevTech.modID + ":generator_front");
		side = register.registerIcon(KevTech.modID + ":generator_side");
		lit = register.registerIcon(KevTech.modID + ":generator_lit");
	}

	public IIcon getIcon(int side, int meta) {
		if (side == meta - 5)
			return lit;
		if (side == meta)
			return front;
		return this.side;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
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

		if (item.hasDisplayName()) {
			((TileEntityFurnace) world.getTileEntity(x, y, z)).func_145951_a(item.getDisplayName());
		}
	}

}
