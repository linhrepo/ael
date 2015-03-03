package com.vn.ael.webapp.util;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Configuration;

public class ConvertUtil {
	
	/**
	 * Convert from Configuration List 2 Map
	 * @param confs
	 * @return
	 */
	public static Map<Long,String> fromConfigurationList2Map(List<Configuration> confs){
		Map<Long,String> map = new LinkedHashMap<>();
		if (confs != null){
			for (Configuration configuration : confs){
					map.put(configuration.getId(), configuration.getValue());
			}
		}
		return map;
	}
	
	/**
	 * Change bigDecimal to 0 if it's null
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal getNotNullValue(BigDecimal bigDecimal){
		if (bigDecimal == null){
			return BigDecimal.ZERO;
		}
		return bigDecimal;
	}

}
