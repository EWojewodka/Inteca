package com.wojewodka.inteca.services.dbo;

import java.util.HashMap;
import java.util.Map;

public enum AnnotationManager {
	INSTANCE;

	private Map<Class<?>, EntityMetadata> buffer = new HashMap<>();

	public static AnnotationManager getInstance() {
		return INSTANCE;
	}

	public EntityMetadata getInfo(Class<?> clazz) {
		EntityMetadata result = buffer.get(clazz);
		if (result == null) {
			result = new EntityMetadata(clazz);
			result.analyze();
			buffer.put(clazz, result);
		}

		return result;
	}

}
