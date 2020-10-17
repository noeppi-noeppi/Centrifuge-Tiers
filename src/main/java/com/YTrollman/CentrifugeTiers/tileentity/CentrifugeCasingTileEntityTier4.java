package com.YTrollman.CentrifugeTiers.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;

public class CentrifugeCasingTileEntityTier4 extends TileEntity {
    private BlockPos controllerPos;

    public CentrifugeCasingTileEntityTier4() {
        super(RegistryHandler.CENTRIFUGE_CASING_ENTITY_TIER_4.get());
    }

    public void setControllerPos(BlockPos pos){
        this.controllerPos = pos;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (controllerPos !=null) {
            if (this.world !=null) {
            	CentrifugeControllerTileEntityTier4 controllerTE = (CentrifugeControllerTileEntityTier4) this.world.getTileEntity(controllerPos);
                if (controllerTE !=null) {
                    if (controllerTE.validStructure) {
                        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
                            return controllerTE.lazyOptional.cast();
                        if (cap.equals(CapabilityEnergy.ENERGY)) return controllerTE.energy.cast();
                    }else{
                        controllerTE.setCasingsToNotLinked(controllerTE.buildStructureBounds());
                    }
                }else {
                    setControllerPos(null);
                }
            }
        }
        return super.getCapability(cap, side);
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT tag) {
        if (controllerPos !=null)
            tag.put("controllerPos", NBTUtil.writeBlockPos(controllerPos));
        return super.write(tag);
    }

    @Override
    public void read(@Nonnull BlockState state, CompoundNBT tag) {
        if (tag.contains("controllerPos"))
            controllerPos = NBTUtil.readBlockPos(tag.getCompound("controllerPos"));
        super.read(state, tag);
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(@Nonnull BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }
}
