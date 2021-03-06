package cn.aliyun.ttms.product.entity;
import java.io.Serializable;
import java.util.Date;

public class ProductType implements Serializable{
	private static final long serialVersionUID = 1L;
	/**分类的id*/
	private Integer id;
	/**分类的名字*/
	private String name;
	
	private int sort;
	/**上级分类id*/
	private Integer parentId;
	/**标签*/
	private String note;
	 /**项目的创建时间*/
	 private Date createdTime;
	 /**项目的修改时间*/
	 private Date modifiedTime;
	 /**项目的创建人*/
	 private String createdUser;
	 /**项目的修改*/
	 private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", sort=" + sort + ", parentId=" + parentId + ", note="
				+ note + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser="
				+ createdUser + ", modifiedUser=" + modifiedUser + "]";
	}
	
	 
	 
	 
	
		
}
