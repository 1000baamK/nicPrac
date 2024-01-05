package test.query.model.vo;

public class Query {

	private String mappingId; // 매핑할 id
	private String sql; // 실행할 쿼리문
	private String description; // 설명
	
	public Query() {
		super();
	}

	public Query(String mappingId, String sql, String description) {
		super();
		this.mappingId = mappingId;
		this.sql = sql;
		this.description = description;
	}

	public String getMappingId() {
		return mappingId;
	}

	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Query [mappingId=" + mappingId + ", sql=" + sql + ", description=" + description + "]";
	}
	
	
}
