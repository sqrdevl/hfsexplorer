/*-
 * Copyright (C) 2006-2007 Erik Larsson
 * 
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package org.catacombae.hfsexplorer;
import org.catacombae.hfsexplorer.partitioning.*;

/**
 * This class will contain code to identify which partitioning scheme is used, and methods
 * to retreive a list of partitions. It will operate on a simple seekable bitstream, a
 * finite string of bytes.
 */
public class PartitionSystemRecognizer {
    public static enum PartitionSystemType { APPLE_PARTITION_MAP, MASTER_BOOT_RECORD, GUID_PARTITION_TABLE, NONE_FOUND };
    private LowLevelFile bitstream;
    
    public PartitionSystemRecognizer(LowLevelFile bitstream) {
	this.bitstream = bitstream;
    }
    
    public PartitionSystemType detectPartitionSystem() {
	bitstream.seek(0);
	byte[] piece1 = new byte[512];
	bitstream.readFully(piece1);
	
	// Look for APM
	DriverDescriptorRecord ddr = new DriverDescriptorRecord(piece1, 0);
	if(ddr.isSignatureValid()) {
	    int blockSize = Util.unsign(ddr.getSbBlkSize());
	    long numberOfBlocksOnDevice = Util.unsign(ddr.getSbBlkCount());
	    //bitStream.seek(blockSize*1); // second block, first partition in list
	    ApplePartitionMap apm = new ApplePartitionMap(bitstream, blockSize*1, blockSize);
	    if(apm.getPartitionCount() > 0)
		return PartitionSystemType.APPLE_PARTITION_MAP;
	}


	// Look for GPT
	// Let's assume that blocks are always 512 bytes in size with MBR and GPT. I don't know
	// how to detect the actual block size...
	byte[] piece2 = new byte[512];
	bitstream.seek(512);
	bitstream.read(piece2);
	GPTHeader gh = new GPTHeader(piece2, 0);
	if(gh.isValid())
	    return PartitionSystemType.GUID_PARTITION_TABLE;
	
	// Look for MBR
	MBRPartitionTable mpt = new MBRPartitionTable(piece1, 0);
	if(mpt.isValid() && mpt.getPartitionCount() > 0)
	    // Here we should look for extended partitions, BSD disk labels, LVM volumes etc.
	    return PartitionSystemType.MASTER_BOOT_RECORD;
	
	return PartitionSystemType.NONE_FOUND;
    }
    
    public static void main(String[] args) throws Exception {
	LowLevelFile llf = new RandomAccessLLF(args[0]);
	System.out.println(new PartitionSystemRecognizer(llf).detectPartitionSystem().toString());
    }
}