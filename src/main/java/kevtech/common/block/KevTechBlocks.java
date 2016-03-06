package kevtech.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import kevtech.KevTech;
import kevtech.common.tile.TileEnergyStorage;
import kevtech.common.tile.TileGenerator;

public class KevTechBlocks {

	/* Blocks */
	public static BlockEnergyStorage energyCell = new BlockEnergyStorage("energy_cell");
	public static BlockGenerator generator = new BlockGenerator("generator");

	public static void registerBlocks() {
		GameRegistry.registerBlock(energyCell, "Energy Cell");
		GameRegistry.registerBlock(generator, "Generator");
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEnergyStorage.class, KevTech.modID + "energy_cell");
		GameRegistry.registerTileEntity(TileGenerator.class, "generator");

	}
}
