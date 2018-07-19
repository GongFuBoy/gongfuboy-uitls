package com.github.gongfuboy.utils.excel;

import java.util.List;

/**
 * 对List进行操作的Util
 * 
 * @author zhoulm18864
 * @version $Id: ListUtils.java, v 0.1 2016年12月7日 下午7:39:30 zhoulm18864 Exp $
 */
public class ListUtils {

	/**
	 * 截取指定长度的List
	 * 
	 * @param list
	 * @param skip
	 * @param pageSize
	 * @return
	 */
	public static <T> List<T> getSubListPage(List<T> list, int skip,
			int pageSize) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		int startIndex = skip;
		int endIndex = skip + pageSize;
		if (startIndex > endIndex || startIndex > list.size()) {
			return null;
		}
		if (endIndex > list.size()) {
			endIndex = list.size();
		}
		return list.subList(startIndex, endIndex);
	}

}
