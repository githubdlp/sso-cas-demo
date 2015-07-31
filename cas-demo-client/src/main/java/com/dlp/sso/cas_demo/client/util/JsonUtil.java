package com.dlp.sso.cas_demo.client.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

;

/**
 * The Class JsonUtil.
 */
public final class JsonUtil {

	/**
	 * Private Constructor.
	 */
	private JsonUtil() {
	}

	/**
	 * This converts Object of type E to its JSON string.
	 *
	 * @param <E>
	 *            the element type
	 * @param type
	 *            - The type of Object.
	 * @return - JSON String.
	 */
	public static <E> String toJsonString(final E type) {
		String jsonValue = null;
		if (null != type) {
			final E object = type;
			final ObjectMapper propertyMapper = new ObjectMapper();
			propertyMapper
					.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

			try {
				jsonValue = propertyMapper.writeValueAsString(object);
			} catch (IOException exception) {
				final String errorMsg = "Object to Json transformation failed : "
						+ object.toString();
				exception.printStackTrace();
			}

		} else {
			final String errorMsg = "Object to Json transformation failed, as Object is null!!!";
		}
		return jsonValue;

	}

}
