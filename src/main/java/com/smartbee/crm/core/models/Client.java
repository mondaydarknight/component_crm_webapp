package com.smartbee.crm.core.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(name = "company_id")
    private Integer companyId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Client{\n");
        stringBuilder.append("    id: ").append(toIndentedString(id)).append("\n");
        stringBuilder.append("    name: ").append(toIndentedString(name)).append("\n");
        stringBuilder.append("    email: ").append(toIndentedString(email)).append("\n");
        stringBuilder.append("    phone: ").append(toIndentedString(phone)).append("\n");
        stringBuilder.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        stringBuilder.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
        stringBuilder.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
        stringBuilder.append("    updatedBy: ").append(toIndentedString(updatedBy)).append("\n");
        return stringBuilder.toString();
    }

    private String toIndentedString(Object obj) {
        return (obj == null) ? "null" : obj.toString().replace("\n", "\n    ");
    }
}
