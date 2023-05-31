package it.ktpm.keva.common.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.ktpm.keva.common.util.DateTimeUtils;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityBase implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = innerClass.ID)
    protected  UUID id;

    @Version
    @Column(name = innerClass.VERSION)
    protected  int version;

    @CreatedBy
    @Column(name = innerClass.CREATE_BY)
    protected  String createBy;

    @CreatedDate
    @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @Column(name = innerClass.CREATE_AT)
    protected  LocalDateTime createAt;

    @LastModifiedBy
    @Column(name = innerClass.LAST_MODIFIED_BY)
    protected  String lastModifiedBy;

    @LastModifiedDate
    @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT)
    @Column(name = innerClass.LAST_MODIFIED_AT)
    protected  LocalDateTime lastModifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityBase that = (EntityBase) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    static class innerClass{
        static final String ID = "ID";
        static final String VERSION = "VERSION";
        static final String CREATE_AT = "CREATE_AT";
        static final String CREATE_BY = "CREATE_BY";
        static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
        static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";

    }
}


