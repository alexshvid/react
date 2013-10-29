package com.reactbase.packedobject;

import java.nio.ByteOrder;

/*
 * PackedObject is the structure of metadata in memory, not a object itself
 * 
 * PackedObject has two parameters: memory address and ptr
 * 
 * memory address can be:
 *   - on-heap   byte[]
 *   - off-heap  long
 * 
 */

public abstract class PackedObject {

	public static final boolean isLittleEndian = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN);
	public static final boolean ptr64 = Boolean.getBoolean("reactbase.packedobject.ptr64");
	
	final long offset;
	
	PackedObject(long offset) {
		this.offset = offset;
	}
	
	public abstract int getTypeId();
	
	public abstract int sizeOf();
	
	public abstract void format(Object address, long ptr);
	
	public void copyTo(Object address, long ptr, Object des, long desPtr) {
		PackedObjectMemory.copyTo(address, ptr + offset, des, desPtr + offset, sizeOf());
	}
	
}
