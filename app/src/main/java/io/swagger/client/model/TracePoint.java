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

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class TracePoint {
  
  @SerializedName("time")
  private Long time = null;
  @SerializedName("latitude")
  private Double latitude = null;
  @SerializedName("longitude")
  private Double longitude = null;

  /**
   * 时间点
   **/
  @ApiModelProperty(required = true, value = "时间点")
  public Long getTime() {
    return time;
  }
  public void setTime(Long time) {
    this.time = time;
  }

  /**
   * 纬度
   **/
  @ApiModelProperty(required = true, value = "纬度")
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * 经度
   **/
  @ApiModelProperty(required = true, value = "经度")
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TracePoint tracePoint = (TracePoint) o;
    return (this.time == null ? tracePoint.time == null : this.time.equals(tracePoint.time)) &&
        (this.latitude == null ? tracePoint.latitude == null : this.latitude.equals(tracePoint.latitude)) &&
        (this.longitude == null ? tracePoint.longitude == null : this.longitude.equals(tracePoint.longitude));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.time == null ? 0: this.time.hashCode());
    result = 31 * result + (this.latitude == null ? 0: this.latitude.hashCode());
    result = 31 * result + (this.longitude == null ? 0: this.longitude.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TracePoint {\n");
    
    sb.append("  time: ").append(time).append("\n");
    sb.append("  latitude: ").append(latitude).append("\n");
    sb.append("  longitude: ").append(longitude).append("\n");
    sb.append("}\n");
    return sb.toString();
  }

  public double LengthFromM(TracePoint from)
  {
    double R = 6371000d;
    double lat1 = latitude;
    double lon1 = longitude;
    double lat2 = from.latitude;
    double lon2 = from.longitude;
    double p = Math.PI / 180;
    double a = 0.5 - Math.cos((lat2 - lat1) * p) / 2 +
            Math.cos(lat1 * p) * Math.cos(lat2 * p) *
                    (1 - Math.cos((lon2 - lon1) * p)) / 2;
    return 2d * R * Math.asin(Math.sqrt(a));
  }

}
