package kevtech.common.block;

import kevtech.KevTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BaseBlock extends Block {

	public BaseBlock(Material material, String name) {
		super(material);
		setBlockName(KevTech.modID + "_" + name);
		setBlockTextureName(KevTech.modID + ":" + name);
		setCreativeTab(KevTech.tabKev);
	}

}
