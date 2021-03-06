package micdoodle8.mods.galacticraft.core.tile;

import java.util.ArrayList;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.tileentity.TileEntity;
import universalelectricity.core.vector.Vector3;

/**
 * GCCoreTileEntityBuggyFuelerSingle.java
 *
 * This file is part of the Galacticraft project
 *
 * @author micdoodle8
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class GCCoreTileEntityBuggyFuelerSingle extends TileEntity
{
    @Override
    public void updateEntity()
    {
        final ArrayList<TileEntity> attachedLaunchPads = new ArrayList<TileEntity>();

        for (int x = this.xCoord - 1; x < this.xCoord + 2; x++)
        {
            for (int z = this.zCoord - 1; z < this.zCoord + 2; z++)
            {
                final Vector3 vector = new Vector3(x, this.yCoord, z);

                final TileEntity tile = vector.getTileEntity(this.worldObj);

                if (tile != null && tile instanceof GCCoreTileEntityBuggyFuelerSingle)
                {
                    attachedLaunchPads.add(tile);
                }
            }
        }

        if (attachedLaunchPads.size() == 9)
        {
            for (final TileEntity tile : attachedLaunchPads)
            {
                tile.invalidate();
                tile.worldObj.setBlock(tile.xCoord, tile.yCoord, tile.zCoord, 0);
            }

            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, GCCoreBlocks.landingPadFull.blockID, 1, 3);
            final GCCoreTileEntityBuggyFueler tile = (GCCoreTileEntityBuggyFueler) this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);

            if (tile instanceof IMultiBlock)
            {
                ((IMultiBlock) tile).onCreate(new Vector3(this.xCoord, this.yCoord, this.zCoord));
            }
        }
    }
}
