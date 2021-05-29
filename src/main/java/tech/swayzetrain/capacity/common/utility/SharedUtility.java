package tech.swayzetrain.capacity.common.utility;

import java.util.UUID;

import tech.swayzetrain.capacity.common.exception.InvalidUUIDException;

public class SharedUtility {
	
	public UUID uuidFromString(String uuidString) {
		if(!uuidString.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
			throw new InvalidUUIDException(String.format("%s is not a valid UUID.", uuidString));
		}
		
		return UUID.fromString(uuidString);
	}

}
