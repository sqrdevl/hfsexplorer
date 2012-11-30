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

package org.catacombae.hfs.types.finder;

import java.io.PrintStream;
import java.lang.reflect.Field;
import org.catacombae.csjc.StructElements;
import org.catacombae.csjc.structelements.Dictionary;
import org.catacombae.hfs.types.carbon.Point;
import org.catacombae.util.Util;

/** This class was generated by CStructToJavaClass. */
public class DXInfo implements StructElements {
    /*
     * struct DXInfo
     * size: 16 bytes
     * description:
     *
     * BP  Size  Type    Identifier   Description
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 0   4     Point   frScroll     Scroll position within the Finder window. The Finder does not necessarily save this position immediately upon user action.
     * 4   4     SInt32  frOpenChain  Chain of directory IDs for open folders. The Finder numbers directory IDs. The Finder does not necessarily save this information immediately upon user action.
     * 8   1     SInt8   frScript     Extended flags. If the high-bit is set, the script system for displaying the folder's name.
     * 9   1     SInt8   frXFlags     Extended flags. See "Extended Finder Flags".
     * 10  2     SInt16  frComment    Reserved (set to 0). If the high-bit is clear, an ID number for the comment that is displayed in the information window when the user selects a folder and chooses the Get Info command from the File menu. The numbers that identify comments are assigned by the Finder.
     * 12  4     SInt32  frPutAway    If the user moves the folder onto the desktop, the directory ID of the folder from which the user moves it.
     */

    public static final int STRUCTSIZE = 16;

    private final Point frScroll;
    private int frOpenChain;
    private byte frScript;
    private byte frXFlags;
    private short frComment;
    private int frPutAway;

    public DXInfo(byte[] data, int offset) {
        this.frScroll = new Point(data, offset+0);
        this.frOpenChain = Util.readIntBE(data, offset+4);
        this.frScript = Util.readByteBE(data, offset+8);
        this.frXFlags = Util.readByteBE(data, offset+9);
        this.frComment = Util.readShortBE(data, offset+10);
        this.frPutAway = Util.readIntBE(data, offset+12);
    }

    public static int length() { return STRUCTSIZE; }

    /** Scroll position within the Finder window. The Finder does not necessarily save this position immediately upon user action. */
    public final Point getFrScroll() { return this.frScroll; }
    /** Chain of directory IDs for open folders. The Finder numbers directory IDs. The Finder does not necessarily save this information immediately upon user action. */
    public final int getFrOpenChain() { return this.frOpenChain; }
    /** Extended flags. If the high-bit is set, the script system for displaying the folder's name. */
    public final byte getFrScript() { return this.frScript; }
    /** Extended flags. See "Extended Finder Flags". */
    public final byte getFrXFlags() { return this.frXFlags; }
    /** Reserved (set to 0). If the high-bit is clear, an ID number for the comment that is displayed in the information window when the user selects a folder and chooses the Get Info command from the File menu. The numbers that identify comments are assigned by the Finder. */
    public final short getFrComment() { return this.frComment; }
    /** If the user moves the folder onto the desktop, the directory ID of the folder from which the user moves it. */
    public final int getFrPutAway() { return this.frPutAway; }


    public void printFields(PrintStream ps, String prefix) {
        ps.println(prefix + " frScroll: ");
        getFrScroll().print(ps, prefix + "  ");
        ps.println(prefix + " frOpenChain: " + getFrOpenChain());
        ps.println(prefix + " frScript: " + getFrScript());
        ps.println(prefix + " frXFlags: " + getFrXFlags());
        ps.println(prefix + " frComment: " + getFrComment());
        ps.println(prefix + " frPutAway: " + getFrPutAway());
    }

    public void print(PrintStream ps, String prefix) {
        ps.println(prefix + "DXInfo:");
        printFields(ps, prefix);
    }

    public byte[] getBytes() {
        byte[] result = new byte[length()];
        getBytes(result, 0);
        return result;
    }

    public int getBytes(byte[] result, int offset) {
        final int startOffset = offset;

        {
            byte[] tempData = this.frScroll.getBytes();
            System.arraycopy(tempData, 0, result, offset, tempData.length); offset += tempData.length;
        }
        Util.arrayPutBE(result, offset, this.frOpenChain); offset += 4;
        Util.arrayPutBE(result, offset, this.frScript); offset += 1;
        Util.arrayPutBE(result, offset, this.frXFlags); offset += 1;
        Util.arrayPutBE(result, offset, this.frComment); offset += 2;
        Util.arrayPutBE(result, offset, this.frPutAway); offset += 4;

        return offset - startOffset;
    }

    private Field getPrivateField(String name) throws NoSuchFieldException {
        Field f = getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f;
    }

    /* @Override */
    public Dictionary getStructElements() {

        DictionaryBuilder db = new DictionaryBuilder(DXInfo.class.getSimpleName());

        db.add("frScroll", frScroll.getStructElements());
        try {
            db.addUIntBE("frOpenChain", getPrivateField("frOpenChain"), this);
            db.addUIntBE("frScript", getPrivateField("frScript"), this);
            db.addUIntBE("frXFlags", getPrivateField("frXFlags"), this);
            db.addUIntBE("frComment", getPrivateField("frComment"), this);
            db.addUIntBE("frPutAway", getPrivateField("frPutAway"), this);
        } catch(NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return db.getResult();
    }
}
