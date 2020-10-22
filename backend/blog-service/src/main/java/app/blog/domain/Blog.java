package app.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
@Document("blog")
public class Blog {
    @MongoId
    @Field(value = "_id", targetType = FieldType.STRING)
    public String id;

    @Field(value = "user_id", targetType = FieldType.INT64)
    public Long userId;

    @Field(value = "title", targetType = FieldType.BINARY)
    public byte[] title;

    @Field(value = "content", targetType = FieldType.BINARY)
    public byte[] content;

    @Field(value = "tags", targetType = FieldType.ARRAY)
    public List<String> tags;

    @Field(value = "starts", targetType = FieldType.INT32)
    public Integer stars;

    @Field(value = "un_starts", targetType = FieldType.INT32)
    public Integer unStars;

    @Field(value = "comments")
    public List<Comment> comments = List.of();

    @Field(value = "created_time", targetType = FieldType.DATE_TIME)
    public ZonedDateTime createdTime;

    @Field(value = "created_by", targetType = FieldType.STRING)
    public String createdBy;

    @Field(value = "updated_time", targetType = FieldType.DATE_TIME)
    public ZonedDateTime updatedTime;

    @Field(value = "updated_by", targetType = FieldType.STRING)
    public String updatedBy;
}
