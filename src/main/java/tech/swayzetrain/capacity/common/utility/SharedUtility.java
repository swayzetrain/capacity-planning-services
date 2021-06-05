package tech.swayzetrain.capacity.common.utility;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import tech.swayzetrain.capacity.common.exception.InvalidUUIDException;

public class SharedUtility {

	public UUID uuidFromString(String uuidString) {
		if (!uuidString.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
			throw new InvalidUUIDException(String.format("%s is not a valid UUID.", uuidString));
		}

		return UUID.fromString(uuidString);
	}

	public void copyNonNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public String[] getNullPropertyNames(Object source) {
		final BeanWrapper src =  PropertyAccessorFactory.forBeanPropertyAccess(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			// check if value of this property is null then add it to the collection
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
