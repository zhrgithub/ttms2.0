package cn.aliyun.ttms.team.entity;

import java.io.Serializable;
import java.util.Date;
/**团(一个项目下面可以创建多个团)
 *例如项目下的50日团，60日团 
 */
public class Team  implements Serializable  {
	/**定义Team实体类的属性*/
	private static final long serialVersionUID = 8950552582883609957L;
		private Integer id;
		private String name;
		private Integer projectId;
		private Integer valid;
		private String note;
		 /**项目的创建时间*/
		 private Date createdTime;
		 /**项目的修改时间*/
		 private Date modifiedTime;
		private String createdUser ;
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
		public void setName(String name) {
			this.name = name;
		}
		public Integer getProjectId() {
			return projectId;
		}
		public void setProjectId(Integer projectId) {
			this.projectId = projectId;
		}
		public Integer getValid() {
			return valid;
		}
		public void setValid(Integer valid) {
			this.valid = valid;
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
			return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note="
					+ note + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser="
					+ createdUser + ", modifiedUser=" + modifiedUser + "]";
		}
		
		
		
}
