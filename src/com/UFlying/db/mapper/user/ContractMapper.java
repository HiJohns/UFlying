package com.UFlying.db.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.db.entity.Contract;

@Repository
public class ContractMapper implements RowMapper<Contract> {

	/** 合同文本的扩展名 */
	public static final String CONTRACT_FILE_EXTENSION = ".pdf";

	@Override
	public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contract contract = new Contract();
		contract.setContractType(rs.getInt("contract_type"));
		contract.setContractName(rs.getString("contract_name"));
		contract.setVersion(rs.getString("version"));
		contract.setLastTimestamp(rs.getTimestamp("lasttimestamp"));
		contract.setFileName(contract.getContractName() + contract.getVersion() + CONTRACT_FILE_EXTENSION);
		return contract;
	}

}
