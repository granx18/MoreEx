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

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class SportRecordInfo {
  
  @SerializedName("recordId")
  private Integer recordId = null;
  @SerializedName("planId")
  private Integer planId = null;
  @SerializedName("completed")
  private Boolean completed = null;
  @SerializedName("actualLength")
  private Double actualLength = 0.0;

  /**
   * 运动记录id
   **/
  @ApiModelProperty(required = true, value = "运动记录id")
  public Integer getRecordId() {
    return recordId;
  }
  public void setRecordId(Integer recordId) {
    this.recordId = recordId;
  }

  /**
   * 对应的运动计划id
   **/
  @ApiModelProperty(required = true, value = "对应的运动计划id")
  public Integer getPlanId() {
    return planId;
  }
  public void setPlanId(Integer planId) {
    this.planId = planId;
  }

  /**
   * 是否已经完成且满足要求
   **/
  @ApiModelProperty(value = "是否已经完成且满足要求")
  public Boolean getCompleted() {
    return completed;
  }
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  /**
   * 实际有效长度
   **/
  @ApiModelProperty(value = "实际有效长度")
  public Double getActualLength() {
    return actualLength;
  }
  public void setActualLength(Double actualLength) {
    this.actualLength = actualLength;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SportRecordInfo sportRecordInfo = (SportRecordInfo) o;
    return (this.recordId == null ? sportRecordInfo.recordId == null : this.recordId.equals(sportRecordInfo.recordId)) &&
        (this.planId == null ? sportRecordInfo.planId == null : this.planId.equals(sportRecordInfo.planId)) &&
        (this.completed == null ? sportRecordInfo.completed == null : this.completed.equals(sportRecordInfo.completed)) &&
        (this.actualLength == null ? sportRecordInfo.actualLength == null : this.actualLength.equals(sportRecordInfo.actualLength));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.recordId == null ? 0: this.recordId.hashCode());
    result = 31 * result + (this.planId == null ? 0: this.planId.hashCode());
    result = 31 * result + (this.completed == null ? 0: this.completed.hashCode());
    result = 31 * result + (this.actualLength == null ? 0: this.actualLength.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SportRecordInfo {\n");
    
    sb.append("  recordId: ").append(recordId).append("\n");
    sb.append("  planId: ").append(planId).append("\n");
    sb.append("  completed: ").append(completed).append("\n");
    sb.append("  actualLength: ").append(actualLength).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
