package kevtech.common.block;

import kevtech.KevTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BaseBlockTE extends BlockContainer {

	protected BaseBlockTE(Material material, String name) {
		super(material);
		setBlockName(KevTech.modID + "_" + name);
		setBlockTextureName(KevTech.modID + ":" + name);
		setCreativeTab(KevTech.tabKev);
	}

	@Override
	public abstract TileEntity createNewTileEntity(World world, int var);

}
