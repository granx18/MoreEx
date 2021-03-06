/**
 * api documentation
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import io.swagger.client.model.StudentInfo;
import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class ClassInfo {
  
  @SerializedName("name")
  private String name = null;
  @SerializedName("teacherName")
  private String teacherName = null;
  @SerializedName("students")
  private List<StudentInfo> students = null;

  /**
   * 课程名称
   **/
  @ApiModelProperty(required = true, value = "课程名称")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 老师名称
   **/
  @ApiModelProperty(required = true, value = "老师名称")
  public String getTeacherName() {
    return teacherName;
  }
  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  /**
   * 班内学生
   **/
  @ApiModelProperty(required = true, value = "班内学生")
  public List<StudentInfo> getStudents() {
    return students;
  }
  public void setStudents(List<StudentInfo> students) {
    this.students = students;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassInfo classInfo = (ClassInfo) o;
    return (this.name == null ? classInfo.name == null : this.name.equals(classInfo.name)) &&
        (this.teacherName == null ? classInfo.teacherName == null : this.teacherName.equals(classInfo.teacherName)) &&
        (this.students == null ? classInfo.students == null : this.students.equals(classInfo.students));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.name == null ? 0: this.name.hashCode());
    result = 31 * result + (this.teacherName == null ? 0: this.teacherName.hashCode());
    result = 31 * result + (this.students == null ? 0: this.students.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassInfo {\n");
    
    sb.append("  name: ").append(name).append("\n");
    sb.append("  teacherName: ").append(teacherName).append("\n");
    sb.append("  students: ").append(students).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
