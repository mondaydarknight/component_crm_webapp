package com.smartbee.crm.core.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Company{\n");
        stringBuilder.append("    id: ").append(toIndentedString(id)).append("\n");
        stringBuilder.append("    name: ").append(toIndentedString(name)).append("\n");
        stringBuilder.append("    address: ").append(toIndentedString(address)).append("\n");
        stringBuilder.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        stringBuilder.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
        stringBuilder.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
        stringBuilder.append("    updatedBy: ").append(toIndentedString(updatedBy)).append("\n");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toIndentedString(Object object) {
        return (object == null) ? "null" : object.toString().replace("\n", "\n    ");
    }
}
