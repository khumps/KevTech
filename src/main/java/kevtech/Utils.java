package kevtech;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class Utils {

	public static Coord3D getAdjacent(Coord3D c, ForgeDirection f) {
		return new Coord3D(c.x + f.offsetX, c.y + f.offsetY, c.z + f.offsetZ);
	}

	public static Coord3D getAdjacent(int x, int y, int z, ForgeDirection f) {
		return new Coord3D(x + f.offsetX, y + f.offsetY, z + f.offsetZ);
	}

	public static boolean isBurnable(ItemStack stack) {
		if (stack != null)
			return TileEntityFurnace.isItemFuel(stack);
		return false;
	}

	public static int getBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack);
	}
}
