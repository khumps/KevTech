package kevtech;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabKev extends CreativeTabs {
	private Item icon = null;

	public TabKev(String label) {
		super(label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		if (icon == null)
			icon = Items.diamond;
		return icon;
	}

}
