package com.moas.backend.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分析记录表
 * </p>
 *
 * @author zy
 * @since 2024-08-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("analysis_records")
@Schema(name = "AnalysisRecords", description = "分析记录表")
public class AnalysisRecords extends Model<AnalysisRecords> {

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "记录ID")
    @TableField("record_id")
    private Long recordId;

    @Schema(description = "记录名称")
    @TableField("record_name")
    private String recordName;

    @Schema(description = "记录路径")
    @TableField("record_path")
    private String recordPath;

    @Schema(description = "分析类型（各组学）")
    @TableField("analysis_type")
    private String analysisType;

    @Schema(description = "分析状态（未开始，进行中，已中止，已完成）")
    @TableField("analysis_status")
    private String analysisStatus;

    @Schema(description = "开始时间")
    @TableField("start_time")
    private Date startTime;

    @Schema(description = "结束时间")
    @TableField("end_time")
    private Date endTime;

    @Schema(description = "结束原因")
    @TableField("end_reason")
    private String endReason;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
