package com.UFlying.config;

import java.util.LinkedHashMap;

/** 常量 */
public class Constants {

	/** 人身意外险保费（单人） */
	public static final int PERSON_INSURANCE_FEE = 340;

	/** 第三方责任险类型 */
	public static final LinkedHashMap<Integer, ThirdpartyInsuranceType> THIRDPARTY_INSURANCE_TYPES = new LinkedHashMap<Integer, ThirdpartyInsuranceType>();

	static {
		// 初始化第三方责任险类型
		ThirdpartyInsuranceType type1 = new ThirdpartyInsuranceType();
		type1.setId(1);
		type1.setFee(2000);
		type1.setDescription("保额20万元，保费2000元");
		ThirdpartyInsuranceType type2 = new ThirdpartyInsuranceType();
		type2.setId(2);
		type2.setFee(5000);
		type2.setDescription("保额50万元，保费5000元");
		THIRDPARTY_INSURANCE_TYPES.put(type1.getId(), type1);
		THIRDPARTY_INSURANCE_TYPES.put(type2.getId(), type2);
	}

}
