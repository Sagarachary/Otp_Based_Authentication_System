package com.example.app.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private String otp;
    private LocalDateTime createdAt;

    public Tokens() {
		// TODO Auto-generated constructor stub
	}

	public Tokens(int id, Users users, String otp, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.users = users;
		this.otp = otp;
		this.createdAt = createdAt;
	}

	public Tokens(Users users, String otp, LocalDateTime createdAt) {
		super();
		this.users = users;
		this.otp = otp;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Tokens [id=" + id + ", users=" + users + ", otp=" + otp + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, otp, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tokens other = (Tokens) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && Objects.equals(otp, other.otp)
				&& Objects.equals(users, other.users);
	}
    
    
    
}
