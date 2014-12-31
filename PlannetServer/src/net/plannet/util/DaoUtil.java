package net.plannet.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.plannet.dbutil.QuerySet;

public class DaoUtil {
	
	//ResultSet을 인스턴스 리스트로 반환
	public <T> ArrayList<T> convertToObject(QuerySet querySet, ResultSet rs,
			Class<T> objClass) throws Exception {
		ArrayList<T> result = new ArrayList<T>();
		ArrayList<String> memberNames = querySet.getMemberNames(objClass);
		ArrayList<String> setterNames = querySet.getSetterNames(memberNames);
		while (rs.next()) {
			T instance = createInstance(objClass);
			initInstance(instance, objClass, rs, memberNames, setterNames);
			result.add(instance);
		}
		return result;
	}
	
	//instance 생성
	private <T> T createInstance(Class<T> objClass) throws Exception {
		Constructor<T> constructor = objClass.getConstructor();
		return constructor.newInstance();
	}
	
	//instance에 setter로 iterative하게 초기화
	private <T> void initInstance(T instance, Class<T> objClass,
			ResultSet rs, ArrayList<String> memberNames,
			ArrayList<String> setterNames) throws Exception {
		int idx = 0;
		for (String setterName : setterNames) {
			String memberName = memberNames.get(idx++);
			Field field = objClass.getDeclaredField(memberName);
			Class<?> paramType = field.getType();
			Object args = rs.getObject(memberName);
			Method setter = objClass.getDeclaredMethod(setterName, paramType);
			setter.invoke(instance, args);
		}
	}

}
