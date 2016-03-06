package kevtech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import kevtech.common.block.KevTechBlocks;

@Mod(modid = "kevtech", name = "Kev Tech", version = "0.0.1")
public class KevTech {

	public static String modID = "kevtech";
	public static String version = "0.0.1";
	public static TabKev tabKev = new TabKev("Kev Tech");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		KevTechBlocks.registerBlocks();
		KevTechBlocks.registerTileEntities();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {

	}
}
