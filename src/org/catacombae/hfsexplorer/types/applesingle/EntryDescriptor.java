/*-
 * Copyright (C) 2008 Erik Larsson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catacombae.hfsexplorer.types.applesingle;

import java.io.PrintStream;
import org.catacombae.util.Util;

/** This class was generated by CStructToJavaClass. */
public class EntryDescriptor {
    /*
     * struct EntryDescriptor
     * size: 12 bytes
     * description: 
     * 
     * BP  Size  Type    Identifier   Description
     * ------------------------------------------
     * 0   4     UInt32  entryId                 
     * 4   4     UInt32  entryOffset             
     * 8   4     UInt32  entryLength             
     */
    
    public static final int STRUCTSIZE = 12;
    
    private final byte[] entryId = new byte[4];
    private final byte[] entryOffset = new byte[4];
    private final byte[] entryLength = new byte[4];
    
    public EntryDescriptor(byte[] data, int offset) {
	System.arraycopy(data, offset+0, entryId, 0, 4);
	System.arraycopy(data, offset+4, entryOffset, 0, 4);
	System.arraycopy(data, offset+8, entryLength, 0, 4);
    }
    
    public static int length() { return STRUCTSIZE; }
    
    /**  */
    public int getEntryId() { return Util.readIntBE(entryId); }
    /**  */
    public int getEntryOffset() { return Util.readIntBE(entryOffset); }
    /**  */
    public int getEntryLength() { return Util.readIntBE(entryLength); }
    
    public void printFields(PrintStream ps, String prefix) {
	ps.println(prefix + " entryId: " + getEntryId());
	ps.println(prefix + " entryOffset: " + getEntryOffset());
	ps.println(prefix + " entryLength: " + getEntryLength());
    }
    
    public void print(PrintStream ps, String prefix) {
	ps.println(prefix + "EntryDescriptor:");
	printFields(ps, prefix);
    }
    
    public byte[] getBytes() {
	byte[] result = new byte[STRUCTSIZE];
	int offset = 0;
	System.arraycopy(entryId, 0, result, offset, entryId.length); offset += entryId.length;
	System.arraycopy(entryOffset, 0, result, offset, entryOffset.length); offset += entryOffset.length;
	System.arraycopy(entryLength, 0, result, offset, entryLength.length); offset += entryLength.length;
	return result;
    }
}