package com.wang.sci.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

public class Collections3 {
	public static String extractToString(@SuppressWarnings("rawtypes") final Collection collection, final String propertyName , final String separator){
		@SuppressWarnings("rawtypes")
		List list = extractToList(collection,propertyName);
		return StringUtils.join(list,separator);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List extractToList(final Collection collection,final String propertyName){
		List list = new ArrayList(collection.size());
		
		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			
		}
		
		return list;
	}
}
