package com.shvid.react.packedobject;


public class ReflectionBasedPackedClass<T extends PackedClass> implements PackedClass {
	
	private final PackedClass[] fields;
	
	private final int fixedSize;
	private final int initCapacity;
	
	public ReflectionBasedPackedClass(long offset, ClassDefinition<T> cr) {
		
		/*
		 * Initialize local cache of fields
		 */
		fields = cr.constructFields((T) this, offset);

		/*
		 * Precalculate sizes
		 */
		
		int size = 0;
		int initCap = 0;
		for (PackedClass field : fields) {
			size += field.getFixedSize();
			initCap += field.getInitCapacity();
		}
		fixedSize = size;
		initCapacity = initCap;
		
	}
	
	@Override
	public int getFixedSize() {
		return fixedSize;
	}

	@Override
	public int getInitCapacity() {
		return initCapacity;
	}

	@Override
	public void format(byte[] blob, long ptr) {
		for (PackedClass field : fields) {
			field.format(blob, ptr);
		}
	}

	@Override
	public void format(long address, long ptr) {
		for (PackedClass field : fields) {
			field.format(address, ptr);
		}
	}

}